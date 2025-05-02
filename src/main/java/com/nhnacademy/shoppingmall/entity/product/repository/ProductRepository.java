package com.nhnacademy.shoppingmall.entity.product.repository;

import com.nhnacademy.shoppingmall.entity.product.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    int save(Product product);
    Optional<Product> findByProductId(int productId);
    List<Product> findAllProducts();
    List<Product> findAllWithCategory();
    List<Product> findByCategoryId(int categoryId);
    int deleteByProductId(int productId);
    int update(Product product);
    int countByProductId(int productId);
}
