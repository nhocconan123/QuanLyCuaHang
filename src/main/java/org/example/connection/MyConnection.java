package org.example.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MyConnection {
    private static final String DB_USER="root";
    private static final String BD_PASSWORD="123456";

    private static final String DB_CONNECTION_URL="jdbc:mysql://localhost:3306/demo_jdbc";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION_URL,DB_USER,BD_PASSWORD);
    }
}
