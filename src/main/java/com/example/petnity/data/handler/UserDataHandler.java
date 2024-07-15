package com.example.petnity.data.handler;

import com.example.petnity.data.entity.UserEntity;

public interface UserDataHandler {
    UserEntity saveUserEntity(String userId, String userName);
    UserEntity getUserEntity(String userId);
}
