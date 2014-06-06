package com.epam.lab.developers.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.epam.lab.developers.setting.PropertyUtil;
import com.epam.lab.developers.setting.Settings;

public abstract class MapItemDAOJdbc<T> extends JdbcConnection {

    protected static Connection conn = getConnection();

    public abstract List<T> getAllByTeamId(int id) throws SQLException;

    public abstract List<T> getAllByMapId(int id) throws SQLException;

}
