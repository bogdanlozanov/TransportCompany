package com.transportcompany.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/transport_company_db";
    private static final String USER = "root";
    private static final String PASSWORD = "parolata";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
