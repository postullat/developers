package com.epam.lab.developers.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	private String getTeamObjsById = "SELECT team_object.rotation_angle, team_object.i, team_object.j, object.path, object.name, object.id "
			+ "FROM team_object INNER JOIN object ON team_object.id_team = ? "
			+ "AND team_object.id_object = object.id ORDER BY team_object.i, team_object.j";
	
			
	@Override
	public List<ActiveObject> getAllByTeamId(int id) throws SQLException {	
		
		PreparedStatement	stat = conn.prepareStatement(getTeamObjsById);
		stat.setInt(1, id);
		ResultSet rs = stat.executeQuery();
		
		return createObjectList(rs);
	}

	@Override
	public List<ActiveObject> getAllByMapId(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private List<ActiveObject> createObjectList(ResultSet rs) throws SQLException{
		List<ActiveObject> objs = new ArrayList<>();
		while(rs.next()){

			//objs.add(new CoffeMashine(rs.getInt("i"), rs.getInt("j"), rs.getInt("rotation_angle"), rs.getString("path")));
			
			switch(rs.getInt("id")){
			case 6 : 
				objs.add(new Notebook(rs.getInt("i"), rs.getInt("j"), rs.getInt("rotation_angle"), rs.getString("path")));
				break;
			case 7 : 
				objs.add(new Printer(rs.getInt("i"), rs.getInt("j"), rs.getInt("rotation_angle"), rs.getString("path")));
				break;
			case 9:
				objs.add(new Sink(rs.getInt("i"), rs.getInt("j"), rs.getInt("rotation_angle"), rs.getString("path")));
				break;
			case 10:
				objs.add(new Toilet(rs.getInt("i"), rs.getInt("j"), rs.getInt("rotation_angle"), rs.getString("path")));
				break;
			case 11:
				objs.add(new CookStove(rs.getInt("i"), rs.getInt("j"), rs.getInt("rotation_angle"), rs.getString("path")));
				break;
			case 12:
				objs.add(new Refrigerator(rs.getInt("i"), rs.getInt("j"), rs.getInt("rotation_angle"), rs.getString("path")));
				break;
			case 13:
				objs.add(new Server(rs.getInt("i"), rs.getInt("j"), rs.getInt("rotation_angle"), rs.getString("path")));
				break;
			case 14:
				objs.add(new CoffeeMashine(rs.getInt("i"), rs.getInt("j"), rs.getInt("rotation_angle"), rs.getString("path")));
				break;
				
			}
		}
		return objs;
	}

}
