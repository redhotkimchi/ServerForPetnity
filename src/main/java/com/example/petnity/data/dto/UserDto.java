package com.example.petnity.data.dto;

import com.example.petnity.data.entity.PetEntity;
import com.example.petnity.data.entity.UserEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class UserDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    public static class Info {
        private String userEmail;
        private String userPassword;
        private String userNickname;
        private String userName;
        private String userGender;
        private String userBirthYear;
        private String userBirthDay;
        private List<PetDto.Response> petDtoResponseList = new ArrayList<PetDto.Response>();

        public UserEntity toEntity(){

            return UserEntity.builder()
                    .userEmail(userEmail)
                    .userNickname(userNickname)
                    .userName(userName)
                    .userPassword(userPassword)
                    .userGender(userGender)
                    .userBirthYear(userBirthYear)
                    .userBirthDay(userBirthDay)
                    .petEntityList(new ArrayList<PetEntity>())
                    .build();
        }
    }


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    public static class Request{
        @NotBlank(message = "이메일을 다시 확인해주세요.")
        private String userEmail;
        @NotBlank(message = "비밀번호를 다시 확인해주세요.")
        private String userPassword;
        @NotBlank(message = "닉네임을 다시 확인해주세요.")
        private String userNickname;
        @NotBlank(message = "이름을 다시 확인해주세요.")
        private String userName;
        @NotBlank(message = "성별을 다시 확인해주세요.")
        private String userGender;
        private String userBirthYear;
        private String userBirthDay;

        public UserEntity toEntity(){
            return UserEntity.builder()
                    .userEmail(userEmail)
                    .userName(userName)
                    .userNickname(userNickname)
                    .userPassword(userPassword)
                    .userGender(userGender)
                    .userBirthYear(userBirthYear)
                    .userBirthDay(userBirthDay)
                    .build();
        }

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    public static class Response{
        private String userEmail;
        private String userPassword;
        private String userNickname;
        private String userName;
        private String userGender;
        private String userBirthYear;
        private String userBirthDay;
    }

}
