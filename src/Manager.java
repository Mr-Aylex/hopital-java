import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Manager {
	private String url = "jdbc:mysql://localhost/hsp?serverTimezone=UTC";
	private String user = "root";
	private String password = "";
	public Connection getJbdc() {
		
		try {
			Connection cnx = DriverManager.getConnection(this.url,this.user,this.password);
			System.out.print("Etat de la connexion");
			System.out.print(cnx.isClosed()?"fermée":"ouverte");
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
			return true;
		}
		else {
			return false;
		}
	}
	public void insertUser(String nom, String prenom, String mail, String mdp, String role_user) throws SQLException {//Création d'un profil admin ou patient
		String sql = "INSERT INTO utilisateur(nom, prenom, mail, mdp, role_user) VALUES (?,?,?,?,?)";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
	}
	public void selectUser() throws SQLException {//Création d'un profil admin ou patient
		String sql = "SELECT * FROM utilisateur";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			int id = rs.getInt("id");
			System.out.print(rs.wasNull()?"inconnu":id);
			System.out.print("\t\t");
			
			String nom = rs.getString("nom");
			System.out.print(rs.wasNull()?"inconnu":nom);
			System.out.print("\t\t");
			
			String prenom = rs.getString("prenom");
			System.out.print(rs.wasNull()?"inconnu":prenom);
			System.out.print("\t\t");
			
			String mail = rs.getString("mail");
			System.out.print(rs.wasNull()?"inconnu":mail);
			System.out.print("\t\t");
			
			String mdp = rs.getString("mdp");
			System.out.print(rs.wasNull()?"inconnu":mdp);
			System.out.print("\t\t");
			
			String role_user = rs.getString("role_user");
			System.out.print(rs.wasNull()?"inconnu":role_user);
			System.out.print("\t\t");
		}
	}
	public void updateUser(String nom, String prenom, String mail, String mdp) throws SQLException {//Modification du profil
		String sql = "UPDATE utilisateur SET nom='+nom+', prenom='+prenom+', mail='+mail+', mdp='+mdp' WHERE id='+id+'";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
	}
	public void selectMedic() throws SQLException {//Affichage de tous les médicaments
		String sql = "SELECT * FROM medicaments";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			System.out.println("nom");
		}
	}
	public void insertMedic(String nom, String toxicite, int nb) throws SQLException {//Ajout de médicaments
		String sql = "INSERT INTO medicaments(nom, toxicite, nb) VALUES(?,?,?)";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
	}
	public void updateMedic(String nom, String toxicite, int nb) throws SQLException {//Modification des médicaments
		String sql = "UPDATE medicaments SET nom='+nom+', toxicite='+toxicite+', nb='+nb+'";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			rs.updateString("nom", rs.getString("nom").toUpperCase());
			rs.updateString("toxicite", rs.getString("toxicite"));
			rs.updateInt("nb", rs.getInt("nb"));
			rs.updateRow();
		}
	}
	public void deleteMedic() throws SQLException {//Suppression des médicaments
		String sql = "DELETE FROM medicaments WHERE nom=?";
		PreparedStatement pstm = this.getJbdc().prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
	}
	public void exportRDV() throws SQLException {//Exportation des RDV
		
	}
}
