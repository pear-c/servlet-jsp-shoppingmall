package com.nhnacademy.shoppingmall.user.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class UserRepositoryImpl implements UserRepository {

    @Override
    public Optional<User> findByUserIdAndUserPassword(String userId, String userPassword) {
        Connection connection = DbConnectionThreadLocal.getConnection();

        /*todo#3-1 회원의 아이디와 비밀번호를 이용해서 조회하는 코드 입니다.(로그인)
          해당 코드는 SQL Injection이 발생합니다. SQL Injection이 발생하지 않도록 수정하세요.
         */
        String sql = """
                        SELECT * 
                        FROM users 
                        WHERE user_id = ? AND user_password = ?
                     """;
        log.debug("sql:{}",sql);

        try(PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            pstmt.setString(2, userPassword);

            try(ResultSet rs =  pstmt.executeQuery()) {
                if(rs.next()){
                    User user = new User(
                            rs.getString("user_id"),
                            rs.getString("user_name"),
                            rs.getString("user_password"),
                            rs.getString("user_birth"),
                            User.Auth.valueOf(rs.getString("user_auth")),
                            rs.getInt("user_point"),
                            Objects.nonNull(rs.getTimestamp("created_at")) ? rs.getTimestamp("created_at").toLocalDateTime() : null,
                            Objects.nonNull(rs.getTimestamp("latest_login_at")) ? rs.getTimestamp("latest_login_at").toLocalDateTime() : null
                    );
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();

        //todo#3-2 회원조회
        String sql = "SELECT * FROM users WHERE user_id = ?";
        log.debug("sql:{}",sql);

        try(PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, userId);

            try(ResultSet rs =  pstmt.executeQuery()) {
                if(rs.next()){
                    User user = new User(
                            rs.getString("user_id"),
                            rs.getString("user_name"),
                            rs.getString("user_password"),
                            rs.getString("user_birth"),
                            User.Auth.valueOf(rs.getString("user_auth")),
                            rs.getInt("user_point"),
                            Objects.nonNull(rs.getTimestamp("created_at")) ? rs.getTimestamp("created_at").toLocalDateTime() : null,
                            Objects.nonNull(rs.getTimestamp("latest_login_at")) ? rs.getTimestamp("latest_login_at").toLocalDateTime() : null
                    );
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public int save(User user) {
        Connection connection = DbConnectionThreadLocal.getConnection();

        //todo#3-3 회원등록, executeUpdate()을 반환합니다.
        String sql = """
                        INSERT INTO users
                        VALUES(?, ?, ?, ?, ?, ?, ?, ?)
                     """;

        log.debug("sql:{}",sql);

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, user.getUserPassword());
            pstmt.setString(4, user.getUserBirth());
            pstmt.setString(5, user.getUserAuth().toString());
            pstmt.setInt(6, user.getUserPoint());
            pstmt.setTimestamp(7, Timestamp.valueOf(user.getCreatedAt()));
            if(user.getLatestLoginAt() != null) {
                pstmt.setTimestamp(8, Timestamp.valueOf(user.getLatestLoginAt()));
            } else {
                pstmt.setNull(8, Types.TIMESTAMP);
            }

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByUserId(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();

        //todo#3-4 회원삭제, executeUpdate()을 반환합니다.
        String sql = "DELETE FROM users WHERE user_id = ?";
        log.debug("sql:{}",sql);

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, userId);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(User user) {
        Connection connection = DbConnectionThreadLocal.getConnection();

        //todo#3-5 회원수정, executeUpdate()을 반환합니다.
        String sql = """
                         UPDATE users 
                         SET user_name = ?, user_password = ?, user_birth = ?, user_auth = ?, user_point = ? 
                         WHERE user_id = ?
                     """;

        log.debug("sql:{}",sql);

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getUserPassword());
            pstmt.setString(3, user.getUserBirth());
            pstmt.setString(4, user.getUserAuth().toString());
            pstmt.setInt(5, user.getUserPoint());
            pstmt.setString(6, user.getUserId());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateLatestLoginAtByUserId(String userId, LocalDateTime latestLoginAt) {
        Connection connection = DbConnectionThreadLocal.getConnection();

        //todo#3-6, 마지막 로그인 시간 업데이트, executeUpdate()을 반환합니다.
        String sql = "UPDATE users SET latest_login_at = ? WHERE user_id = ?";
        log.debug("sql:{}",sql);

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setTimestamp(1, Timestamp.valueOf(latestLoginAt));
            pstmt.setString(2, userId);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByUserId(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();

        //todo#3-7 userId와 일치하는 회원의 count를 반환합니다.
        String sql = "SELECT COUNT(*) FROM users WHERE user_id = ?";
        log.debug("sql:{}",sql);

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, userId);

            try(ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
