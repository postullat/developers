package com.epam.lab.developers.dao.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.lab.developers.dao.DAOJdbc;
import com.epam.lab.developers.entity.User;
import com.epam.lab.developers.entity.User.Statistic;

public class UserDAO extends DAOJdbc<User> {

	private static final String ADD_USER_NAME_AND_PASSWORD_QUERY = "INSERT INTO user(name, password) VALUES(?,?)";
	private static final String ADD_USER_INFO_QUERY = "INSERT INTO info(user, email, photo) VALUES(?,?,?)";
	private static final String ADD_USER_STATISTIC_QUERY = "INSERT INTO stats(user, score, winnings, losings) VALUES(?, ?, ?, ?)";

	private static final String SELECT_USER_DATA = "SELECT * FROM user AS t1 JOIN info AS t2 ON t1.name = ? AND t2.user = t1.id";
	private static final String SELECT_USER_STATISTIC = "SELECT * FROM stats WHERE user = ?";
	
	private static final String UPDATE_USER_NAME_AND_PASSWORD_QUERY = "UPDATE user SET name = ?, password = ? WHERE id = ?";
	private static final String UPDATE_USER_INFO_QUERY = "UPDATE info SET email = ?, photo = ? WHERE user = ?";
	private static final String UPDATE_USER_STATISTIC_QUERY = "UPDATE stats SET score = ?, winnings = ?, losings = ? WHERE user = ?";

	static final Logger logger = Logger.getLogger(UserDAO.class);
	private static final String LOG4J_FILE_PROPERTIES = "log4j.properties";

	static {
		PropertyConfigurator.configure(new UserDAO().getClass().getResource("/" + LOG4J_FILE_PROPERTIES));
	}

	@Override
	public User getById(int id) {
		String nameChecker = null;
		String passChecker = null;
		String photo = null;
		String email = null;
		int score = 0;
		int winnings = 0;
		int losings = 0;
		User user = null;
		String sql = "";
		try {
			Statement stmt = conn.createStatement();
			sql = "SELECT * FROM user AS t1 JOIN info AS t2 ON t1.id = '" + id + "' AND t2.user = '" + id + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				nameChecker = rs.getString("name");
				passChecker = rs.getString("password");
				email = rs.getString("email");
				photo = rs.getString("photo");
				user = new User(nameChecker, passChecker, email, photo);
				user.setId(id);

				String sqlStats = "SELECT * FROM stats WHERE user = '" + id + "'";
				ResultSet rsStats = stmt.executeQuery(sqlStats);
				if (rsStats.next()) {
					score = rsStats.getInt("score");
					winnings = rsStats.getInt("winnings");
					losings = rsStats.getInt("losings");
				}
				Statistic stats = user.new Statistic(score, winnings, losings);
				user.setStats(stats);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Can't execute sql query:" + sql);
		}
		return user;
	}

	@Override
	public int insert(User entity) {

		int id = 0;

		try {
			PreparedStatement stmt = conn.prepareStatement(ADD_USER_NAME_AND_PASSWORD_QUERY);
			stmt.setString(1, entity.getName());
			stmt.setString(2, entity.getPassword());
			stmt.executeUpdate();
			Statement stmtId = conn.createStatement();
			String sql = "SELECT id FROM user WHERE name = '" + entity.getName() + "'";
			ResultSet rs = stmtId.executeQuery(sql);
			if (rs.next()) {
				id = rs.getInt("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Can't execute sql query:" + ADD_USER_NAME_AND_PASSWORD_QUERY + "; name=" + entity.getName() + "; password="
					+ entity.getPassword());
		}

		try {
			PreparedStatement stmtInfo = conn.prepareStatement(ADD_USER_INFO_QUERY);
			stmtInfo.setInt(1, id);
			stmtInfo.setString(2, entity.getInfo().getEmail());
			stmtInfo.setString(3, entity.getInfo().getPhoto());
			stmtInfo.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Can't execute sql query:" + ADD_USER_INFO_QUERY + "; userId=" + id + "; email=" + entity.getInfo().getEmail()
					+ "; photo=" + entity.getInfo().getPhoto());
		}

		try {

			if (null != entity.getStats()) {
				PreparedStatement stmtStats = conn.prepareStatement(ADD_USER_STATISTIC_QUERY);
				stmtStats.setInt(1, id);
				stmtStats.setInt(2, entity.getStats().getScore());
				stmtStats.setInt(3, entity.getStats().getWinnings());
				stmtStats.setInt(4, entity.getStats().getLosings());
				stmtStats.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Can't execute sql query:" + ADD_USER_STATISTIC_QUERY + "; userId=" + id + "; score=" + entity.getStats().getScore()
					+ "; winnings=" + entity.getStats().getWinnings() + "; losings=" + entity.getStats().getLosings());
		}

		return 0;
	}

	@Override
	public User getByName(String name) {
		String nameChecker = null;
		String passChecker = null;
		String photo = null;
		String email = null;
		int score = 0;
		int winnings = 0;
		int losings = 0;
		int id;
		User user = null;
		try {
			
			PreparedStatement stmt = conn.prepareStatement(SELECT_USER_DATA);
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				id = rs.getInt("id");
				nameChecker = rs.getString("name");
				passChecker = rs.getString("password");
				email = rs.getString("email");
				photo = rs.getString("photo");
				user = new User(nameChecker, passChecker, email, photo);
				user.setId(id);
				
				PreparedStatement stmt2 = conn.prepareStatement(SELECT_USER_STATISTIC);
				stmt2.setInt(1, id);
				ResultSet rsStats = stmt2.executeQuery();
				
				if (rsStats.next()) {
					score = rsStats.getInt("score");
					winnings = rsStats.getInt("winnings");
					losings = rsStats.getInt("losings");
				}
				Statistic statistic = user.new Statistic(score, winnings, losings);
				user.setStats(statistic);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Can't execute one of the following queries:\n1) " + SELECT_USER_DATA + "\n2) " + SELECT_USER_STATISTIC);
		}
		return user;
	}

	@Override
	public int update(User user) {
		try {
			PreparedStatement stmt = conn.prepareStatement(UPDATE_USER_NAME_AND_PASSWORD_QUERY);

			stmt.setString(1, user.getName());
			stmt.setString(2, user.getPassword());
			stmt.setInt(3, user.getId());
			stmt.executeUpdate();
			PreparedStatement stmtInfo = conn.prepareStatement(UPDATE_USER_INFO_QUERY);

			stmtInfo.setString(1, user.getInfo().getEmail());
			stmtInfo.setString(2, user.getInfo().getPhoto());
			stmtInfo.setInt(3, user.getId());
			stmtInfo.executeUpdate();

			if (null != user.getStats()) {
				PreparedStatement stmtStats = conn.prepareStatement(UPDATE_USER_STATISTIC_QUERY);
				stmtStats.setInt(1, user.getStats().getScore());
				stmtStats.setInt(2, user.getStats().getWinnings());
				stmtStats.setInt(3, user.getStats().getLosings());
				stmt.setInt(4, user.getId());
				stmtStats.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Can't execute one of the following queries:\n1) " + UPDATE_USER_NAME_AND_PASSWORD_QUERY + "\n2) "
					+ UPDATE_USER_INFO_QUERY + "\n3) " + UPDATE_USER_STATISTIC_QUERY);
		}
		return 0;
	}

}
