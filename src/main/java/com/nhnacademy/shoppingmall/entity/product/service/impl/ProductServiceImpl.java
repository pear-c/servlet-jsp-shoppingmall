package com.nhnacademy.shoppingmall.entity.product.service.impl;

import com.nhnacademy.shoppingmall.entity.product.domain.Product;
import com.nhnacademy.shoppingmall.entity.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.entity.product.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProduct(int productId) {
        return productRepository.findByProductId(productId).orElse(null);
    }

    @Override
    public List<Product> getProductList() {
        return productRepository.findAllProducts();
    }

    @Override
    public List<Product> getProductListWithCategory() {
        return productRepository.findAllWithCategory();
    }

    @Override
    public List<Product> getProductListByCategory(int categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        if(!isExist(product.getProductId())) {
            throw new IllegalStateException("해당 상품 아이디는 없는 상품입니다.");
        }
        productRepository.update(product);
    }

    @Override
    public void deleteProduct(int productId) {
        if(!isExist(productId)) {
            throw new IllegalStateException("해당 상품 아이디는 없는 상품입니다.");
        }
        productRepository.deleteByProductId(productId);
    }

    private boolean isExist(int productId) {
        return productRepository.countByProductId(productId) > 0;
    }
}
