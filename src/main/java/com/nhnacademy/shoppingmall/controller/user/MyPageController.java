package com.nhnacademy.shoppingmall.controller.user;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.entity.order.domain.Order;
import com.nhnacademy.shoppingmall.entity.order.repository.Impl.OrderRepositoryImpl;
import com.nhnacademy.shoppingmall.entity.order.service.Impl.OrderServiceImpl;
import com.nhnacademy.shoppingmall.entity.order.service.OrderService;
import com.nhnacademy.shoppingmall.entity.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.entity.product.service.ProductService;
import com.nhnacademy.shoppingmall.entity.product.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.entity.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/mypage/index.do")
public class MyPageController implements BaseController {

    private final OrderService orderService = new OrderServiceImpl(new OrderRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");


        log.debug("로그인 유저 ID: {}", loginUser.getUserId());

        List<Order> orderList = orderService.getOrdersByUserId(loginUser.getUserId());
        req.setAttribute("orderList", orderList);

        return "shop/main/mypage";
    }
}
