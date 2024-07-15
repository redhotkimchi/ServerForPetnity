package com.example.petnity.data.dao.impl;

import com.example.petnity.data.dao.CommentDao;
import com.example.petnity.data.entity.CommentEntity;
import com.example.petnity.data.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentDaoImpl implements CommentDao {

    private final CommentRepository commentRepository;
    public CommentDaoImpl(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }
    @Override
    public CommentEntity saveComment(CommentEntity commentEntity) {
        commentRepository.save(commentEntity);
        return commentEntity;
    }

    @Override
    public List<CommentEntity> getMotherComment(Long postId) {
        return commentRepository.findMotherCommentEntity(postId);
    }

    @Override
    public List<CommentEntity> getSubComment(Long postId, Long commentId) {
        return commentRepository.findSubComment(postId,commentId);
    }

}
