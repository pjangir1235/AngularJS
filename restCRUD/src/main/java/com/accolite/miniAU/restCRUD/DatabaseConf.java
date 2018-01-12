package com.accolite.miniAU.restCRUD;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConf {
	public DatabaseConf(){
		
	}
	public Connection DatabaseConnection()
	{
		Connection con=null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/restDatabase","root","root");
				
		} catch (Exception e) {
				e.printStackTrace();
		}
		return con;
	}
		
}
