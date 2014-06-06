package com.epam.lab.developers.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.lab.developers.game.map.GameMap;

public class MapDAO extends DAOJdbc<GameMap> {

    private String getMapParametersById = "SELECT * FROM map WHERE id = ?";
    private String getMapIdByName = "SELECT id FROM map WHERE name = ?";
    private String getTeamsByMapId = "SELECT team.id FROM team WHERE id_map = ?";
    private String getFloorByMapId = "SELECT floor_texture FROM map WHERE id = ?";

    @Override
    public GameMap getById(int id) throws SQLException {
        PreparedStatement stat = conn.prepareStatement(getMapParametersById);
        stat.setInt(1, id);
        ResultSet rs = stat.executeQuery();
        rs.next();
        GameMap map = new GameMap(rs.getInt("frame_width"), rs.getInt("frame_height"), rs.getInt("rows"),
                rs.getInt("columns"), rs.getInt("player_count"));

        return map;
    }

    @Override
    public GameMap getByName(String name) throws SQLException {

        int id = getMapID(name);

        return getById(id);
    }

    public int getMapID(String name) throws SQLException {

        PreparedStatement stat = conn.prepareStatement(getMapIdByName);
        stat.setString(1, name);
        ResultSet rs = stat.executeQuery();
        rs.next();
        int id = rs.getInt("id");

        return id;
    }

    public List<Integer> getTeams(int mapId) throws SQLException {
        List<Integer> teams = new ArrayList<>();

        PreparedStatement stat = conn.prepareStatement(getTeamsByMapId);
        stat.setInt(1, mapId);
        ResultSet rs = stat.executeQuery();
        while(rs.next()) {
            teams.add(rs.getInt("id"));
        }

        return teams;
    }

    public String getMapFloorTexture(int id) throws SQLException {
        PreparedStatement stat = conn.prepareStatement(getFloorByMapId);
        stat.setInt(1, id);
        ResultSet rs = stat.executeQuery();
        rs.next();

        return rs.getString("floor_texture");
    }

    @Override
    public int insert(GameMap entity) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(GameMap entity) {
        // TODO Auto-generated method stub
        return 0;
    }

}
