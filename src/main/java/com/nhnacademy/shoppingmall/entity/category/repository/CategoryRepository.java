package com.nhnacademy.shoppingmall.entity.category.repository;

import com.nhnacademy.shoppingmall.entity.category.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Optional<Category> findByCategoryId(int categoryId);
    List<Category> findAllCategories();
    int save(String categoryName);
    int update(Category category);
    int deleteByCategoryId(int categoryId);
    int countByCategoryId(int categoryId);
    int countByCategoryName(String categoryName);
}
