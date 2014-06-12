package com.epam.lab.developers.dao;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAOJdbc<T> extends JdbcConnection {


    protected static Connection conn = getConnection();

    public abstract T getById(int id) throws SQLException;

    public abstract T getByName(String name) throws SQLException;

    public abstract int insert(T entity);

    public abstract int update(T entity);
}
