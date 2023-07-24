package org.belisario.products.jdbc;

import java.sql.*;

public class JdbcConnection {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/java-ee-course?serverTimezone=UTC";
        String username = "root";
        String password = "root";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM products");

            while (result.next()) {
                System.out.println(result.getString("name"));
            }
            result.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}