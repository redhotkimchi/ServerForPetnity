package com.example.petnity.data.repository;

import com.example.petnity.data.entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<PetEntity, Long> {
    List<PetEntity> findAllByOwnerEntityUserId(Long ownerId);
}
