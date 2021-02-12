package manager;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class Manager {
	private String url = "jdbc:mysql://localhost/hopital?serverTimezone=UTC";
	private String user = "root";
	private String password = "";
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
		int rs = pstm.executeUpdate();
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
	public void updateMedic(String nom, String toxicite, int nb) throws SQLException {//Modification des médicaments
		String sql = "UPDATE medicaments SET nom='?', toxicite='?', nb='?'";
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
	
	public void selectMedecin() throws SQLException {//Afficher médecin
		String sql = "SELECT nom, prenom, mail, specialites.nom_spe as specialites, medecin.telephone, medecin.lieu FROM utilisateur \r\n"
				+ "INNER JOIN medecin ON utilisateur.id = medecin.id_user \r\n"
				+ "INNER JOIN specialites ON medecin.id_specialite = specialites.id \r\n"
				+ "WHERE role_user = 'medecin'";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			ArrayList<Object> ficheInfo = new ArrayList<Object>();
			ficheInfo.add(rs.getString("nom"));
			ficheInfo.add(rs.getString("prenom"));
			ficheInfo.add(rs.getString("mail"));
			ficheInfo.add(rs.getString("specialites"));
			ficheInfo.add(rs.getString("telephone"));
			ficheInfo.add(rs.getString("lieu"));
			
			for(int i = 0; i < ficheInfo.size(); i++) {
				System.out.println(ficheInfo.get(i));
			}
		}
	}
    /*
	public ArrayList<ArrayList> exportRDV() throws SQLException {//Exportation des RDV
		//Délimiteurs qui doivent être dans le fichier CSV
		  final String DELIMITER = ",";
		  final String SEPARATOR = "\n";   
		 //En-tête de fichier
		  final String HEADER = "Nom,Motif,Specialites,Heure,Date";
		    
		String sql = "SELECT utilisateur.nom as nom, motif.nom_motif as motif,specialites.nom_spe as specialites, heure.nom_heure as heure, date_rdv FROM rdv \r\n"
				+ "INNER JOIN medecin ON id_medecin = medecin.id \r\n"
				+ "INNER JOIN utilisateur ON medecin.id_user = utilisateur.id INNER JOIN heure ON heure_id = heure.id INNER JOIN specialites ON specialites.id = medecin.id_specialite \r\n"
				+ "INNER JOIN motif ON motif.id = rdv.id_motif";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			ArrayList<Object> rdvList = new ArrayList<Object>();
			rdvList.add("nom");
			rdvList.add("motif");
			rdvList.add("specialites");
			rdvList.add("heure");
			rdvList.add("date_rdv");
			
			for(int i = 0; i < rdvList.size(); i++) {
				System.out.println(rdvList.get(i));
			}
		}
		
		FileWriter file = null;
		
		try {
			file = new FileWriter("Rdv.csv");
	        //Ajouter l'en-tête
	        file.append(HEADER);
	        //Ajouter une nouvelle ligne après l'en-tête
	        file.append(SEPARATOR);
	        SQLException liste;
			//Itérer bookList
	        Iterator it = liste.iterator();
	        while(it.hasNext())
	        {
	          rdvList b = (rdvList)it.next();
	          file.append(b.getNom());
	          file.append(DELIMITER);
	          file.append(b.getMotif());
	          file.append(DELIMITER);
	          file.append(b.getSpecialites());
	          file.append(DELIMITER);
	          file.append(b.getHeure());
	          file.append(DELIMITER);
	          file.append(b.getDate());
	          
	          file.append(SEPARATOR);
	        }
	        file.close();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	*/
}
