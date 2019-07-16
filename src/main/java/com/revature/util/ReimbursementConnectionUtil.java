package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ReimbursementConnectionUtil {
	
	private static final Logger logger = Logger.getLogger(ReimbursementConnectionUtil.class);
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e) {
			logger.warn("Exception thrown adding the driver",e);
		}
	}

	public static Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@firstinstance.cyhmny1cgean.us-east-2.rds.amazonaws.com:1521:ORCL";
		String username = "ajoshi97";
		String password = "Mangya0304!!";

		return DriverManager.getConnection(url,username,password);


	}

	public static void main(String[] args) {
		try {
			getConnection();
			System.out.println("Connection successful");
		} catch (SQLException e) {
			System.out.println("Could not connect.");
		}
	}
	
	
}
