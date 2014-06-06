package com.epam.lab.developers.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.epam.lab.developers.setting.PropertyUtil;
import com.epam.lab.developers.setting.Settings;

public abstract class JdbcConnection {

	private static String url;
	private static String password;
	private static String userName;
	private static Connection conn;

	static {
		PropertyUtil.initialize();
		try {
			Class.forName(PropertyUtil.getProperty(Settings.JDBC_DRIVER));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		url = PropertyUtil.getProperty(Settings.JDBC_DB_URL);
		userName = PropertyUtil.getProperty(Settings.JDBC_DB_USER_NAME);
		password = PropertyUtil.getProperty(Settings.JDBC_DB_PASSWORD);
		try {
			conn = DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		return conn;
	}
}
