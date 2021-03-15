-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 15 mars 2021 à 16:20
-- Version du serveur :  8.0.21
-- Version de PHP : 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `hopital`
--

-- --------------------------------------------------------

--
-- Structure de la table `dossier_patients`
--

DROP TABLE IF EXISTS `dossier_patients`;
CREATE TABLE IF NOT EXISTS `dossier_patients` (
  `id` smallint UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_patient` int UNSIGNED NOT NULL,
  `adresse_post` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `mutuelle` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `num_ss` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `opt` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `regime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_to_idpatient` (`id_patient`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `heure`
--

DROP TABLE IF EXISTS `heure`;
CREATE TABLE IF NOT EXISTS `heure` (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `nom_heure` time NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `heure`
--

INSERT INTO `heure` (`id`, `nom_heure`) VALUES
(2, '09:00:00'),
(3, '09:15:00'),
(4, '09:30:00'),
(5, '09:45:00'),
(6, '10:00:00'),
(7, '10:15:00'),
(8, '10:30:00'),
(9, '10:45:00'),
(10, '11:00:00'),
(11, '11:15:00'),
(12, '12:00:00'),
(13, '12:15:00'),
(14, '12:30:00'),
(15, '12:45:00'),
(16, '13:00:00'),
(17, '13:15:00'),
(18, '13:30:00'),
(19, '13:45:00'),
(20, '14:00:00'),
(21, '14:15:00'),
(22, '14:30:00'),
(23, '14:45:00'),
(24, '15:00:00'),
(25, '15:15:00'),
(26, '15:30:00'),
(27, '15:45:00'),
(28, '16:00:00'),
(29, '16:15:00'),
(30, '16:30:00'),
(31, '16:45:00'),
(32, '17:00:00'),
(33, '17:15:00'),
(34, '17:30:00'),
(35, '17:45:00'),
(36, '17:45:00'),
(37, '18:00:00'),
(38, '18:15:00'),
(39, '18:30:00'),
(40, '18:45:00'),
(41, '19:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `medecin`
--

DROP TABLE IF EXISTS `medecin`;
CREATE TABLE IF NOT EXISTS `medecin` (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_user` int UNSIGNED NOT NULL,
  `id_specialite` int UNSIGNED NOT NULL,
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `lieu` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_spe_to_medecin` (`id_specialite`),
  KEY `fk_id_user_to_medecin` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `medecin`
--

INSERT INTO `medecin` (`id`, `id_user`, `id_specialite`, `telephone`, `lieu`) VALUES
(1, 27, 1, 'testesset', 'etsest'),
(3, 32, 1, 'tel', 'lieu'),
(4, 32, 1, '12132131', 'bondy'),
(5, 49, 1, '512854', 'sfs');

-- --------------------------------------------------------

--
-- Structure de la table `medicaments`
--

DROP TABLE IF EXISTS `medicaments`;
CREATE TABLE IF NOT EXISTS `medicaments` (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `nom` varchar(80) COLLATE utf8_bin NOT NULL,
  `toxicite` varchar(80) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `nb` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `medicaments`
--

INSERT INTO `medicaments` (`id`, `nom`, `toxicite`, `nb`) VALUES
(6, 'test', 'Faible', 38),
(7, 'tests', 'Modéré', 2),
(8, 'dsfsdf', 'Faible', 3),
(9, 'pmlo', 'Extrème', 4),
(10, 'tedfjdfg', 'Extrème', 5),
(11, 'medic1', 'Extrème', 4);

-- --------------------------------------------------------

--
-- Structure de la table `motif`
--

DROP TABLE IF EXISTS `motif`;
CREATE TABLE IF NOT EXISTS `motif` (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `nom_motif` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `id_spe` int UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_id_spe_to_motif` (`id_spe`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `motif`
--

INSERT INTO `motif` (`id`, `nom_motif`, `id_spe`) VALUES
(1, 'djlkb', 1),
(2, 'aucun', 1);

-- --------------------------------------------------------

--
-- Structure de la table `rdv`
--

DROP TABLE IF EXISTS `rdv`;
CREATE TABLE IF NOT EXISTS `rdv` (
  `id` smallint UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_patient` int UNSIGNED NOT NULL,
  `id_medecin` int UNSIGNED NOT NULL,
  `id_motif` int UNSIGNED NOT NULL,
  `date_rdv` date NOT NULL,
  `heure_id` int UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_to_idpatien` (`id_patient`),
  KEY `fk_medecin_to_idmedecin` (`id_medecin`) USING BTREE,
  KEY `fk_id_motif_to_rdv` (`id_motif`),
  KEY `fk_id_heure_to_rdv` (`heure_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `rdv`
--

INSERT INTO `rdv` (`id`, `id_patient`, `id_medecin`, `id_motif`, `date_rdv`, `heure_id`) VALUES
(1, 1, 1, 1, '2021-02-24', 5),
(3, 29, 1, 2, '2021-03-01', 5),
(4, 1, 1, 2, '2021-03-01', 6),
(5, 33, 3, 2, '2021-03-10', 5);

-- --------------------------------------------------------

--
-- Structure de la table `specialites`
--

DROP TABLE IF EXISTS `specialites`;
CREATE TABLE IF NOT EXISTS `specialites` (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `nom_spe` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `specialites`
--

INSERT INTO `specialites` (`id`, `nom_spe`) VALUES
(1, 'test');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `prenom` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `mail` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `mdp` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `role_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `nom`, `prenom`, `mail`, `mdp`, `role_user`) VALUES
(1, 'CARMONE', 'Alexandre', 'alexandre@hotmail.fr', '1234', 'admin'),
(27, 'test', 'test', 'test@test.fr', '81dc9bdb52d04dc20036dbd8313ed055', 'medecin'),
(32, 'nom', 'prenom', 'Mail', 'mdp', 'medecin'),
(33, 'nom1', 'prenom1', 'mail1', '123456', 'utilisateur'),
(34, 'testtt', 'testtt', 'Mail', '1234', 'utilisateur'),
(48, 'testt', 'testt', 'mail11', '1234', 'utilisateur'),
(49, 'testttt', 'testdsf', 'mail11t', '1234', 'medecin');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `medecin`
--
ALTER TABLE `medecin`
  ADD CONSTRAINT `fk_id_user_to_medecin` FOREIGN KEY (`id_user`) REFERENCES `utilisateur` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `fk_spe_to_medecin` FOREIGN KEY (`id_specialite`) REFERENCES `specialites` (`id`);

--
-- Contraintes pour la table `motif`
--
ALTER TABLE `motif`
  ADD CONSTRAINT `fk_id_spe_to_motif` FOREIGN KEY (`id_spe`) REFERENCES `specialites` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Contraintes pour la table `rdv`
--
ALTER TABLE `rdv`
  ADD CONSTRAINT `fk_id_heure_to_rdv` FOREIGN KEY (`heure_id`) REFERENCES `heure` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `fk_id_medecin_to_rdv` FOREIGN KEY (`id_medecin`) REFERENCES `medecin` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `fk_id_motif_to_rdv` FOREIGN KEY (`id_motif`) REFERENCES `motif` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
