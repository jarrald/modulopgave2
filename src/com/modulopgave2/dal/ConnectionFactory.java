package com.modulopgave2.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionFactory {
    public static final String URL = "jdbc:mysql://localhost:3306/";
    public static final String USER = "testuser";
    public static final String PASS = "test";

    /**
     * Get a connection to database
     *
     * @return Connection object
     */
    public static Connection getConnection(String databaseName) throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user", USER);
        properties.setProperty("password", PASS);
        properties.setProperty("useSSL", "false");
        properties.setProperty("autoReconnect", "true");

        return DriverManager.getConnection(URL + databaseName, properties);
    }
}
