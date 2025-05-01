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
    public void saveCategory(Category category) {
        if(isExist(category.getCategoryId())) {
            throw new IllegalStateException("이미 존재하는 카테고리 입니다.");
        }

        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Category category) {
        if(!isExist(category.getCategoryId())) {
            throw new IllegalStateException("존재하지 않는 카테고리 입니다.");
        }

        categoryRepository.update(category);
    }

    @Override
    public void deleteCategory(int categoryId) {
        if(!isExist(categoryId)) {
            throw new IllegalStateException("존재하지 않는 카테고리 입니다.");
        }

        categoryRepository.deleteByCategoryId(categoryId);
    }

    private boolean isExist(int categoryId) {
        return categoryRepository.countByCategoryId(categoryId) > 0;
    }
}
