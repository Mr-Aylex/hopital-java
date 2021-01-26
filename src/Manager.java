import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Manager {
	private String url = "jdbc:mysql://localhost/hopital?serverTimezone=UTC";
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
	public void loginUser() throws SQLException {
		PreparedStatement pstm = this.getJbdc().prepareStatement("SELECT nom, mdp FROM utilisateur WHERE nom=?");
		ResultSet rs = pstm.executeQuery();
		if(true) {
			
		} else {
			
		}
	}
	public void updateUser() throws SQLException {
		PreparedStatement pstm = this.getJbdc().prepareStatement("UPDATE SET utilisateur WHERE id=?");
		ResultSet rs = pstm.executeQuery();
	}
	public void insertMedic() throws SQLException {
		PreparedStatement pstm = this.getJbdc().prepareStatement("INSERT INTO medicaments(nom, toxicite, nb) VALUES(?,?,?)");
		ResultSet rs = pstm.executeQuery();
	}
	public void updateMedic() throws SQLException {
		PreparedStatement pstm = this.getJbdc().prepareStatement("UPDATE SET medicaments WHERE nom=?");
		ResultSet rs = pstm.executeQuery();
	}
	public void deleteMedic() throws SQLException {
		PreparedStatement pstm = this.getJbdc().prepareStatement("DELETE FROM medicaments WHERE nom=?");
		ResultSet rs = pstm.executeQuery();
	}
}
