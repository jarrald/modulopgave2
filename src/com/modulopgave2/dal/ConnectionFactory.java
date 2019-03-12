package com.modulopgave2.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/";
    public static final String USER = "root";
    public static final String PASS = "";

    /**
     * Get a connection to database
     *
     * @return Connection object
     */
    public static Connection getConnection(String databaseName) throws SQLException {
        return DriverManager.getConnection(URL + databaseName, USER, PASS);
    }
}
