package com.nhnacademy.shoppingmall.entity.order.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Order {
    private int orderId;
    private String userId;
    private int totalPrice;
    private Date orderCreatedAt;
    // 주문 상세 목록
    private List<OrderItem> orderItems;

    public Order() {}

    public Order(int orderId, String userId, int totalPrice, Date orderCreatedAt) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.orderCreatedAt = orderCreatedAt;
    }

    // getters & setters
    public int getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Date getOrderCreatedAt() {
        return orderCreatedAt;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setOrderCreatedAt(Date orderCreatedAt) {
        this.orderCreatedAt = orderCreatedAt;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
