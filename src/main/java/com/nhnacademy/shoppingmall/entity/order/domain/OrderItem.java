package com.nhnacademy.shoppingmall.entity.order.domain;

public class OrderItem {
    private int orderItemId;
    private int orderId;
    private int orderProductId;
    private int quantity;
    private int itemPrice;

    public OrderItem() {}

    public OrderItem(int orderItemId, int orderId, int orderProductId, int quantity, int itemPrice) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.orderProductId = orderProductId;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    // getters & setters
    public int getOrderItemId() {
        return orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getOrderProductId() {
        return orderProductId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setOrderProductId(int productId) {
        this.orderProductId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }
}
