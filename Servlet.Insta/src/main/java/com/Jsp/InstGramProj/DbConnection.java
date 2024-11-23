package com.Jsp.InstGramProj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	private static String url="jdbc:mysql://localhost:3306/instagram";
	private static String user="root";
	private static String password="9938";
	public static Connection con;
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,user,password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
}
