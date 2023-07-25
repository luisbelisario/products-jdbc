package org.belisario.products.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String url = "jdbc:mysql://localhost:3306/java-ee-course?serverTimezone=UTC";
    private static final String username = "root";
    private static final String password = "root";
    private static Connection connection;

    public static Connection getConnectionInstance() throws SQLException {
        if(connection == null) {
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }
}
