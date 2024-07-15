package com.example.petnity.service;

import com.example.petnity.data.dto.PetDto;

public interface PetService {
    PetDto.Response savePet(PetDto.Request petDto);

    PetDto.Response updatePet(PetDto.Request petDto);
    PetDto.Response getPet(Long petId);
}
