package com.nhnacademy.shoppingmall.controller.order;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.entity.cart.domain.Cart;
import com.nhnacademy.shoppingmall.entity.cart.domain.CartItem;
import com.nhnacademy.shoppingmall.entity.order.domain.Order;
import com.nhnacademy.shoppingmall.entity.order.domain.OrderItem;
import com.nhnacademy.shoppingmall.entity.order.repository.Impl.OrderRepositoryImpl;
import com.nhnacademy.shoppingmall.entity.order.service.Impl.OrderServiceImpl;
import com.nhnacademy.shoppingmall.entity.order.service.OrderService;
import com.nhnacademy.shoppingmall.entity.product.domain.Product;
import com.nhnacademy.shoppingmall.entity.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.entity.product.service.ProductService;
import com.nhnacademy.shoppingmall.entity.product.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.entity.user.domain.User;
import com.nhnacademy.shoppingmall.entity.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.entity.user.service.UserService;
import com.nhnacademy.shoppingmall.entity.user.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@RequestMapping(method = RequestMapping.Method.POST, value = "/order/checkout.do")
public class OrderCheckoutController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final OrderService orderService = new OrderServiceImpl(new OrderRepositoryImpl());
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("loginUser");
        String userId = user.getUserId();

        Map<CartItem, Product> cartItemProductMap = new LinkedHashMap<>();
        for (CartItem item : cart.getCartItemList()) {
            Product product = productService.getProduct(item.getCartProductId());
            cartItemProductMap.put(item, product);
        }
        int totalPrice = cart.calculateTotalPrice(cartItemProductMap);

        if(user.getUserPoint() < totalPrice) {
            session.setAttribute("error", "보유 포인트가 부족합니다.");
            return "redirect:/cart/view.do";
        }
        user.setUserPoint(user.getUserPoint() - totalPrice);
        userService.updateUser(user);


        Order order = new Order();
        order.setUserId(userId);
        order.setTotalPrice(totalPrice);
        order.setOrderCreatedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem item : cart.getCartItemList()) {
            Product product = productService.getProduct(item.getCartProductId());

            if (product.getProductStock() < item.getCartQuantity()) {
                session.setAttribute("error", product.getProductName() + "의 재고가 부족합니다.");
                return "redirect:/cart/view.do";
            }

            product.setProductStock(product.getProductStock() - item.getCartQuantity());
            productService.updateProduct(product);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrderProductId(product.getProductId());
            orderItem.setQuantity(item.getCartQuantity());
            orderItem.setItemPrice(product.getProductPrice());

            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        orderService.createOrder(order);

        session.removeAttribute("cart");
//        return "redirect:/order/success.do";
        return "redirect:/index.do";
    }
}
