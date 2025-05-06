package com.nhnacademy.shoppingmall.entity.order.service;

import com.nhnacademy.shoppingmall.entity.order.domain.Order;

import java.util.List;

public interface OrderService {
    int createOrder(Order order);
    List<Order> getOrdersByUserId(String userId);
}
