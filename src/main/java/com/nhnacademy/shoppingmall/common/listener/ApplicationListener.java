package com.nhnacademy.shoppingmall.common.listener;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.entity.user.domain.User;
import com.nhnacademy.shoppingmall.entity.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.entity.user.service.UserService;
import com.nhnacademy.shoppingmall.entity.user.service.impl.UserServiceImpl;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@WebListener
public class ApplicationListener implements ServletContextListener {
    static final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //todo#12 application 시작시 테스트 계정인 admin,user 등록합니다. 만약 존재하면 등록하지 않습니다.
        DbConnectionThreadLocal.initialize();

        User admin = new User("admin", "관리자", "12345", "20000611", User.Auth.ROLE_ADMIN, 1000000, LocalDateTime.now(), null);
        User user = new User("user", "유저", "12345", "20000611", User.Auth.ROLE_USER, 1000000, LocalDateTime.now(), null);

        if(Objects.isNull(userService.getUser(admin.getUserId()))) {
            userService.saveUser(admin);
        }
        if(Objects.isNull(userService.getUser(user.getUserId()))) {
            userService.saveUser(user);
        }

        DbConnectionThreadLocal.reset();
    }
}
