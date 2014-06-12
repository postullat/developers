package com.epam.lab.developers.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.lab.developers.setting.PropertyUtil;
import com.epam.lab.developers.setting.Settings;

public abstract class JdbcConnection {

	private static String url;
	private static String password;
	private static String userName;
	private static Connection conn;
	static final Logger logger = Logger.getLogger(JdbcConnection.class);
	private static final String LOG4J_FILE_PROPERTIES = "log4j.properties";
	
	static {
		

		PropertyUtil.initialize();
		try {
			Properties props = new Properties();
			ClassLoader loader = JdbcConnection.class.getClassLoader();
			InputStream stream = loader.getResourceAsStream(LOG4J_FILE_PROPERTIES);
			props.load(stream);
			PropertyConfigurator.configure(props);
			
			Class.forName(PropertyUtil.getProperty(Settings.JDBC_DRIVER));
			url = PropertyUtil.getProperty(Settings.JDBC_DB_URL);
			userName = PropertyUtil.getProperty(Settings.JDBC_DB_USER_NAME);
			password = PropertyUtil.getProperty(Settings.JDBC_DB_PASSWORD);
			conn = DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.error("Can't load jdbc drive "
					+ PropertyUtil.getProperty(Settings.JDBC_DRIVER));
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Can't connect to DB. URL=" + url + "; User name="
					+ userName + "; Password=" + password.replaceAll(".*", "*"));
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Can't load file "+LOG4J_FILE_PROPERTIES);
		}
	}

	public static Connection getConnection() {
		return conn;
	}
}
