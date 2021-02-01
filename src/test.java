
import java.sql.*;
import java.util.Properties;
import com.mysql.*;
public class test {
	public static void main(String[] args) throws SQLException {
		Manager manager= new Manager();
		System.out.println(manager.loginUser("alexandre@hotmail.fr", "1234"));
	}

}
