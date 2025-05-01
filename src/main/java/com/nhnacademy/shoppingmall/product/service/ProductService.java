package com.nhnacademy.shoppingmall.product.service;

import com.nhnacademy.shoppingmall.product.domain.Product;

import java.util.List;

public interface ProductService {
    Product getProduct(int productId);
    List<Product> getProductList();
    void saveProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Product product);
}
