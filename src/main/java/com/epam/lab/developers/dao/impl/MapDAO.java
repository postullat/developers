package com.epam.lab.developers.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.lab.developers.dao.DAOJdbc;
import com.epam.lab.developers.dao.JdbcConnection;
import com.epam.lab.developers.game.map.GameMap;

public class MapDAO extends DAOJdbc<GameMap> {

    private String selectMapById = "SELECT * FROM map WHERE id = ?";
    private String selectMapIdByName = "SELECT id FROM map WHERE name = ?";
    private String selectTeamIdByMapId = "SELECT team.id FROM team WHERE id_map = ?";
    private String selectFloorByMapId = "SELECT floor_texture FROM map WHERE id = ?";
    
    private static final String LOG4J_FILE_PROPERTIES = "log4j.properties";
    
    static final Logger logger = Logger.getLogger(JdbcConnection.class);
    
    static{
    	Properties props = new Properties();
		ClassLoader loader = MapDAO.class.getClassLoader();
		InputStream stream = loader.getResourceAsStream(LOG4J_FILE_PROPERTIES);
		PropertyConfigurator.configure(props);
		try {
			props.load(stream);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Can't load file "+LOG4J_FILE_PROPERTIES);
		}
    }
    
    
    
    @Override
    public GameMap getById(int id) {
        PreparedStatement stat;
		try {
			stat = conn.prepareStatement(selectMapById);
			stat.setInt(1, id);
			ResultSet rs = stat.executeQuery();
			rs.next();
			GameMap map = new GameMap(rs.getInt("frame_width"), rs.getInt("frame_height"), rs.getInt("rows"),
					rs.getInt("columns"), rs.getInt("player_count"));
			
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Can't execute sql query: "+selectMapById+"; id="+id);
			return new GameMap();
		}
		
	
    }

    @Override
    public GameMap getByName(String name) {

        int id;
		id = getMapID(name);
		return getById(id);

    }

    public int getMapID(String name) {

        PreparedStatement stat;
		try {
			stat = conn.prepareStatement(selectMapIdByName);
			stat.setString(1, name);
			ResultSet rs = stat.executeQuery();
			rs.next();
			int id = rs.getInt("id");
			
			return id;
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Can't execute sql query: "+selectMapIdByName+"; name="+name);
			return -1;
		}
    }

    public List<Integer> getTeams(int mapId) {
        List<Integer> teams = new ArrayList<>();

        PreparedStatement stat;
		try {
			stat = conn.prepareStatement(selectTeamIdByMapId);
			stat.setInt(1, mapId);
			ResultSet rs = stat.executeQuery();
			while(rs.next()) {
				teams.add(rs.getInt("id"));
			}
			
			return teams;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Can't execute sql query: "+selectTeamIdByMapId+"; id_map="+mapId);
			return Collections.emptyList();
		}
    }

    public String getMapFloorTexture(int id) {
        PreparedStatement stat;
		try {
			stat = conn.prepareStatement(selectFloorByMapId);
			stat.setInt(1, id);
			ResultSet rs = stat.executeQuery();
			rs.next();
			
			return rs.getString("floor_texture");
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Can't execute sql query: "+selectFloorByMapId+"; id="+id);
			return "error";
		}
    }

    @Override
    public int insert(GameMap entity) {
        return 0;
    }

    @Override
    public int update(GameMap entity) {
        return 0;
    }

}
