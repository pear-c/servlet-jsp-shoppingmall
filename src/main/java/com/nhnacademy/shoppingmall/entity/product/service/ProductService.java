package com.nhnacademy.shoppingmall.entity.product.service;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.entity.product.domain.Product;

import java.util.List;

public interface ProductService {
    Product getProduct(int productId);
    List<Product> getProductList();
    List<Product> getProductListWithCategory();
    List<Product> getProductListByCategory(int categoryId);
    void saveProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(int productId);
    // 페이징
    Page<Product> getAllProductPage(int offset, int limit);
    Page<Product> getProductPageByCategory(int categoryId, int offset, int limit);
}
