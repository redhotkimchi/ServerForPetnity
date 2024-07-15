package com.example.petnity.data.entity;

import lombok.*;
import org.apache.catalina.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "comment")
public class CommentEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private Long ownerCommentId;
    private String commentBody;
    private Boolean subComment;
    @ManyToOne
    @JoinColumn
    private UserEntity userEntity;
    @ManyToOne
    @JoinColumn
    private PostEntity postEntity;
}
