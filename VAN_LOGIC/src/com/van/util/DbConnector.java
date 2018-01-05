package com.van.util;
/*
 * DbConnector.java			Jan 1, 2018
 *
 *
 *
 */
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Connector class for manging database connection
 * @author George
 *
 */
public class DbConnector {
	
	//private static DbConnector connector=null;
	
	/**
	 * Default constructor
	 */
	public DbConnector() {
	}
	
	/*public DbConnector getDbConnector(){
		if(connector!=null){
			return connector;
		}else{
			return new DbConnector();
		}
	}*/
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]){
		try{
			getConnection();
		}catch(Exception e){e.printStackTrace();}
	}
	
	/**
	 * 
	 * @return
	 */
	public static Connection getConnection(){
		Connection connector=null;
		String url = "jdbc:mysql://localhost:3306/VANTRAX";
		try{
			Class.forName ("com.mysql.jdbc.Driver");
			connector = DriverManager.getConnection (url, "root", "");
		}catch(Exception e){e.printStackTrace();}
		return connector;
	}
	
	
}
