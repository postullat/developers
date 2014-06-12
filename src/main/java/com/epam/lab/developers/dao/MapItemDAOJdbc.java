package com.epam.lab.developers.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class MapItemDAOJdbc<T> extends JdbcConnection {

    protected static Connection conn = getConnection();

    public abstract List<T> getAllByTeamId(int id) throws SQLException;

    public abstract List<T> getAllByMapId(int id) throws SQLException;

}
