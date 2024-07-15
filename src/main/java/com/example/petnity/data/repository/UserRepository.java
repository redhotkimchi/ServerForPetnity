package com.example.petnity.data.repository;

import com.example.petnity.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserEmail(String userEmail);
    /*
    @Query(value="select * from userId = :userId", nativeQuery = true)
    List < UserEntity> findByUserId(@Param("user_id") String user_id);
    */
}
