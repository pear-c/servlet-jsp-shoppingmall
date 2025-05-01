package com.nhnacademy.shoppingmall.entity.category.service;

import com.nhnacademy.shoppingmall.entity.category.domain.Category;

import java.util.List;

public interface CategoryService {
    Category getCategory(int categoryId);
    List<Category> getCategoryList();
    void saveCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(int categoryId);
}
