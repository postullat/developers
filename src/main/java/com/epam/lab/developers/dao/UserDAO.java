package com.epam.lab.developers.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.lab.developers.entity.User;
import com.epam.lab.developers.entity.User.Stats;

public class UserDAO extends DAOJdbc<User> {
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
		try {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM user AS t1 JOIN info AS t2 ON t1.id = '" + id + "' AND t2.user = '" + id + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				nameChecker = rs.getString("name");
				passChecker = rs.getString("password");
				email = rs.getString("email");
				photo = rs.getString("photo");
				user = new User(nameChecker, passChecker, email, photo);
				user.setId(id);
				
				/*�������� ���������� �����������*/
				String sqlStats = "SELECT * FROM stats WHERE user = '" + id + "'";
				ResultSet rsStats = stmt.executeQuery(sqlStats);
				if (rsStats.next()) {
					score = rsStats.getInt("score");
					winnings = rsStats.getInt("winnings");
					losings = rsStats.getInt("losings");
				}
				Stats stats = user.new Stats(score, winnings, losings);
				user.setStats(stats);
			}
		}  catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int insert(User entity) {
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO user(name, password) VALUES(?,?)");//���������� ����� ��������
			stmt.setString(1, entity.getName());
			stmt.setString(2, entity.getPassword());
			stmt.executeUpdate();
			Statement stmtId = conn.createStatement();
			String sql = "SELECT id FROM user WHERE name = '" + entity.getName() + "'";//������� �� ����������� ����� ����� ��������
			ResultSet rs = stmtId.executeQuery(sql);
			int id = 0;
			if (rs.next()) {
				id = rs.getInt("id");
			}
			PreparedStatement stmtInfo = conn.prepareStatement("INSERT INTO info(user, email, photo) VALUES(?,?,?)");//���������� �������� info
			stmtInfo.setInt(1, id);
			stmtInfo.setString(2, entity.getInfo().getEmail());
			stmtInfo.setString(3, entity.getInfo().getPhoto());
			stmtInfo.executeUpdate();
			
			/*��������� � ������� stats ���������� �����������*/
			if (null != entity.getStats()) {
				PreparedStatement stmtStats = conn.prepareStatement("INSERT INTO stats(user, score, winnings, losings) VALUES(?, ?, ?, ?)");
				stmtStats.setInt(1, id);
				stmtStats.setInt(2, entity.getStats().getScore());
				stmtStats.setInt(3, entity.getStats().getWinnings());
				stmtStats.setInt(4, entity.getStats().getLosings());
				stmtStats.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
/*����������� ����������� �� ����*/
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
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM user AS t1 JOIN info AS t2 ON t1.name = '" + name + "' AND t2.user = t1.id";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				id = rs.getInt("id");
				nameChecker = rs.getString("name");
				passChecker = rs.getString("password");
				email = rs.getString("email");
				photo = rs.getString("photo");
				user = new User(nameChecker, passChecker, email, photo);
				user.setId(id);
				
				/*�������� ���������� �����������*/
				String sqlStats = "SELECT * FROM stats WHERE user = '" + id + "'";
				ResultSet rsStats = stmt.executeQuery(sqlStats);
				if (rsStats.next()) {
					score = rsStats.getInt("score");
					winnings = rsStats.getInt("winnings");
					losings = rsStats.getInt("losings");
				}
				Stats stats = user.new Stats(score, winnings, losings);
				user.setStats(stats);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	@Override
	public int update(User user) {
		try {
			PreparedStatement stmt = conn.prepareStatement("UPDATE user SET name = ?, password = ? WHERE id = '" +user.getId() + "'");//���������� ����� ��������
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getPassword());
			stmt.executeUpdate();
			PreparedStatement stmtInfo = conn.prepareStatement("UPDATE info SET email = ?, photo = ? WHERE user = '" +user.getId() + "'");//���������� �������� info
			stmtInfo.setString(1, user.getInfo().getEmail());
			stmtInfo.setString(2, user.getInfo().getPhoto());
			stmtInfo.executeUpdate();
			
			/*��������� � ������� stats ���������� �����������*/
			if (null != user.getStats()) {
				PreparedStatement stmtStats = conn.prepareStatement("UPDATE stats SET score = ?, winnings = ?, losings = ? WHERE user = '" +user.getId() + "'");
				stmtStats.setInt(1, user.getStats().getScore());
				stmtStats.setInt(2, user.getStats().getWinnings());
				stmtStats.setInt(3, user.getStats().getLosings());
				stmtStats.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
