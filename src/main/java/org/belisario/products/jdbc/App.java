package org.belisario.products.jdbc;

import org.belisario.products.jdbc.util.DatabaseConnection;

import java.sql.*;

public class App {
    public static void main(String[] args) {

        try (Connection conn = DatabaseConnection.getConnectionInstance();
             Statement stmt = conn.createStatement();
             ResultSet result = stmt.executeQuery("SELECT * FROM products")) {
            // using try with resources
            // in this case Jav will autoclose the connection and the other resources
            while (result.next()) {
                System.out.print(result.getInt("id"));
                System.out.print(" | ");
                System.out.print(result.getString("name"));
                System.out.print(" | ");
                System.out.print(result.getDouble("price"));
                System.out.print(" | ");
                System.out.println(result.getDate("register_date"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}