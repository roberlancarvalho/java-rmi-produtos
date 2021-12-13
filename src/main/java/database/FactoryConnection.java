package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoryConnection {

	private static final String URL = "jdbc:postgresql://localhost/ap2";
	private static final String USER = "postgres";
	private static final String PASS = "banco123";
	private static Connection conn;
	
	private FactoryConnection() {
		// TODO Auto-generated constructor stub
	}
	
	public static Connection getConnection() throws SQLException {
		
		if (conn == null) {
			conn = DriverManager.getConnection(URL, USER, PASS);
			conn.setAutoCommit(false);
		}
		
		return conn;
	}
}
