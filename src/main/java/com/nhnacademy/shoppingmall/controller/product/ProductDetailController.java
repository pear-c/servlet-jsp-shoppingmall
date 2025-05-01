package com.nhnacademy.shoppingmall.controller.product;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Timestamp;
import java.util.Date;

@RequestMapping(method = RequestMapping.Method.GET, value = "/product/detail.do")
public class ProductDetailController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String targetId = req.getParameter("product_id");
        if (targetId == null) {
            throw new IllegalArgumentException("productId is required");
        }

        int productId = Integer.parseInt(targetId);
        Product product = productService.getProduct(productId);

        // 등록일 표시용
        Date createdDate = Timestamp.valueOf(product.getCreatedAt());
        req.setAttribute("createdDate", createdDate);
        req.setAttribute("product", product);

        return "shop/product/product_detail";
    }
}
