package com.nhnacademy.shoppingmall.entity.user.repository;

import com.nhnacademy.shoppingmall.entity.user.domain.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUserIdAndUserPassword(String userId, String userPassword);
    Optional<User> findById(String userId);
    List<User> findAllUsers();
    int save(User user);
    int deleteByUserId(String userId);
    int update(User user);
    int updateLatestLoginAtByUserId(String userId, LocalDateTime latestLoginAt);
    int countByUserId(String userId);
}
