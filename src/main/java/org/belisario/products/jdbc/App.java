package org.belisario.products.jdbc;

import org.belisario.products.jdbc.model.Product;
import org.belisario.products.jdbc.repository.ProductRepositoryImpl;
import org.belisario.products.jdbc.repository.Repository;
import org.belisario.products.jdbc.util.DatabaseConnection;

import java.sql.*;
import java.util.List;

public class App {
    public static void main(String[] args) {

        try (Connection conn = DatabaseConnection.getConnectionInstance();) {
            // using try with resources
            // in this case Java will autoclose the connection and the other resources
            Repository<Product> productRepository = new ProductRepositoryImpl();
            List<Product> products = productRepository.findAll();

            products.forEach(Product::printProductName);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}