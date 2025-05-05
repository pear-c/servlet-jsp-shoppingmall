package com.nhnacademy.shoppingmall.controller.index;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;

import com.nhnacademy.shoppingmall.entity.category.domain.Category;
import com.nhnacademy.shoppingmall.entity.category.repository.Impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.entity.category.service.CategoryService;
import com.nhnacademy.shoppingmall.entity.category.service.Impl.CategoryServiceImpl;
import com.nhnacademy.shoppingmall.entity.product.domain.Product;
import com.nhnacademy.shoppingmall.entity.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.entity.product.service.ProductService;
import com.nhnacademy.shoppingmall.entity.product.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

@RequestMapping(method = RequestMapping.Method.GET,value = {"/index.do"})
public class IndexController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String categoryIdParam = req.getParameter("category_id");

        // 카테고리 별 상품 목록
        List<Product> productList;
        if(categoryIdParam != null && !categoryIdParam.isEmpty()) {
            int categoryId = Integer.parseInt(categoryIdParam);
            productList = productService.getProductListByCategory(categoryId);
        } else {
            productList = productService.getProductListWithCategory();
        }
        req.setAttribute("productList", productList);

        // 카테고리 리스트
        List<Category> categoryList = categoryService.getCategoryList();
        req.setAttribute("categoryList", categoryList);

        return "shop/main/index";
    }
}