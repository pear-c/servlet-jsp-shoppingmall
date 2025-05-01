package com.nhnacademy.shoppingmall.product.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public int save(Product product) {
        Connection conn = DbConnectionThreadLocal.getConnection();

        String sql = """
                        INSERT INTO products(category_id, product_name, product_price, product_created_at, product_image_path, product_explain)
                        VALUES(?, ?, ?, ?, ?, ?)
                     """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, product.getCategoryId());
            pstmt.setString(2, product.getProductName());
            pstmt.setInt(3, product.getProductPrice());
            pstmt.setTimestamp(4, Timestamp.valueOf(product.getCreatedAt()));
            if(!product.getImagePath().isEmpty() && Objects.nonNull(product.getImagePath())) {
                pstmt.setString(5, product.getImagePath());
            } else {
                pstmt.setNull(5, Types.VARCHAR);
            }
            pstmt.setString(6, product.getExplain());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Product> findByProductId(int productId) {
        Connection conn = DbConnectionThreadLocal.getConnection();

        String sql = "SELECT * FROM products WHERE product_id = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, productId);

            try(ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()) {
                    Product product = new Product(
                            rs.getInt("product_id"),
                            rs.getInt("category_id"),
                            rs.getString("product_name"),
                            rs.getInt("product_price"),
                            rs.getTimestamp("product_created_at").toLocalDateTime(),
                            rs.getString("product_image_path"),
                            rs.getString("product_explain")
                    );
                    return Optional.of(product);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Product> findAllProducts() {
        Connection conn = DbConnectionThreadLocal.getConnection();

        String sql = "SELECT * FROM products";

        List<Product> productList = new ArrayList<>();

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {

            try(ResultSet rs = pstmt.executeQuery()) {
                while(rs.next()) {
                    Product product = new Product(
                            rs.getInt("product_id"),
                            rs.getInt("category_id"),
                            rs.getString("product_name"),
                            rs.getInt("product_price"),
                            rs.getTimestamp("product_created_at").toLocalDateTime(),
                            rs.getString("product_image_path"),
                            rs.getString("product_explain")
                    );
                    productList.add(product);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    @Override
    public List<Product> findByCategoryId(int categoryId) {
        Connection conn = DbConnectionThreadLocal.getConnection();

        String sql = "SELECT * FROM products WHERE category_id = ?";

        List<Product> productList = new ArrayList<>();
        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, categoryId);

            try(ResultSet rs = pstmt.executeQuery()) {
                while(rs.next()) {
                    Product product = new Product(
                            rs.getInt("product_id"),
                            rs.getInt("category_id"),
                            rs.getString("product_name"),
                            rs.getInt("product_price"),
                            rs.getTimestamp("product_created_at").toLocalDateTime(),
                            rs.getString("product_image_path"),
                            rs.getString("product_explain")
                    );
                    productList.add(product);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    @Override
    public int deleteByProductId(int productId) {
        Connection conn = DbConnectionThreadLocal.getConnection();

        String sql = """
                         DELETE FROM products
                         WHERE product_id = ?
                     """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, productId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Product product) {
        Connection conn = DbConnectionThreadLocal.getConnection();

        String sql = """
                         UPDATE products
                         SET category_id = ?, product_name = ?, product_price = ?, product_image_path = ?, product_explain = ?
                         WHERE product_id = ?
                     """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, product.getCategoryId());
            pstmt.setString(2, product.getProductName());
            pstmt.setInt(3, product.getProductPrice());
            pstmt.setString(4, product.getImagePath());
            if(!product.getImagePath().isEmpty() && Objects.nonNull(product.getImagePath())) {
                pstmt.setString(5, product.getImagePath());
            } else {
                pstmt.setNull(5, Types.VARCHAR);
            }
            pstmt.setString(6, product.getExplain());
            pstmt.setInt(7, product.getProductId());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByProductId(int productId) {
        Connection conn = DbConnectionThreadLocal.getConnection();

        String sql = "SELECT COUNT(*) FROM products WHERE product_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);

            try(ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
