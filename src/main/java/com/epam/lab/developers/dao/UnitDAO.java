package com.epam.lab.developers.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.epam.lab.developers.game.map.unit.JuniorDeveloper;
import com.epam.lab.developers.game.map.unit.MiddleDeveloper;
import com.epam.lab.developers.game.map.unit.SeniorDeveloper;
import com.epam.lab.developers.game.map.unit.Unit;

public class UnitDAO extends MapItemDAOJdbc<Unit> {

	private String getTeamUnitsById = "SELECT team_unit.id, team_unit.id_unit, team_unit.rotation_angle, team_unit.i, team_unit.j," 
	        +" unit.stand_texture, unit.texture1, unit.texture2, unit.texture3, unit.texture4, unit.name "
			+ "FROM team_unit INNER JOIN unit ON team_unit.id_team = ? "
			+ "AND team_unit.id_unit = unit.id ORDER BY team_unit.i, team_unit.j";
	
	
	@Override
	public List<Unit> getAllByTeamId(int id) throws SQLException {
		
		PreparedStatement	stat = conn.prepareStatement(getTeamUnitsById);
		stat.setInt(1, id);
		ResultSet rs = stat.executeQuery();
		
		return createUnitList(rs);
	}

	@Override
	public List<Unit> getAllByMapId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Unit> createUnitList(ResultSet rs) throws SQLException{
		List<Unit> units = new ArrayList<>();
		
		while(rs.next()){
			
			//switch(rs.getInt("id_unit")){
			//case 0:
			if(rs.getInt("id_unit") == 0 | rs.getInt("id_unit") == 3)
				units.add(new JuniorDeveloper(rs.getInt("id"),rs.getInt("i"), rs.getInt("j"), rs.getInt("rotation_angle"),
						Arrays.asList(rs.getString("stand_texture"),rs.getString("texture1"),rs.getString("texture2"),
						rs.getString("texture3"),rs.getString("texture4")),rs.getString("name")));
				
			if(rs.getInt("id_unit") == 1 | rs.getInt("id_unit") == 4)
				units.add(new MiddleDeveloper(rs.getInt("id"),rs.getInt("i"), rs.getInt("j"), rs.getInt("rotation_angle"),
						Arrays.asList(rs.getString("stand_texture"),rs.getString("texture1"),rs.getString("texture2"),
						rs.getString("texture3"),rs.getString("texture4")),rs.getString("name")));
				
			if(rs.getInt("id_unit") == 2 | rs.getInt("id_unit") == 5)
				units.add(new SeniorDeveloper(rs.getInt("id"),rs.getInt("i"), rs.getInt("j"), rs.getInt("rotation_angle"),
						Arrays.asList(rs.getString("stand_texture"),rs.getString("texture1"),rs.getString("texture2"),
						rs.getString("texture3"),rs.getString("texture4")),rs.getString("name")));
				
			
			
		}
		
		
		return units;
	}
	
}
