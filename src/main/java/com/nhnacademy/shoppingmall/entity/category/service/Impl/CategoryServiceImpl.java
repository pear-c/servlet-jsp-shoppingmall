package com.nhnacademy.shoppingmall.entity.category.service.Impl;

import com.nhnacademy.shoppingmall.entity.category.domain.Category;
import com.nhnacademy.shoppingmall.entity.category.repository.CategoryRepository;
import com.nhnacademy.shoppingmall.entity.category.repository.Impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.entity.category.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategory(int categoryId) {
        return categoryRepository.findByCategoryId(categoryId).orElse(null);
    }

    @Override
    public List<Category> getCategoryList() {
        return categoryRepository.findAllCategories();
    }

    @Override
    public void saveCategoryByName(String categoryName) {
        if(isExist(categoryName)) {
            throw new RuntimeException();
        }

        categoryRepository.save(categoryName);
    }

    @Override
    public void updateCategory(Category category) {
        if(!isExist(category.getCategoryName())) {
            throw new RuntimeException();
        }

        categoryRepository.update(category);
    }

    @Override
    public void deleteCategory(int categoryId) {
        if(!isExist(categoryId)) {
            throw new RuntimeException();
        }

        categoryRepository.deleteByCategoryId(categoryId);
    }

    private boolean isExist(String categoryName) {
        return categoryRepository.countByCategoryName(categoryName) > 0;
    }

    private boolean isExist(int categoryId) {
        return categoryRepository.countByCategoryId(categoryId) > 0;
    }
}
