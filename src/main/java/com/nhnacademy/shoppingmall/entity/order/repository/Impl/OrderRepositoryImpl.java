package com.nhnacademy.shoppingmall.entity.order.repository.Impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.entity.order.domain.Order;
import com.nhnacademy.shoppingmall.entity.order.domain.OrderItem;
import com.nhnacademy.shoppingmall.entity.order.repository.OrderRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public int saveOrder(Order order) {
        Connection conn = DbConnectionThreadLocal.getConnection();

        String sql = """
                        INSERT INTO orders(user_id, total_price, order_created_at)
                        VALUES (?, ?, ?)
                     """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, order.getUserId());
            pstmt.setInt(2, order.getTotalPrice());
            pstmt.setTimestamp(3, new Timestamp(order.getOrderCreatedAt().getTime()));

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveOrderItems(List<OrderItem> orderItems) {
        Connection conn = DbConnectionThreadLocal.getConnection();

        String sql = """
                        INSERT INTO order_items(order_id, product_id, quantity, item_price)
                        VALUES (?, ?, ?, ?)
                     """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (OrderItem item : orderItems) {
                pstmt.setInt(1, item.getOrderId());
                pstmt.setInt(2, item.getOrderProductId());
                pstmt.setInt(3, item.getQuantity());
                pstmt.setInt(4, item.getItemPrice());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<Order> findByOrderId(int orderId) {
        Connection conn = DbConnectionThreadLocal.getConnection();

        String sql = "SELECT * FROM orders WHERE order_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Order order = new Order(
                            rs.getInt("order_id"),
                            rs.getString("user_id"),
                            rs.getInt("total_price"),
                            rs.getTimestamp("order_created_at")
                    );
                    return Optional.of(order);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public List<Order> findByUserIdWithItems(String userId) {
        Connection conn = DbConnectionThreadLocal.getConnection();

        String sql = "SELECT * FROM orders WHERE user_id = ? ORDER BY order_created_at DESC";
        List<Order> orders = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order(
                            rs.getInt("order_id"),
                            rs.getString("user_id"),
                            rs.getInt("total_price"),
                            rs.getTimestamp("order_created_at")
                    );

                    List<OrderItem> orderItems = findItemsByOrderId(order.getOrderId());
                    order.setOrderItems(orderItems);

                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orders;
    }

    @Override
    public List<OrderItem> findItemsByOrderId(int orderId) {
        Connection conn = DbConnectionThreadLocal.getConnection();

        String sql = "SELECT * FROM order_items WHERE order_id = ?";
        List<OrderItem> items = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    OrderItem item = new OrderItem(
                            rs.getInt("order_item_id"),
                            rs.getInt("order_id"),
                            rs.getInt("product_id"),
                            rs.getInt("quantity"),
                            rs.getInt("item_price")
                    );
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return items;
    }
}
