package com.example.petnity.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "pet")
public class PetEntity extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petId;
    @Column(unique = true)
    private String petName;
    private String petKind;
    private String petGender;
    private String petBirthYear;
    private String petBirthDay;

    @ManyToOne
    @JoinColumn
    private UserEntity ownerEntity;

    public void setOwnerEntity(UserEntity ownerEntity){
        if (this.ownerEntity != null) {
            this.ownerEntity.removePet(this);
        }
        this.ownerEntity = ownerEntity;
        ownerEntity.addPet(this);
    }

}
