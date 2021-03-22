package manager;
import java.sql.Date;  
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.mysql.cj.xdevapi.Statement;

import entity.Medecin;
import entity.Medicaments;
import entity.Rdv;
import entity.Utilisateur;

public class Manager {
	private String url = "jdbc:mysql://localhost/hopital?serverTimezone=UTC";
	private String user = "root";
	private String password = "";
	//Délimiteurs qui doivent être dans le fichier CSV
	private static final String DELIMITER = ",";
	private static final String SEPARATOR = "\n";   
	//En-tête de fichier Medicaments.csv
	private static final String HEADER = "Nom, Toxicite, Nombre";
	//En-tête de fichier Rdv.csv
	private static final String HEADER1 = "Id, IdPatient, IdMedecin, IdMotif, DateRdv, HeureId";
	/*
	private ArrayList<Object> medicamentList;
	*/
	public Connection getJbdc() {
		
		try {
			Connection cnx = DriverManager.getConnection(this.url,this.user,this.password);
			System.out.print("Etat de la connexion :");
			System.out.print(cnx.isClosed()?"fermée":"ouverte \r\n");
			return cnx;
			
		} catch (SQLException e) {
			System.out.print("erreur");
			e.printStackTrace();
			return null;
		}
	}
	public void selectWhere() throws SQLException { //Page 22 du cours
		PreparedStatement pstm = this.getJbdc().prepareStatement("SELECT * FROM utilisateur",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			System.out.print(rs.getString("nom"));
		}
	}
	public Utilisateur loginUser(String mail, String mdp) throws SQLException {//Connexion à l'interface de gestion
		String sql = "SELECT * FROM utilisateur WHERE mail = ? AND mdp = ?";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		pstm.setString(1, mail);
		pstm.setString(2, mdp);
		ResultSet rs = pstm.executeQuery();
		Utilisateur user = null;
		if(rs.next() ) {
			user = new  Utilisateur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("mail"), rs.getString("mdp"), rs.getString("role_user"));
			System.out.println("oui");
		}
		return user;
	}
	
	public int insertUser(String nom, String prenom, String mail, String mdp, String role_user) throws SQLException {//Création d'un profil admin ou patient
		String sql = "SELECT id FROM utilisateur WHERE mail = ? or (nom = ? and prenom = ?)";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		pstm.setString(1, mail);
		pstm.setString(2, nom);
		pstm.setString(3, prenom);
		
		ResultSet rs = pstm.executeQuery();
		if(!rs.next()) {
			sql = "INSERT INTO utilisateur(nom, prenom, mail, mdp, role_user) VALUES (?,?,?,?,?)";
			pstm = this.getJbdc().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			pstm.setString(1, nom);
			pstm.setString(2, prenom);
			pstm.setString(3, mail);
			pstm.setString(4, mdp);
			pstm.setString(5, role_user);
			pstm.executeUpdate();
			rs = pstm.getGeneratedKeys();
			while(rs.next()) {
				return rs.getInt(1);
			}	
		}
		else {
			return rs.getInt("id");
		}
		return 0;
		
	}
	public ArrayList<ArrayList> getRdv(String id) throws SQLException {
		String req = "";
		if (!id.equals("0")) {
			req = "WHERE medecin.id = " + id;
		}
		String sql = "SELECT utilisateur.nom, utilisateur.prenom, heure.nom_heure, date_rdv, motif.nom_motif FROM rdv"
				+ " INNER JOIN utilisateur ON utilisateur.id = rdv.id_patient INNER JOIN medecin ON rdv.id_medecin = medecin.id"
				+ " INNER JOIN motif ON rdv.id_motif = motif.id INNER JOIN heure ON rdv.heure_id = heure.id " + req;
		
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = pstm.executeQuery();ArrayList<ArrayList> array = new ArrayList<ArrayList>();
		
		while (rs.next()) {
			ArrayList<String> subarray = new ArrayList<String>();
			subarray.add(rs.getString("nom"));
			subarray.add(rs.getString("prenom"));
			subarray.add(rs.getString("date_rdv"));
			subarray.add(rs.getString("nom_heure"));
			subarray.add(rs.getString("nom_motif"));
			array.add(subarray);
		}
		return array;
	}
	
	public void insertMedecin(String nom, String prenom, String mail,String mdp, String num, String lieu, String idSpetialite) throws SQLException {
		int idMedecin = insertUser(nom, prenom, mail, mdp, "medecin");
		System.out.println(num);
		System.out.println(lieu);
		System.out.println(idSpetialite);
		String sql = "INSERT INTO medecin (id_user, id_specialite, telephone, lieu) VALUES (?, ?, ?, ?) ";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		pstm.setInt(1, idMedecin);
		pstm.setString(2, idSpetialite);
		pstm.setString(3, num);
		pstm.setString(4, lieu);
		int rs = pstm.executeUpdate();
	}
	public void insertRdv(String nom, String prenom, String mail, int id_medecin, String date_rdv, int heure_id) throws SQLException {//Création d'un profil admin ou patient
		int id_patient = insertUser(nom, prenom, mail, "123456","utilisateur");
		String[] result = date_rdv.split("-");
		Date daate = Date.valueOf(result[2]+"-"+result[1]+"-"+result[0]);
		String sql = "INSERT INTO rdv (id_patient, id_medecin, id_motif, date_rdv, heure_id) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		pstm.setInt(1, id_patient);
		pstm.setInt(2, id_medecin);
		pstm.setInt(3, 2);
		pstm.setDate(4, daate);
		pstm.setInt(5, heure_id);
		
		int rs = pstm.executeUpdate();
		try {
			if(rs > 0) {
				System.out.println("L\'utilisateur a bien été ajouté");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public Map<String, String> selectSpetialite() throws SQLException {
		String sql = "SELECT * FROM specialites";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		Map<String, String> spetialites = new HashMap<String,String>();
		while (rs.next()) {
			spetialites.put(rs.getString("id"), rs.getString("nom_spe"));
		}
		return spetialites;
	}
	
	public ArrayList<ArrayList> selectUser() throws SQLException {//Affichage des utilisateurs
		String sql = "SELECT * FROM utilisateur";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		ArrayList<ArrayList> users = new ArrayList<ArrayList>();
		while(rs.next()) {
			ArrayList<String> user = new ArrayList<String>();
			user.add(rs.getString("nom"));
			user.add(rs.getString("prenom"));
			user.add(rs.getString("mail"));
			user.add(rs.getString("mdp"));
			user.add(rs.getString("role_user"));
			users.add(user);
		}
		return users;
	}
	public Utilisateur updateUser(String nom, String prenom, String mail, int id) throws SQLException {//Modification du profil
		String sql = "UPDATE utilisateur SET nom=?, prenom=?, mail=? WHERE id = ?";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		pstm.setString(1, nom);
		pstm.setString(2, prenom);
		pstm.setString(3, mail);
		pstm.setInt(4, id);
		int rowsUpdated = pstm.executeUpdate();
		
		if(rowsUpdated > 0) {
			System.out.println("Le profil a été modifié");
		}
		else {
			System.out.println("Veuillez réessayer");
		}
		sql = "SELECT * FROM utilisateur WHERE nom = ? and prenom = ? and mail = ?";
		pstm = this.getJbdc().prepareStatement(sql);
		pstm.setString(1, nom);
		pstm.setString(2, prenom);
		pstm.setString(3, mail);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			Utilisateur utilisateur = new  Utilisateur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("mail"), rs.getString("mdp"), rs.getString("role_user"));
			return utilisateur;
		}
		return null;
		
	}
	public Medecin hydratMedecin(int id_user) throws SQLException {
		String sql = "SELECT * FROM medecin WHERE id_user = ?";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		pstm.setInt(1, id_user);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			Medecin medecin = new  Medecin(rs.getInt("id"), rs.getInt("id_user"), rs.getInt("id_specialite"), rs.getString("telephone"), rs.getString("lieu"));
			return medecin;
		}
		return null;
	}
	
	public void updateMedecin(String lieu, String tel, int id) throws SQLException {
		String sql = "UPDATE medecin SET lieu = ?, telephone = ? WHERE id_user = ?";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		pstm.setString(1, lieu);
		pstm.setString(2, tel);
		pstm.setInt(3, id);
		int rowsUpdated = pstm.executeUpdate();
		System.out.println("Medecin modifier");
	}
	
	public ArrayList<ArrayList> selectMedic() throws SQLException {//Affichage de tous les médicaments
		String sql = "SELECT * FROM medicaments";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		ArrayList<ArrayList> liste = new ArrayList<ArrayList>();
		while(rs.next()) {
			ArrayList<Object> subList = new ArrayList<Object>();
			subList.add(rs.getString("nom"));
			subList.add(rs.getString("toxicite"));
			subList.add(rs.getInt("nb"));
			liste.add(subList);
		}
		return liste;
	}
	public ArrayList<String> selectNomMedic() throws SQLException {
		String sql = "SELECT * FROM medicaments";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		ArrayList<String> liste = new ArrayList<String>();
		while(rs.next()) {
			liste.add(rs.getString("nom"));
		}
		return liste;
	}
	public void insertMedic(String nom, String toxicite, int nb) throws SQLException {//Ajout de médicaments
		String sql = "SELECT * FROM medicaments WHERE nom = ?";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		pstm.setString(1, nom);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			int nombre = rs.getInt("nb");
			
			sql = "UPDATE medicaments SET nb = ? WHERE nom = ?";
			pstm = this.getJbdc().prepareStatement(sql);
			pstm.setInt(1, nombre+nb);
			pstm.setString(2, nom);
			pstm.execute();
		}
		else {
			sql = "INSERT INTO medicaments(nom, toxicite, nb) VALUES(?,?,?)";
			pstm = this.getJbdc().prepareStatement(sql);
			pstm.setString(1, nom);
			pstm.setString(2, toxicite);
			pstm.setInt(3, nb);
			boolean se = pstm.execute();
		}		
	}
	public void getMedic(String nom, int nombre) throws SQLException {
		String sql = "SELECT * FROM medicaments WHERE nom = ?";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		pstm.setString(1, nom);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			int nombreIni = rs.getInt("nb");
			
			sql = "UPDATE medicaments SET nb = ? WHERE nom = ?";
			pstm = this.getJbdc().prepareStatement(sql);
			pstm.setInt(1, nombreIni-nombre);
			pstm.setString(2, nom);
			pstm.execute();
		}
	}
	public void updateMedic(String nom, String toxicite, String nomInitial) throws SQLException {//Modification des médicaments
		String sql = "UPDATE medicaments SET nom=?, toxicite=? WHERE nom=?";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		pstm.setString(1, nom);
		pstm.setString(2, toxicite);
		pstm.setString(3, nomInitial);
		pstm.execute();
		
	}
	public void deleteMedic(String nom, String toxicite, int nb) throws SQLException {//Suppression des médicaments
		String sql = "DELETE FROM medicaments WHERE nom = ?";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		pstm.setString(1, nom);
		
		int rowsDeleted = pstm.executeUpdate();
		if(rowsDeleted > 0) {
			System.out.println("Produit supprimé");
			}
		else {
			System.out.println("Erreur dans la suppression");
		}
	}
	
	public Map<String, String> selectMedecin() throws SQLException {//Afficher médecin
		String sql = "SELECT nom, medecin.id FROM utilisateur INNER JOIN medecin ON medecin.id_user = utilisateur.id";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		Map<String,String> ficheInfo = new HashMap<String,String>();
		while(rs.next()) {
			ficheInfo.put(rs.getString("id"),rs.getString("nom"));
		}
		return ficheInfo;
	}
	public ArrayList<ArrayList> selectHeureDispo(String id, String date) throws SQLException {
		String sql = "SELECT * FROM heure WHERE id not in (SELECT heure_id FROM rdv WHERE id_medecin= ? AND date_rdv = ? )";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		String[] result = date.split("-");
		Date daate = Date.valueOf(result[2]+"-"+result[1]+"-"+result[0]);
		pstm.setString(1,id);
		pstm.setDate(2,daate);
		ResultSet rs = pstm.executeQuery();
		ArrayList<ArrayList> rdvs = new ArrayList<ArrayList>();
		while(rs.next()) {
			ArrayList<String> subarray = new ArrayList<String>();
			subarray.add(rs.getString("id"));
			subarray.add(rs.getString("nom_heure"));
			rdvs.add(subarray);
		}
		return rdvs;
	}
	
	public void exportMedic() throws SQLException {//Exportation des produits		
		//Création de ton tableau des médicaments de type arrayList
		ArrayList<Medicaments> tabMedicaments = new ArrayList<Medicaments>();
		String sql = "SELECT * FROM medicaments";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		ResultSet rs = pstm.executeQuery(); 
		while(rs.next()) {
			// new medicament
			Medicaments m = new Medicaments(rs.getInt("id"), rs.getString("nom"), rs.getString("toxicite"), rs.getInt("nb"));
			tabMedicaments.add(m);
			/*this.medicamentList = new ArrayList<Object>();
			medicamentList.add(rs.getString("nom"));
			medicamentList.add(rs.getString("toxicite"));
			medicamentList.add(rs.getInt("nb"));
			// array add medicament
			tabMedicaments.add(medicamentList);*/
			
			/*for(int i = 0; i < medicamentList.size(); i++) {
				System.out.println(medicamentList.get(i));
			}*/
		}
		
		FileWriter file = null;
		
		try {
			file = new FileWriter("Medicaments.csv");
	        //Ajouter l'en-tête
	        file.append(HEADER);
	        //Ajouter une nouvelle ligne après l'en-tête
	        file.append(SEPARATOR);
			
	        for(Medicaments m : tabMedicaments) {
	        	  file.append(m.getNom());
		          file.append(DELIMITER);
		          file.append(m.getTocixite());
		          file.append(DELIMITER);
		          file.append(String.valueOf(m.getNb()));
		          file.append(SEPARATOR);
	        }
	        file.close();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void exportRdv() throws SQLException {
		ArrayList<Rdv> tabRdv = new ArrayList<Rdv>();
		String sql = "SELECT * FROM rdv";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			Rdv r = new Rdv(rs.getInt("id"), rs.getInt("id_patient"), rs.getInt("id_medecin"), rs.getInt("id_motif"), rs.getDate("date_rdv"), rs.getInt("heure_id"));
			tabRdv.add(r);
		}
		
		FileWriter file = null;
		
		try {
			file = new FileWriter("Rdv.csv");
			file.append(HEADER1);
			file.append(SEPARATOR);
			
			for(Rdv r : tabRdv) {
				file.append(String.valueOf(r.getId()));
				file.append(DELIMITER);
				file.append(String.valueOf(r.getId_patient()));
				file.append(DELIMITER);
				file.append(String.valueOf(r.getId_medecin()));
				file.append(DELIMITER);
				file.append(String.valueOf(r.getId_motif()));
				file.append(DELIMITER);
				file.append(String.valueOf(r.getDate_rdv()));
				file.append(DELIMITER);
				file.append(String.valueOf(r.getHeure_id()));
				file.append(SEPARATOR);
			}
			file.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<ArrayList> selectDisponibilite() throws SQLException {//Visibilité des médecins disponibles
		ArrayList<ArrayList> tabMedecins = new ArrayList<ArrayList>();
		String sql = "SELECT nom, prenom, mail, specialites.nom_spe as specialites, medecin.lieu as lieu FROM utilisateur r\n\""
				+ "INNER JOIN medecin ON utilisateur.id = medecin.id_user \r\n"
				+ "INNER JOIN specialites ON medecin.id_specialite = specialites.id \r\n"
				+ "WHERE role_user='medecin'";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			ArrayList<Object> liste = new ArrayList<Object>();
			liste.add(rs.getString("nom"));
			liste.add(rs.getString("prenom"));
			liste.add(rs.getString("specialites"));
			liste.add(rs.getString("lieu"));
			tabMedecins.add(liste);
		}
		return tabMedecins;
	}
	
	public void insertDossier(int id_patient, String adresse_post, String mutuelle, String num_ss, String opt, String regime) throws SQLException {//Saisir les informations du patient
		String sql = "INSERT INTO dossier_patients(id_patient, adresse_post, mutuelle, num_ss, opt, regime) VALUES(?,?,?,?,?,?)";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		pstm.setInt(1, id_patient);
		pstm.setString(2, adresse_post);
		pstm.setString(3, mutuelle);
		pstm.setString(4, num_ss);
		pstm.setString(5, opt);
		pstm.setString(6, regime);
		
		int rs = pstm.executeUpdate();
		try {
			if(rs > 0) {
				System.out.println("Dossier du patient ajouté");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ajoutRdv(int id_patient, int id_medecin, int id_motif, Date date_rdv, int heure_id) throws SQLException {//Service de prise de RDV
		String sql = "INSERT INTO rdv(id_patient, id_medecin, id_motif, date_rdv, heure_id) VALUES(?,?,?,?,?)";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		pstm.setInt(1, id_patient);
		pstm.setInt(2, id_medecin);
		pstm.setInt(3, id_motif);
		pstm.setDate(4, date_rdv);
		pstm.setInt(5, heure_id);
		
		int rs = pstm.executeUpdate();
		
		try {
			if(rs > 0) {
				System.out.println("Rendez-vous ajouté");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
