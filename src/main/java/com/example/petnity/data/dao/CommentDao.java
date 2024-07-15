package com.example.petnity.data.dao;

import com.example.petnity.data.dto.CommentDto;
import com.example.petnity.data.entity.CommentEntity;

import java.util.List;

public interface CommentDao {
    CommentEntity saveComment(CommentEntity commentEntity);
    List<CommentEntity> getMotherComment(Long postId);
    List<CommentEntity> getSubComment(Long postId, Long commentId);

}
