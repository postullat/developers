package com.epam.lab.developers.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.lab.developers.dao.ActiveObjectDAO;
import com.epam.lab.developers.dao.MapDAO;
import com.epam.lab.developers.dao.PassiveObjectDAO;
import com.epam.lab.developers.dao.UnitDAO;
import com.epam.lab.developers.game.Team;
import com.epam.lab.developers.game.map.GameMap;
import com.epam.lab.developers.game.map.object.MapObject;
import com.epam.lab.developers.game.map.object.active.ActiveObject;
import com.epam.lab.developers.game.map.object.active.Server;
import com.epam.lab.developers.game.map.object.passive.Floor;
import com.epam.lab.developers.game.map.unit.Unit;

public class MapManager {

	private MapDAO mapDAO = new MapDAO();
	
	private PassiveObjectDAO pObjectDAO = new PassiveObjectDAO();
	private ActiveObjectDAO aObjectDAO = new ActiveObjectDAO();
	private UnitDAO unitDAO = new UnitDAO();
	
	private GameMap gameMap;
	private List<Team> mapTeams;
	
	
	public MapManager(int mapId) throws SQLException{
		initMapItems(mapId);
	}
	
	public MapManager(String mapName) throws SQLException{
		
		initMapItems(mapDAO.getMapID(mapName));
	}
	
	public GameMap getMap(){
		return gameMap;
	}
	
	public List<Team> getTeams(){
		return mapTeams;
	}
	
	private void initMapItems(int mapId) throws SQLException{
		this.gameMap = mapDAO.getById(mapId);
		
		
		
		List<MapObject> pObjs = pObjectDAO.getAllByMapId(mapId);

		
		for (int i = 0; i < pObjs.size(); i++){
			gameMap.setMapObjects(pObjs.get(i));
		}
		
		
		List<Integer> teamIdList = mapDAO.getTeams(mapId);
		this.mapTeams = new ArrayList<Team>();
		
		for(int i=0;i<teamIdList.size();i++){
			
			List<ActiveObject> aObjs = aObjectDAO.getAllByTeamId(teamIdList.get(i));
			List<Unit> units = unitDAO.getAllByTeamId(teamIdList.get(i));
			Team tmpTeam =new Team(teamIdList.get(i),aObjs,units); 
			mapTeams.add(tmpTeam);
			
			for (int j = 0; j < aObjs.size(); j++){
				if (aObjs.get(j) instanceof Server) ((Server)aObjs.get(j)).setTeam(tmpTeam);
				gameMap.setMapObjects(aObjs.get(j));
			}
			
		}
		
		System.out.println(gameMap.getRows() +"     "+gameMap.getColumns());
		String floorTexture = mapDAO.getMapFloorTexture(mapId);
		
		for(int i=0;i<gameMap.getRows();i++)
			for(int j=0;j<gameMap.getColumns();j++){
				
				if(gameMap.getMapObjects()[i][j] == null)
					gameMap.getMapObjects()[i][j] = new Floor(i, j, 0, floorTexture);
			}
		
		
	}
	
}
