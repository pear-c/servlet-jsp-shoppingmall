package com.nhnacademy.shoppingmall.entity.category.service;

import com.nhnacademy.shoppingmall.entity.category.domain.Category;

import java.util.List;

public interface CategoryService {
    Category getCategory(int categoryId);
    List<Category> getCategoryList();
    void saveCategoryByName(String categoryName);
    void updateCategory(Category category);
    void deleteCategory(int categoryId);
}
