package com.projet.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	//create db connection 
	
	@SuppressWarnings("finally")
	public static Connection createConnection () throws Exception {
		Connection con = null;
		try{
			Class.forName(Constants.dbClass);
			con = DriverManager.getConnection(Constants.dbUrl, Constants.dbUser, Constants.dbPwd);
		} catch (Exception e ){
			throw e;
		} finally {
			
			return con;
		}
	}
	
	
	
	
	

}
