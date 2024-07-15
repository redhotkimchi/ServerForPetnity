package com.example.petnity.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "post")
public class PostEntity extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String postTitle;
    private String postBody;
    @ManyToOne
    @JoinColumn
    private UserEntity user;
    @OneToMany(mappedBy = "postEntity")
    private List<CommentEntity> commentEntityList = new ArrayList<CommentEntity>();



}
