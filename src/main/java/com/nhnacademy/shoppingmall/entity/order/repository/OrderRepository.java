package com.nhnacademy.shoppingmall.entity.order.repository;

import com.nhnacademy.shoppingmall.entity.order.domain.Order;
import com.nhnacademy.shoppingmall.entity.order.domain.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    int saveOrder(Order order); // Order 저장
    void saveOrderItems(List<OrderItem> orderItems); // OrderItem 저장
    Optional<Order> findByOrderId(int orderId);
    List<Order> findByUserIdWithItems(String userId);
    List<OrderItem> findItemsByOrderId(int orderId);
}
