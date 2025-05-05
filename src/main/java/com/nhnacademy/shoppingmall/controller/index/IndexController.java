package com.nhnacademy.shoppingmall.controller.index;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.common.util.CookieUtils;
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

import java.util.*;
import java.util.stream.Collectors;

@RequestMapping(method = RequestMapping.Method.GET,value = {"/index.do"})
public class IndexController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String categoryIdParam = req.getParameter("category_id");
        String pageParm = req.getParameter("page");

        // 페이지네이션
        int page = 1;
        int limit = 9;
        if(pageParm != null && !pageParm.isEmpty()) {
            page = Integer.parseInt(pageParm);
        }
        int offset = (page - 1) * limit;

        Page<Product> productPage;
        if(categoryIdParam != null && !categoryIdParam.isEmpty()) {
            int categoryId = Integer.parseInt(categoryIdParam);
            productPage = productService.getProductPageByCategory(categoryId, offset, limit);
            req.setAttribute("selectedCategoryId", categoryId);
        } else {
            productPage = productService.getAllProductPage(offset, limit);
        }
        req.setAttribute("productPage", productPage);
        req.setAttribute("productList", productPage.getContent());

        // 카테고리 리스트
        List<Category> categoryList = categoryService.getCategoryList();
        req.setAttribute("categoryList", categoryList);

        // 최근 본 상품(쿠키 기반)
        String recentCookie = CookieUtils.getCookieValue(req, "recentProductIds");
        List<Product> recentProductList = new ArrayList<>();
        if(recentCookie != null && !recentCookie.isEmpty()) {
            List<Integer> productIds = Arrays.stream(recentCookie.split("-"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            List<Product> fetched = productService.getProductListByIds(productIds);

            // 정렬
            Map<Integer, Product> productMap = fetched.stream()
                    .collect(Collectors.toMap(Product::getProductId, p -> p));

            recentProductList = productIds.stream()
                    .map(productMap::get)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
        req.setAttribute("recentProductList", recentProductList);

        return "shop/main/index";
    }
}