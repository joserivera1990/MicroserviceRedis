package com.architecture.RedisAWS.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.amazonaws.services.lambda.runtime.Context;

public class AuditMessage {
  
	
	private static final String FIND_WAREHOUSE_ITEM = "INSERT INTO public.audit (identificationNumber, category, event, date_register) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
	
	public void saveRegister(Integer category, String body, Integer identificationNumber, Context context) {

		ResultSet resultSet = null;

		try (Connection connection = getConnection(context);
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_WAREHOUSE_ITEM)) {

			preparedStatement.setInt(1, identificationNumber);
			preparedStatement.setInt(2, category);
			preparedStatement.setString(3, body);
			
			resultSet = preparedStatement.executeQuery();
		} catch (Exception e) {
			context.getLogger().log("Unexpected error trying to search a warehouse item: " + e.getMessage());
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					// Ignore sql exception during the result set closing
				}
			}
		}
	}

	private static Connection getConnection(Context context) {

		Connection connection = null;
		try {

			Class.forName("org.postgresql.Driver");

			String host = "ccpaudit.cm8zpcpj3xtp.us-east-1.rds.amazonaws.com";
			String port = "5432";
			String database = "CCPAudit";
			String databaseUser = "ccpadmin";
			String databasePassword = "1030583889";

			StringBuilder connectionString = new StringBuilder("jdbc:postgresql://");
			connectionString.append(host);
			connectionString.append(":");
			connectionString.append(port);
			connectionString.append("/");
			connectionString.append(database);

			context.getLogger().log("Connection string: " + connectionString.toString());
			
			connection = DriverManager.getConnection(connectionString.toString(), databaseUser, databasePassword);
		} catch (ClassNotFoundException e) {
			context.getLogger().log("Connection Failed! Check output console");
		} catch (SQLException e) {
			context.getLogger().log("Connection Failed! Check output console");
		}
		return connection;

	}

}
