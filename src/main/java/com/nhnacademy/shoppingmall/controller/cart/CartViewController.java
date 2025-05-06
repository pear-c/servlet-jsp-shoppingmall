package com.nhnacademy.shoppingmall.controller.cart;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.entity.cart.domain.Cart;
import com.nhnacademy.shoppingmall.entity.cart.domain.CartItem;
import com.nhnacademy.shoppingmall.entity.product.domain.Product;
import com.nhnacademy.shoppingmall.entity.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.entity.product.service.ProductService;
import com.nhnacademy.shoppingmall.entity.product.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@RequestMapping(method = RequestMapping.Method.GET, value = "/cart/view.do")
public class CartViewController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if(cart == null || cart.isEmpty()) {
            req.setAttribute("cartItemProductMap", Collections.emptyMap());
        } else {
            Map<CartItem, Product> cartItemProductMap = new LinkedHashMap<>();
            for(CartItem item : cart.getCartItemList()) {
                Product product = productService.getProduct(item.getCartProductId());
                cartItemProductMap.put(item, product);
            }
            req.setAttribute("cartItemProductMap", cartItemProductMap);
        }

        return "shop/cart/index";
    }
}
