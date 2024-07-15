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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/pet-api")
public class PetController {

    private final Logger LOGGER = LoggerFactory.getLogger(PetController.class);

    private final PetService petService;
    private final UserService userService;


    public PetController (PetService petService, UserService userService){
        this.petService = petService;
        this.userService = userService;
    }

    @GetMapping("/pet")
    public ResponseEntity<PetDto.Response> getPet(PetDto.Request petDto){
        LOGGER.info("[UserController] perform {} of Petnity API.", "getPet");

        UserDto.Info userDtoInfo = userService.getUserByEmail(petDto.getOwnerEmail());

        if (userDtoInfo == null) {
            LOGGER.error("[PetController] failed Response :: There is no {}", petDto.getOwnerEmail());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List<PetDto.Response> petDtoResponseList = userDtoInfo.getPetDtoResponseList();

        if (petDtoResponseList.isEmpty()){
            LOGGER.error("[PetController] failed Response :: Owner's pet list is Empty");
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
    public PetDto.Response createPet(){
        return null;
    }

    @PostMapping("/pet/udpate")
    public PetDto.Response updatePet(){
        return null;
    }


}
