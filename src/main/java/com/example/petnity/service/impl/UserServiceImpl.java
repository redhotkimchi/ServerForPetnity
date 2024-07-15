package com.example.petnity.service.impl;

import com.example.petnity.controller.UserController;
import com.example.petnity.data.dao.PetDao;
import com.example.petnity.data.dao.UserDao;
import com.example.petnity.data.dto.PetDto;
import com.example.petnity.data.dto.UserDto;
import com.example.petnity.data.entity.PetEntity;
import com.example.petnity.data.entity.UserEntity;
import com.example.petnity.data.handler.UserDataHandler;
import com.example.petnity.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDao userDao;
    private final PetDao petDao;

//    @Autowired
    public UserServiceImpl(UserDao userDao, PetDao petDao){
        this.userDao = userDao;
        this.petDao = petDao;
    }

    @Override
    public UserDto.Info kakaoLogin(String userEmail) {
        Optional<UserEntity> userEntity = userDao.getUserByEmail(userEmail);
        UserDto.Info userDto = UserDto.Info.builder()
                .userEmail(userEntity.get().getUserEmail())
                .userNickname(userEntity.get().getUserName())
                .userName(userEntity.get().getUserName())
                .userGender(userEntity.get().getUserGender())
                .build();

        return userDto;
    }

    @Override
    public UserDto.Info saveUser(UserDto.Request userDto) {
        LOGGER.info("[UserService] Param :: userDtoInfo = {}", userDto.toString());

        UserEntity userEntity = userDto.toEntity();

        LOGGER.info("[UserService] User, add pet list :: userEntity = {}", userEntity.toString());

        UserEntity savedUserEntity = userDao.saveUser(userEntity);

        LOGGER.info("[UserService] User, save entity :: savedUserEntity = {}", savedUserEntity.toString());

        UserDto.Info savedUserDtoInfo = UserDto.Info.builder()
                .userEmail(savedUserEntity.getUserEmail())
                .userNickname(savedUserEntity.getUserNickname())
                .userName(savedUserEntity.getUserName())
                .userPassword(savedUserEntity.getUserPassword())
                .userGender(savedUserEntity.getUserGender())
                .userBirthYear(savedUserEntity.getUserBirthYear())
                .userBirthDay(savedUserEntity.getUserBirthDay())
                .build();

        LOGGER.info("[UserService] User, saved dto :: savedUserDtoInfo = {}", savedUserDtoInfo.toString());

        return savedUserDtoInfo;
    }

    @Override
    public UserDto.Info saveUser(UserDto.Info userDto) {
        LOGGER.info("[UserService] Param :: userDtoInfo = {}", userDto.toString());

        UserEntity userEntity = userDto.toEntity();

        List<PetDto.Response> petDtoResponseList = userDto.getPetDtoResponseList();

        for(PetDto.Response petDtoResponse : petDtoResponseList){
            PetEntity petEntity = PetEntity.builder()
                    .petName(petDtoResponse.getPetName())
                    .petKind(petDtoResponse.getPetKind())
                    .petGender(petDtoResponse.getPetGender())
                    .petBirthYear(petDtoResponse.getPetBirthYear())
                    .petBirthDay(petDtoResponse.getPetBirthDay())
                    .build();
            petEntity.setOwnerEntity(userEntity);
        }

        LOGGER.info("[UserService] User, add pet list :: userEntity = {}", userEntity.toString());

        UserEntity savedUserEntity = userDao.saveUser(userEntity);

        LOGGER.info("[UserService] User, save entity :: savedUserEntity = {}", savedUserEntity.toString());


        UserDto.Info savedUserDtoInfo = UserDto.Info.builder()
                .userEmail(savedUserEntity.getUserEmail())
                .userNickname(savedUserEntity.getUserNickname())
                .userName(savedUserEntity.getUserName())
                .userPassword(savedUserEntity.getUserPassword())
                .userGender(savedUserEntity.getUserGender())
                .userBirthYear(savedUserEntity.getUserBirthYear())
                .userBirthDay(savedUserEntity.getUserBirthDay())
                .build();

        LOGGER.info("[UserService] User, saved dto :: savedUserDtoInfo = {}", savedUserDtoInfo.toString());

        return savedUserDtoInfo;
    }

    @Override
    public UserDto.Info getUser(Long userId) {

        LOGGER.info("[UserService] Param :: userId = {}", userId);

        Optional<UserEntity> userEntity = userDao.getUser(userId);

        LOGGER.info("[UserService] Response :: userEntity = {}", userEntity.get().toString());

        List<PetEntity> petEntityList = petDao.getAllPetByOwnerId(userEntity.get().getUserId());

        LOGGER.info("[UserService] Response :: petEntityList = {}", petEntityList.toString());

        List<PetDto.Response> petDtoResponseList = new ArrayList<PetDto.Response>();

        for(PetEntity petEntity : petEntityList){
            petEntity.setOwnerEntity(userEntity.get());
            PetDto.Response petDto = PetDto.Response.builder()
                    .petName(petEntity.getPetName())
                    .petKind(petEntity.getPetKind())
                    .petGender(petEntity.getPetGender())
                    .petBirthYear(petEntity.getPetBirthYear())
                    .petBirthDay(petEntity.getPetBirthDay())
                    .build();
            petDtoResponseList.add(petDto);
        }

        LOGGER.info("[UserService] Response :: petDtoResponseList = {}", petDtoResponseList.toString());

        UserDto.Info userDtoInfo = UserDto.Info.builder()
                .userEmail(userEntity.get().getUserEmail())
                .userPassword(userEntity.get().getUserPassword())
                .userNickname(userEntity.get().getUserNickname())
                .userName(userEntity.get().getUserName())
                .userGender(userEntity.get().getUserGender())
                .userBirthYear(userEntity.get().getUserBirthYear())
                .userBirthDay(userEntity.get().getUserBirthDay())
                .petDtoResponseList(petDtoResponseList)
                .build();

        LOGGER.info("[UserService] Response :: userDtoInfo = {}", userDtoInfo.toString());

        return userDtoInfo;
    }

    @Override
    public UserDto.Info getUserByEmail(String userEmail) {

        LOGGER.info("[UserService] Param :: userEmail = {}", userEmail);

        Optional<UserEntity> userEntity = userDao.getUserByEmail(userEmail);

        LOGGER.info("[UserService] Response :: userEntity = {}", userEntity.get().toString());

        List<PetEntity> petEntityList = petDao.getAllPetByOwnerId(userEntity.get().getUserId());

        LOGGER.info("[UserService] Response :: petEntityList = {}", petEntityList.toString());

        List<PetDto.Response> petDtoResponseList = new ArrayList<PetDto.Response>();

        for(PetEntity petEntity : petEntityList){
            petEntity.setOwnerEntity(userEntity.get());
            LOGGER.info("[UserService] Response :: petEntity = {}", petEntity.toString());
            PetDto.Response petDto = PetDto.Response.builder()
                    .petName(petEntity.getPetName())
                    .petKind(petEntity.getPetKind())
                    .petGender(petEntity.getPetGender())
                    .petBirthYear(petEntity.getPetBirthYear())
                    .petBirthDay(petEntity.getPetBirthDay())
                    .build();
            petDtoResponseList.add(petDto);

            LOGGER.info("[UserService] Response :: petDto = {}", petDto.toString());
        }

        LOGGER.info("[UserService] Response :: petDtoResponseList = {}", petDtoResponseList.toString());

        UserDto.Info userDtoInfo = UserDto.Info.builder()
                .userEmail(userEntity.get().getUserEmail())
                .userPassword(userEntity.get().getUserPassword())
                .userNickname(userEntity.get().getUserNickname())
                .userName(userEntity.get().getUserName())
                .userGender(userEntity.get().getUserGender())
                .userBirthYear(userEntity.get().getUserBirthYear())
                .userBirthDay(userEntity.get().getUserBirthDay())
                .petDtoResponseList(petDtoResponseList)
                .build();

        LOGGER.info("[UserService] Response :: userDtoInfo = {}", userDtoInfo.toString());

        return userDtoInfo;
    }
}
