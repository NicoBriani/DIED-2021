package util;

import java.sql.*;

public class ConnectionBD {

	private static final String url = "jdbc:postgresql://localhost:5432/tp_died";
	private static final String user = "postgres";
	private static final String pass = "";

	public ConnectionBD() {

	}

	static Connection con = null;
	static Statement stm = null;

	public static Connection conectar() {

		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			System.out.println("No conectado");
		}

		return con;
	}

}
