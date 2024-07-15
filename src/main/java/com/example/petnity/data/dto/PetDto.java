package com.example.petnity.data.dto;

import com.example.petnity.data.entity.PetEntity;
import lombok.*;

import javax.persistence.Column;


public class PetDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    public static class Info{
        private Long petId;
        private String petName;
        private String petKind;
        private String petGender;
        private String petBirthYear;
        private String petBirthDay;
        private String ownerEmail;

        public PetEntity toEntity(){
            return PetEntity.builder()
                    .petId(petId)
                    .petName(petName)
                    .petKind(petKind)
                    .petGender(petGender)
                    .petBirthYear(petBirthYear)
                    .petBirthDay(petBirthDay)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    public static class Request{
        private String petName;
        private String petKind;
        private String petGender;
        private String petBirthYear;
        private String petBirthDay;
        private String ownerEmail;

        public PetEntity toEntity(){
            return PetEntity.builder()
                    .petName(petName)
                    .petKind(petKind)
                    .petGender(petGender)
                    .petBirthYear(petBirthYear)
                    .petBirthDay(petBirthDay)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    public static class Response{
        private String petName;
        private String petKind;
        private String petGender;
        private String petBirthYear;
        private String petBirthDay;

        public PetEntity toEntity(){
            return PetEntity.builder()
                    .petName(petName)
                    .petKind(petKind)
                    .petGender(petGender)
                    .petBirthYear(petBirthYear)
                    .petBirthDay(petBirthDay)
                    .build();
        }
    }

}
