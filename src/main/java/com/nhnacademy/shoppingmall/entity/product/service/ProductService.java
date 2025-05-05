package com.nhnacademy.shoppingmall.entity.product.service;

import com.nhnacademy.shoppingmall.entity.product.domain.Product;

import java.util.List;

public interface ProductService {
    Product getProduct(int productId);
    List<Product> getProductList();
    List<Product> getProductListWithCategory();
    List<Product> getProductListByCategory(int categoryId);
    void saveProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Product product);
}
