package org.belisario.products.jdbc.repository;

import org.belisario.products.jdbc.model.Product;
import org.belisario.products.jdbc.util.DatabaseConnection;

import java.sql.*;
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
                Product product = toProduct(resultSet);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public Product findById(Long id) {
        Product product = null;

        try(PreparedStatement pstmt = getConnection().prepareStatement("SELECT * FROM products WHERE id = ?")) {
            pstmt.setLong(1, id);
            // If we put rseultSet inside a try with resources block it will autoclose
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    product = toProduct(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    // This method will cover both update and insert
    // The operation will be controlled by the if-else statement
    public void save(Product product) {
        String sql;

        if(product.getId() != null && product.getId() > 0) {
            sql = "UPDATE products SET name = ?, price = ? WHERE id = ?";
        } else {
            sql = "INSERT INTO products (name, price, register_date) VALUES (?, ?, ?)";
        }
        try(PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            if(product.getId() != null && product.getId() > 0) {
                pstmt.setLong(3, product.getId());
            } else {
                pstmt.setDate(3, new Date(product.getRegisterDate().getTime()));
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement pstmt = getConnection().prepareStatement("DELETE from products WHERE id = ?")) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Product toProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getLong("id"));
        product.setName(resultSet.getString("name"));
        product.setPrice(resultSet.getDouble("price"));
        product.setRegisterDate(resultSet.getDate("register_date"));
        return product;
    }
}
