package com.epam.lab.developers.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.lab.developers.game.map.object.MapObject;
import com.epam.lab.developers.game.map.object.passive.Desk;
import com.epam.lab.developers.game.map.object.passive.Wall;
import com.epam.lab.developers.game.map.object.passive.WallCorner;

public class PassiveObjectDAO extends MapItemDAOJdbc<MapObject> {

    private String getObjectsByMapId = "SELECT passive_object.rotation_angle, passive_object.i, passive_object.j,"
            + "object.path, object.name, object.id FROM passive_object INNER JOIN object ON passive_object.id_map = ? "
            + "AND passive_object.id_object = object.id ORDER BY passive_object.i, passive_object.j";

    @Override
    public List<MapObject> getAllByTeamId(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<MapObject> getAllByMapId(int id) throws SQLException {
        PreparedStatement stat = conn.prepareStatement(getObjectsByMapId);
        stat.setInt(1, id);
        ResultSet rs = stat.executeQuery();

        return createObjectList(rs);
    }

    private List<MapObject> createObjectList(ResultSet rs) throws SQLException {
        List<MapObject> objs = new ArrayList<>();
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
            }

        }

        return objs;
    }
}
