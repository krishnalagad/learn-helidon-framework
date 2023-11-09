package com.learnhelidon.project.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(AppConstants.DB_URL, AppConstants.DB_USERNAME, AppConstants.DB_PASSWORD);
    }
}