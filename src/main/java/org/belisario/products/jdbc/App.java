package org.belisario.products.jdbc;

import org.belisario.products.jdbc.model.Product;
import org.belisario.products.jdbc.repository.ProductRepositoryImpl;
import org.belisario.products.jdbc.repository.Repository;
import org.belisario.products.jdbc.util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class App {
    public static void main(String[] args) {

        try (Connection conn = DatabaseConnection.getConnectionInstance()) {
            // Using try with resources
            // In this case Java will autoclose the connection and the other resources
            // I´m still using this here so Java will autoclose this connection ate the end of the process
            Repository<Product> productRepository = new ProductRepositoryImpl();

            // Get and print all products in the database
            System.out.println("FIND ALL ----------------------");
            List<Product> products = productRepository.findAll();
            products.forEach(Product::printProductName);

            System.out.println("\nFIND BY ID ----------------------");
            // Get by id and print one product in the database

            Product product = productRepository.findById(2L);

            Product.printProductName(product);

            System.out.println("\nINSERT ONE ----------------------");

            Product productToInsert = new Product(null, "Stanley Cup", 40.00, new Date());

            // productRepository.save(productToInsert);
            List<Product> newProducts = productRepository.findAll();
            newProducts.forEach(Product::printProductName);

            System.out.println("\nDELETE BY ID ----------------------");
            productRepository.delete(4L);
            List<Product> newProducts2 = productRepository.findAll();
            newProducts2.forEach(Product::printProductName);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}