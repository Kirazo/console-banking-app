package project.bank.utility;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
	public static Connection getConnection() {
		Connection con = null;
		try {
			FileReader rdr = new FileReader("./bankdb.properties");
			Properties properties = new Properties();
			properties.load(rdr);
			String driver = properties.getProperty("d");
			String url = properties.getProperty("url");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println("Error, could not connect to database.");
		}
		return con;
	}
}
