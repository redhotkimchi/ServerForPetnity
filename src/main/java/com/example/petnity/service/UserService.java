package com.example.petnity.service;

import com.example.petnity.data.dto.UserDto;

public interface UserService {
    UserDto.Info kakaoLogin(String userEmail);

    UserDto.Info saveUser(UserDto.Request userDto);

    UserDto.Info saveUser(UserDto.Info userDto);

    UserDto.Info getUser(Long userId);

    UserDto.Info getUserByEmail(String userEmail);
}
