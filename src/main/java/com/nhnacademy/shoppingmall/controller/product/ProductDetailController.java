package com.nhnacademy.shoppingmall.controller.product;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.util.CookieUtils;
import com.nhnacademy.shoppingmall.entity.product.domain.Product;
import com.nhnacademy.shoppingmall.entity.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.entity.product.service.ProductService;
import com.nhnacademy.shoppingmall.entity.product.service.impl.ProductServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RequestMapping(method = RequestMapping.Method.GET, value = "/product/detail.do")
public class ProductDetailController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int productId = Integer.parseInt(req.getParameter("product_id"));
        Product product = productService.getProduct(productId);

        // 기존 쿠키 읽기
        String currentCookie = CookieUtils.getCookieValue(req, "recentProductIds");
        List<String> productIds = new ArrayList<>();
        if(currentCookie != null && !currentCookie.isEmpty()) {
            productIds = new ArrayList<>(Arrays.asList(currentCookie.split("-")));
            productIds.remove(String.valueOf(productId));
        }
        productIds.add(0, String.valueOf(productId));

        if(productIds.size() > 5) {
            productIds = productIds.subList(0, 5);
        }

        // 쿠키 저장
        String newCookie = String.join("-", productIds);
        Cookie cookie = new Cookie("recentProductIds", newCookie);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 7); // 7일
        resp.addCookie(cookie);

        // 등록일 표시용
        Date createdDate = Timestamp.valueOf(product.getCreatedAt());
        req.setAttribute("createdDate", createdDate);
        req.setAttribute("product", product);

        return "shop/product/product_detail";
    }
}
