package com.example.petnity.data.dao;

import com.example.petnity.data.entity.UserEntity;

import java.util.Optional;

public interface UserDao {
    UserEntity saveUser(UserEntity userEntity);
    void deleteUser(Long userId);
    Optional<UserEntity> getUser(Long userId);
    Optional<UserEntity> getUserByEmail(String userEmail);
}
