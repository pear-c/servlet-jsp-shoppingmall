package com.nhnacademy.shoppingmall.entity.category.repository.Impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.entity.category.domain.Category;
import com.nhnacademy.shoppingmall.entity.category.repository.CategoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryRepositoryImpl implements CategoryRepository {
    @Override
    public Optional<Category> findByCategoryId(int categoryId) {
        Connection conn = DbConnectionThreadLocal.getConnection();

        String sql = "SELECT * FROM categories WHERE category_id = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, categoryId);

            try(ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()) {
                    Category category = new Category(
                            rs.getInt("category_id"),
                            rs.getString("category_name")
                    );
                    return Optional.of(category);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Category> findAllCategories() {
        Connection conn = DbConnectionThreadLocal.getConnection();

        String sql = "SELECT * FROM categories";

        List<Category> categoryList = new ArrayList<>();
        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {

            try(ResultSet rs = pstmt.executeQuery()) {
                while(rs.next()) {
                    Category category = new Category(
                            rs.getInt("category_id"),
                            rs.getString("category_name")
                    );
                    categoryList.add(category);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryList;
    }

    @Override
    public int save(Category category) {
        Connection conn = DbConnectionThreadLocal.getConnection();

        String sql = """
                        INSERT INTO categoris(category_name)
                        VALUES(?)
                     """;

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, category.getCategoryName());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Category category) {
        Connection conn = DbConnectionThreadLocal.getConnection();

        String sql = """
                         UPDATE categories
                         SET category_name = ?
                         WHERE category_id = ?
                     """;

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, category.getCategoryName());
            pstmt.setInt(2, category.getCategoryId());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByCategoryId(int categoryId) {
        Connection conn = DbConnectionThreadLocal.getConnection();

        String sql = """
                        DELETE FROM categories
                        WHERE category_id = ?
                     """;

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, categoryId);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByCategoryId(int categoryId) {
        Connection conn = DbConnectionThreadLocal.getConnection();

        String sql = "SELECT COUNT(*) FROM categories WHERE category_id = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, categoryId);

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

    @Override
    public int countByCategoryName(String categoryName) {
        Connection conn = DbConnectionThreadLocal.getConnection();

        String sql = "SELECT COUNT(*) FROM categories WHERE category_name = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, categoryName);

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
