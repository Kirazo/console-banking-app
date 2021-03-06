package project.bank.utility;
import java.sql.*;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
	public static Connection getConnection() {
		Connection con = null;
		try {
			//./src/main/java/project/bank/utility/bankdb.properties
			FileReader rdr = new FileReader("C:\\Users\\Howard\\Desktop\\Eclipse\\Project 0\\bank-app\\src\\main\\java\\project\\bank\\utility\\bankdb.properties");
			//FileReader rdr = new FileReader("./src/main/java/project/bank/utility/bankdb.properties");
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
