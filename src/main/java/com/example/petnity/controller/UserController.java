package com.example.petnity.controller;

import com.example.petnity.data.dto.PetDto;
import com.example.petnity.data.dto.UserDto;
import com.example.petnity.service.PetService;
import com.example.petnity.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/user-api")
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final PetService petService;
  //x  @Autowired
    public UserController(UserService userService, PetService petService) {
        this.userService = userService;
        this.petService = petService;
    }

    @GetMapping(value = "/user/{userEmail}")
    public ResponseEntity<UserDto.Info> getUserByUserEmail(@PathVariable String userEmail){
        LOGGER.info("[UserController] Perform {} of Petnity API.", "getUserByEmail");
        UserDto.Info userDtoInfo = userService.getUserByEmail(userEmail);

        if (userDtoInfo == null) {
            LOGGER.error("[UserController] failed Response :: There is no {}", userEmail);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        LOGGER.info("[UserController] Response :: userDtoInfo = {}", userDtoInfo.toString());

        return ResponseEntity.status(HttpStatus.OK).body(userDtoInfo);
    }

    @PostMapping(value = "/user/signup")
    public ResponseEntity<UserDto.Info> createUser(@Valid @RequestBody UserDto.Request userDto){
        LOGGER.info("[UserController] perform {} of Petnity API.", "createUser");

        long StartTime = System.currentTimeMillis();
        LOGGER.info("[UserController] Response :: userDtoInfo = {}", userDto.toString());

        UserDto.Info response = userService.saveUser(userDto);

        LOGGER.info("[UserController] Response :: response = {}, Response Time = {}ms", response.toString(), System.currentTimeMillis() - StartTime);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value = "/user/update")
    public ResponseEntity<UserDto.Info> updateUser(@Valid @RequestBody UserDto.Request userDto){
        LOGGER.info("[UserController] perform {} of Petnity API.", "updateUser");

        long StartTime = System.currentTimeMillis();
        LOGGER.info("[UserController] Response :: userDtoInfo = {}", userDto.toString());

        UserDto.Info response = userService.saveUser(userDto);

        LOGGER.info("[UserController] Response :: response = {}, Response Time = {}ms", response.toString(), System.currentTimeMillis() - StartTime);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/pet")
    public ResponseEntity<PetDto.Response> getPet(PetDto.Request petDto){
        LOGGER.info("[UserController] perform {} of Petnity API.", "getPet");

        UserDto.Info userDtoInfo = userService.getUserByEmail(petDto.getOwnerEmail());

        if (userDtoInfo == null) {
            LOGGER.error("[UserController] failed Response :: There is no {}", petDto.getOwnerEmail());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List<PetDto.Response> petDtoResponseList = userDtoInfo.getPetDtoResponseList();

        if (petDtoResponseList.isEmpty()){
            LOGGER.error("[UserController] failed Response :: Owner's pet list is Empty");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        PetDto.Response response = null;
        for(PetDto.Response petDtoResponse : petDtoResponseList) {
            if (Objects.equals(petDto.getPetName(), petDtoResponse.getPetName())) {
                response = petDtoResponse;
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/pet/create")
    public ResponseEntity<PetDto.Response> createPet(@Valid @RequestBody PetDto.Request petDto){
        LOGGER.info("[UserController] perform {} of Petnity API.", "createPet");
        long StartTime = System.currentTimeMillis();

        PetDto.Response response = petService.savePet(petDto);

        LOGGER.info("[UserController] Response :: response = {}, Response Time = {}ms", response.toString(), System.currentTimeMillis() - StartTime);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/pet/udpate")
    public PetDto.Response updatePet(){
        return null;
    }


}
