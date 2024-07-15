package com.example.petnity.data.dao;

import com.example.petnity.data.dto.PetDto;
import com.example.petnity.data.entity.PetEntity;

import java.util.List;
import java.util.Optional;

public interface PetDao {
    PetEntity savePet(PetEntity petEntity);
    Optional<PetEntity> getPet(Long petId);

    List<PetEntity> getAllPetByOwnerId(Long ownerId);

}
