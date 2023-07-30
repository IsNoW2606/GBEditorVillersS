package org.helmo.gbeditor.repository;

import org.helmo.gbeditor.repository.exception.ConnectionFailedException;
import org.helmo.gbeditor.repository.exception.JdbcDriverNotFoundException;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBRepositoryFactory {
    public DBRepositoryFactory() throws JdbcDriverNotFoundException {
        try {
            Class.forName(DBConfig.DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            throw new JdbcDriverNotFoundException(DBConfig.DRIVER_NAME);
        }
    }

    public DBRepository newDBSession() throws ConnectionFailedException {
        try {
            return new DBRepository(DriverManager.getConnection(DBConfig.DB, DBConfig.USERNAME, DBConfig.PASSWORD));
        } catch (SQLException e) {
            throw new ConnectionFailedException("Unable to access db : " + DBConfig.DB, e);
        }
    }
}