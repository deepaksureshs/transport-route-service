package com.company.transportrouteservice.config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DbConfig {

	private static final Logger LOGGER = Logger.getLogger(DbConfig.class);
	private static Connection dbConnection = null;

	private DbConfig() {

	}

	public static Connection getConnection() throws Exception {
		if (dbConnection != null) {
			return dbConnection;
		} else {
			Properties properties = new Properties();
			try {
				properties.load(DbConfig.class.getClassLoader().getResourceAsStream("connection.properties"));
				if (properties != null) {
					String dbDriver = properties.getProperty("dbDriver");
					String connectionUrl = properties.getProperty("connectionUrl");
					String userName = properties.getProperty("userName");
					String password = properties.getProperty("password");
					Class.forName(dbDriver);
					dbConnection = DriverManager.getConnection(connectionUrl, userName, password);
				}
			} catch (IOException e) {
				LOGGER.error("Connection.properties file loading error");
				throw new Exception();
			} catch (Exception e) {
				LOGGER.error("Exception occure on db connection :: " + e.getMessage());
				throw new Exception("Db connection failed :: " + e.getMessage());
			}

		}
		return dbConnection;
	}

}
