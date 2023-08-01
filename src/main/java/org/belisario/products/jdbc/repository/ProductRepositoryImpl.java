package org.belisario.products.jdbc.repository;

import org.belisario.products.jdbc.model.Product;
import org.belisario.products.jdbc.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements Repository<Product> {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getConnectionInstance();
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();

        try(Statement stmt = getConnection().createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM products")) {
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setRegisterDate(resultSet.getDate("register_date"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void delet(Long id) {

    }
}
