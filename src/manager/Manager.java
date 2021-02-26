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

import entity.Medicaments;
import entity.Rdv;

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
	public boolean loginUser(String mail, String mdp) throws SQLException {//Connexion à l'interface de gestion
		String sql = "SELECT * FROM utilisateur WHERE mail = ? AND mdp = ?";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		pstm.setString(1, mail);
		pstm.setString(2, mdp);
		ResultSet rs = pstm.executeQuery();
		if(rs.next() ) {
			System.out.println(" oui");
			return true;
		}
		else {
			System.out.println(" non");
			return false;
		}
	}
	public void insertUser(String nom, String prenom, String mail, String mdp, String role_user) throws SQLException {//Création d'un profil admin ou patient
		String sql = "INSERT INTO utilisateur(nom, prenom, mail, mdp, role_user) VALUES (?,?,?,?,?)";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		pstm.setString(1, nom);
		pstm.setString(2, prenom);
		pstm.setString(3, mail);
		pstm.setString(4, mdp);
		pstm.setString(5, role_user);
		
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
	public void selectUser() throws SQLException {//Affichage des utilisateurs
		String sql = "SELECT * FROM utilisateur";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			ArrayList<Object> users = new ArrayList<Object>();
			users.add(rs.getString("nom"));
			users.add(rs.getString("prenom"));
			users.add(rs.getString("mail"));
			users.add(rs.getString("mdp"));
			users.add(rs.getString("role_user"));
			
			for(int i = 0; i < users.size(); i++) {
				System.out.println(users.get(i));
			}
		}
	}
	public void updateUser(String nom, String prenom, String mail, String mdp) throws SQLException {//Modification du profil
		String sql = "SELECT * FROM utilisateur WHERE nom = ?";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		pstm.setString(1, nom);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			sql = "UPDATE utilisateur SET nom='?', prenom='?', mail='?', mdp='?'";
			pstm = this.getJbdc().prepareStatement(sql);
			pstm.setString(1, nom);
			pstm.setString(2, prenom);
			pstm.setString(3, mail);
			pstm.setString(4, mdp);
			
			int rowsUpdated = pstm.executeUpdate();
			if(rowsUpdated > 0) {
				System.out.println("Le profil a été modifié");
			}
			else {
				System.out.println("Veuillez réessayer");
			}
		}
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
	public void updateMedic(String nom, String toxicite, int nb) throws SQLException {//Modification des médicaments
		String sql = "UPDATE medicaments SET nom=?, toxicite=?, nb=?";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			rs.updateString("nom", rs.getString("nom").toUpperCase());
			rs.updateString("toxicite", rs.getString("toxicite"));
			rs.updateInt("nb", rs.getInt("nb"));
			rs.updateRow();
		}
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
