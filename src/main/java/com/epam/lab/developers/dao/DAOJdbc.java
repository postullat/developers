package com.epam.lab.developers.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.epam.lab.developers.setting.PropertyUtil;
import com.epam.lab.developers.setting.Settings;

public abstract class DAOJdbc<T> extends JdbcConnection {


    protected static Connection conn = getConnection();

    public abstract T getById(int id) throws SQLException;

    public abstract T getByName(String name) throws SQLException;

    public abstract int insert(T entity);

    public abstract int update(T entity);
}
