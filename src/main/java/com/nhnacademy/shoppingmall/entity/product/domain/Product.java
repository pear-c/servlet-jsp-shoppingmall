package com.nhnacademy.shoppingmall.entity.product.domain;

import java.time.LocalDateTime;

public class Product {
    private int productId;
    private int categoryId;
    private String productName;
    private int productPrice;
    private LocalDateTime createdAt;
    private String imagePath;
    private String explain;

    private String categoryName;    // 카테고리 명 출력용 필드

    public Product(int productId, int categoryId, String productName, int productPrice, LocalDateTime createdAt, String imagePath, String explain) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.createdAt = createdAt;
        this.imagePath = imagePath;
        this.explain = explain;
    }

    public Product(int categoryId, String productName, int productPrice, LocalDateTime createdAt, String imagePath, String explain) {
        this.categoryId = categoryId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.createdAt = createdAt;
        this.imagePath = imagePath;
        this.explain = explain;
    }

    public int getProductId() {
        return productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getExplain() {
        return explain;
    }
    
    public String getCategoryName() {
        return categoryName;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
