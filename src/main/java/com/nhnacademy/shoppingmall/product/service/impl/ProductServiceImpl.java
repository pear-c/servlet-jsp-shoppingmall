package com.nhnacademy.shoppingmall.product.service.impl;

import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.product.service.ProductService;

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
    public void deleteProduct(Product product) {
        if(!isExist(product.getProductId())) {
            throw new IllegalStateException("해당 상품 아이디는 없는 상품입니다.");
        }
        productRepository.deleteByProductId(product.getProductId());
    }

    private boolean isExist(int productId) {
        return productRepository.countByProductId(productId) > 0;
    }
}
