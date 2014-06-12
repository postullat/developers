package com.epam.lab.developers.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.lab.developers.dao.MapItemDAOJdbc;
import com.epam.lab.developers.game.map.unit.JuniorDeveloper;
import com.epam.lab.developers.game.map.unit.MiddleDeveloper;
import com.epam.lab.developers.game.map.unit.SeniorDeveloper;
import com.epam.lab.developers.game.map.unit.Unit;

public class UnitDAO extends MapItemDAOJdbc<Unit> {

	private static final String SELECT_TEAM_UNITS_BY_ID = "SELECT team_unit.id, team_unit.id_unit, team_unit.rotation_angle, team_unit.i, team_unit.j,"
			+ " unit.stand_texture, unit.texture1, unit.texture2, unit.texture3, unit.texture4, unit.name "
			+ "FROM team_unit INNER JOIN unit ON team_unit.id_team = ? "
			+ "AND team_unit.id_unit = unit.id ORDER BY team_unit.i, team_unit.j";

	static final Logger logger = Logger.getLogger(UnitDAO.class);
	private static final String LOG4J_FILE_PROPERTIES = "log4j.properties";

	static {
		PropertyConfigurator.configure(new UnitDAO().getClass().getResource(
				"/" + LOG4J_FILE_PROPERTIES));
	}

	@Override
	public List<Unit> getAllByTeamId(int id) {

		PreparedStatement stat;
		try {
			stat = conn.prepareStatement(SELECT_TEAM_UNITS_BY_ID);
			stat.setInt(1, id);
			ResultSet rs = stat.executeQuery();

			return createUnitList(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Can't execute sql query:" + SELECT_TEAM_UNITS_BY_ID
					+ "; id=" + id);
			return Collections.emptyList();
		}
	}

	@Override
	public List<Unit> getAllByMapId(int id) {
		return null;
	}

	public List<Unit> createUnitList(ResultSet rs) {
		List<Unit> units = new ArrayList<>();

		try {
			while (rs.next()) {

				if (rs.getInt("id_unit") == 0 | rs.getInt("id_unit") == 3) {

					units.add(new JuniorDeveloper(rs.getInt("id"), rs.getInt("i"),
							rs.getInt("j"), rs.getInt("rotation_angle"), Arrays
									.asList(rs.getString("stand_texture"),
											rs.getString("texture1"),
											rs.getString("texture2"),
											rs.getString("texture3"),
											rs.getString("texture4")), rs
									.getString("name")));

				}

				if (rs.getInt("id_unit") == 1 | rs.getInt("id_unit") == 4) {

					units.add(new MiddleDeveloper(rs.getInt("id"), rs.getInt("i"),
							rs.getInt("j"), rs.getInt("rotation_angle"), Arrays
									.asList(rs.getString("stand_texture"),
											rs.getString("texture1"),
											rs.getString("texture2"),
											rs.getString("texture3"),
											rs.getString("texture4")), rs
									.getString("name")));

				}
				if (rs.getInt("id_unit") == 2 | rs.getInt("id_unit") == 5) {

					units.add(new SeniorDeveloper(rs.getInt("id"), rs.getInt("i"),
							rs.getInt("j"), rs.getInt("rotation_angle"), Arrays
									.asList(rs.getString("stand_texture"),
											rs.getString("texture1"),
											rs.getString("texture2"),
											rs.getString("texture3"),
											rs.getString("texture4")), rs
									.getString("name")));

				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Can't execute Result Set using method rs.getInt(\"id_unit\")");
		}

		return units;
	}

}
