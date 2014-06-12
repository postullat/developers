package com.epam.lab.developers.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.lab.developers.dao.MapItemDAOJdbc;
import com.epam.lab.developers.game.map.object.active.ActiveObject;
import com.epam.lab.developers.game.map.object.active.CoffeeMashine;
import com.epam.lab.developers.game.map.object.active.CookStove;
import com.epam.lab.developers.game.map.object.active.Notebook;
import com.epam.lab.developers.game.map.object.active.Printer;
import com.epam.lab.developers.game.map.object.active.Refrigerator;
import com.epam.lab.developers.game.map.object.active.Server;
import com.epam.lab.developers.game.map.object.active.Sink;
import com.epam.lab.developers.game.map.object.active.Toilet;

public class ActiveObjectDAO extends MapItemDAOJdbc<ActiveObject> {

	private String SELECT_TEAM_OBJ_BY_ID = "SELECT team_object.rotation_angle, team_object.i, team_object.j, object.path, object.name, object.id "
			+ "FROM team_object INNER JOIN object ON team_object.id_team = ? "
			+ "AND team_object.id_object = object.id ORDER BY team_object.i, team_object.j";

	static final Logger logger = Logger.getLogger(ActiveObjectDAO.class);
	private static final String LOG4J_FILE_PROPERTIES = "log4j.properties";

	static {
		PropertyConfigurator.configure(new ActiveObjectDAO().getClass().getResource("/" + LOG4J_FILE_PROPERTIES));
	}

	@Override
	public List<ActiveObject> getAllByTeamId(int id) {

		PreparedStatement stat;
		try {
			stat = conn.prepareStatement(SELECT_TEAM_OBJ_BY_ID);
			stat.setInt(1, id);
			ResultSet rs = stat.executeQuery();

			return createObjectList(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Can't execute the following SQL query:"+SELECT_TEAM_OBJ_BY_ID);
			return Collections.emptyList();

		}
	}

	@Override
	public List<ActiveObject> getAllByMapId(int id) {
		return null;
	}

	private List<ActiveObject> createObjectList(ResultSet rs) {
		List<ActiveObject> objs = new ArrayList<>();
		int id = -1;
		try {
			while (rs.next()) {

				id = rs.getInt("id");

				if (id == 6) {
					objs.add(new Notebook(rs.getInt("i"), rs.getInt("j"), rs.getInt("rotation_angle"), rs.getString("path")));
				}
				if (id == 7) {
					objs.add(new Printer(rs.getInt("i"), rs.getInt("j"), rs.getInt("rotation_angle"), rs.getString("path")));
				}
				if (id == 9) {
					objs.add(new Sink(rs.getInt("i"), rs.getInt("j"), rs.getInt("rotation_angle"), rs.getString("path")));
				}
				if (id == 10) {
					objs.add(new Toilet(rs.getInt("i"), rs.getInt("j"), rs.getInt("rotation_angle"), rs.getString("path")));
				}
				if (id == 11) {
					objs.add(new CookStove(rs.getInt("i"), rs.getInt("j"), rs.getInt("rotation_angle"), rs.getString("path")));
				}
				if (id == 12) {
					objs.add(new Refrigerator(rs.getInt("i"), rs.getInt("j"), rs.getInt("rotation_angle"), rs.getString("path")));
				}
				if (id == 13) {
					objs.add(new Server(rs.getInt("i"), rs.getInt("j"), rs.getInt("rotation_angle"), rs.getString("path")));
				}
				if (id == 14) {
					objs.add(new CoffeeMashine(rs.getInt("i"), rs.getInt("j"), rs.getInt("rotation_angle"), rs.getString("path")));
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Can't create list of active objects. Result set id = " + id);
		}
		return objs;
	}

}
