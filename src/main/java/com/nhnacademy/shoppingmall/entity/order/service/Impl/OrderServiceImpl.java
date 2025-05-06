package com.nhnacademy.shoppingmall.entity.order.service.Impl;

import com.nhnacademy.shoppingmall.entity.order.domain.Order;
import com.nhnacademy.shoppingmall.entity.order.domain.OrderItem;
import com.nhnacademy.shoppingmall.entity.order.repository.OrderRepository;
import com.nhnacademy.shoppingmall.entity.order.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public int createOrder(Order order) {
        int orderId = orderRepository.saveOrder(order);

        for (OrderItem item : order.getOrderItems()) {
            item.setOrderId(orderId); // FK 설정
        }

        orderRepository.saveOrderItems(order.getOrderItems());
        return orderId;
    }

    @Override
    public List<Order> getOrdersByUserId(String userId) {
        return orderRepository.findByUserIdWithItems(userId);
    }

}
