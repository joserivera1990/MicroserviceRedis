package com.architecture.RedisAWS.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Main {

	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String url = "jdbc:postgresql://ccpaudit.cm8zpcpj3xtp.us-east-1.rds.amazonaws.com:5432/CCPAudit";
		Properties props = new Properties();
		props.setProperty("user","ccpadmin");
		props.setProperty("password","1030583889");
		props.setProperty("ssl","true");
		try {
			Connection conn = DriverManager.getConnection(url, props);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from audit");
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
