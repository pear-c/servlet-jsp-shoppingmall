package com.nhnacademy.shoppingmall.entity.product.repository;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.entity.product.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    int save(Product product);
    Optional<Product> findByProductId(int productId);
    List<Product> findAllProducts();
    List<Product> findByIds(List<Integer> productIds);
    List<Product> findAllWithCategory();
    List<Product> findByCategoryId(int categoryId);
    int deleteByProductId(int productId);
    int update(Product product);
    int countByProductId(int productId);
    // 페이징
    Page<Product> findAllPaged(int offset, int limit);
    Page<Product> findByCategoryIdPaged(int categoryId, int offset, int limit);
}
