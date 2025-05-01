package com.nhnacademy.shoppingmall.entity.user.service;

import com.nhnacademy.shoppingmall.entity.user.domain.User;

import java.util.List;

public interface UserService {

    User getUser(String userId);

    List<User> getAllUsers();

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(String userId);

    User doLogin(String userId, String userPassword);

}
