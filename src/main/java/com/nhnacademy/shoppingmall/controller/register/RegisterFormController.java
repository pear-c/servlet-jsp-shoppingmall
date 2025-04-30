package com.nhnacademy.shoppingmall.controller.register;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;

@RequestMapping(method = RequestMapping.Method.POST, value = "/signup.do")
public class RegisterFormController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter("user_id");
        String userName = req.getParameter("user_name");
        String userPassword = req.getParameter("user_password");
        String userBirth = req.getParameter("user_birth");
        User.Auth userAuth = User.Auth.valueOf(req.getParameter("user_auth"));
        int userPoint = 1000000;    // 회원 가입 시 기본 100만 포인트 부여
        LocalDateTime createdAt = LocalDateTime.now();

        User user = new User(userId, userName, userPassword, userBirth, userAuth, userPoint, createdAt, null);
        userService.saveUser(user);

        // 회원가입 성공 시 알림 메시지 출력용
        HttpSession session = req.getSession();
        session.setAttribute("signupSuccess", true);

        return "redirect:/index.do";
    }
}
