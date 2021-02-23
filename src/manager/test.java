package manager;

import java.sql.*;
import java.util.Properties;
import com.mysql.*;
public class test {
	public static void main(String[] args) throws SQLException {
		Manager manager= new Manager();
		manager.ajoutRdv(0, 0, 0, null, 0);
	}

}
