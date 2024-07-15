package com.example.petnity.data.dao.impl;

import com.example.petnity.data.dao.PetDao;
import com.example.petnity.data.dto.PetDto;
import com.example.petnity.data.entity.PetEntity;
import com.example.petnity.data.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetDaoImpl implements PetDao {
    PetRepository petRepository;

    @Autowired
    public PetDaoImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public PetEntity savePet(PetEntity petEntity) {
        petRepository.save(petEntity);
        return petEntity;
    }

    @Override
    public Optional<PetEntity> getPet(Long petId) {
        Optional<PetEntity> petEntity = petRepository.findById(petId);
        return petEntity;
    }

    @Override
    public List<PetEntity> getAllPetByOwnerId(Long ownerId) {
        List<PetEntity> petEntityList = petRepository.findAllByOwnerEntityUserId(ownerId);
        return petEntityList;
    }


}
