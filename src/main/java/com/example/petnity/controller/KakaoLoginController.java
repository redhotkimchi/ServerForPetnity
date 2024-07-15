package com.example.petnity.controller;

import com.example.petnity.data.dto.UserDto;
import com.example.petnity.service.KakaoLoginService;
import com.example.petnity.service.UserService;
import com.example.petnity.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class KakaoLoginController {

    KakaoLoginService kakaoLoginService;
    UserService userService;

//    @GetMapping("/login")
//    public String loginPage(){
//        return "kakaoCI/login";
//    }

    @Autowired
    public KakaoLoginController(KakaoLoginService kakaoLoginService, UserService userService){
        this.kakaoLoginService = kakaoLoginService;
        this.userService = userService;
    }

    @GetMapping(value = "/getKakaoAuthUrl")
    public String getKakaoAuthUrl(HttpServletRequest request) throws Exception{
        String reqUrl = "https://kauth.kakao.com/oauth/authorize" +
                "?client_id=fd3786d57849589d07ee5f9e8ec1ef8d" +
                "&redirect_uri=http://localhost:8080/login/kakao" +
                "&response_type=code";

        return reqUrl;
    }


    @GetMapping(value = "/kakao")
    public UserDto.Info kakaoLogin(@RequestParam String code) throws IOException{
        System.out.println("code: " + code);
        String access_token = kakaoLoginService.getKakaoAccessToken(code);
        Map<String, Object> userInfo = kakaoLoginService.getUserInfo(access_token);

        String kakaoAccountId = (String) userInfo.get("id");
        String userName = (String) userInfo.get("nickname");
        String userEmail = (String) userInfo.get("email");

        UserDto.Info userDtoInfo;
        if (userService.kakaoLogin(kakaoAccountId) == null ){
            userDtoInfo = UserDto.Info.builder()
                    .userName(userName)
                    .userEmail(userEmail)
                    .build();
        }
        userDtoInfo = userService.getUserByEmail(userEmail);

        // if (userDtoInfo == null) => signup
        // if (userDtoInfo != null) => login

        return userDtoInfo;
    }

}
