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
import com.epam.lab.developers.game.map.object.MapObject;
import com.epam.lab.developers.game.map.object.passive.Desk;
import com.epam.lab.developers.game.map.object.passive.Wall;
import com.epam.lab.developers.game.map.object.passive.WallCorner;

public class PassiveObjectDAO extends MapItemDAOJdbc<MapObject> {

    private static final String SELECT_PASSIVE_OBJECTS_BY_MAP_ID = "SELECT passive_object.rotation_angle, passive_object.i, passive_object.j,"
            + "object.path, object.name, object.id FROM passive_object INNER JOIN object ON passive_object.id_map = ? "
            + "AND passive_object.id_object = object.id ORDER BY passive_object.i, passive_object.j";

    
	private static final Logger logger = Logger.getLogger(PassiveObjectDAO.class);
	private static final String LOG4J_FILE_PROPERTIES = "log4j.properties";
	
	static {
		 PropertyConfigurator.configure(new PassiveObjectDAO().getClass().getResource(
			    "/"+LOG4J_FILE_PROPERTIES));
	}
	
    @Override
    public List<MapObject> getAllByTeamId(int id) {
        return null;
    }

    @Override
    public List<MapObject> getAllByMapId(int id) {
        PreparedStatement stat;
		try {
			stat = conn.prepareStatement(SELECT_PASSIVE_OBJECTS_BY_MAP_ID);
			stat.setInt(1, id);
			ResultSet rs = stat.executeQuery();
			
			return createObjectList(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Can't select passive objects using query:"+SELECT_PASSIVE_OBJECTS_BY_MAP_ID+"; id="+id);
			return Collections.emptyList();
		}
    }

    private List<MapObject> createObjectList(ResultSet rs) {
        List<MapObject> objs = new ArrayList<>();
        try {
			while(rs.next()) {

			    switch(rs.getInt("id")) {
			        case 2:
			            objs.add(new WallCorner(rs.getInt("i"), rs.getInt("j"), rs.getInt("rotation_angle"), rs
			                    .getString("path")));
			            break;
			        case 1:
			            objs.add(new Wall(rs.getInt("i"), rs.getInt("j"), rs.getInt("rotation_angle"), rs.getString("path")));
			            break;
			        case 5:
			            objs.add(new Desk(rs.getInt("i"), rs.getInt("j"), rs.getInt("rotation_angle"), rs.getString("path")));
			            break;
			        case 15:
			            objs.add(new Desk(rs.getInt("i"), rs.getInt("j"), rs.getInt("rotation_angle"), rs.getString("path")));
			            break;
			        default: logger.error("Can't add passive objects to wrapper using ID='"+rs.getInt("id")+"'");
			    }

			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Can't execute Result Set using method rs.getInt(\"id\")");
		}

        return objs;
    }
}
