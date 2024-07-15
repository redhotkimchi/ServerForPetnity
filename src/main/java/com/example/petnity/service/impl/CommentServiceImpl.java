package com.example.petnity.service.impl;

import com.example.petnity.data.dao.CommentDao;
import com.example.petnity.data.dao.PostDao;
import com.example.petnity.data.dao.UserDao;
import com.example.petnity.data.dto.CommentDto;
import com.example.petnity.data.entity.CommentEntity;
import com.example.petnity.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao;
    private final UserDao userDao;
    private final PostDao postDao;
    public CommentServiceImpl(CommentDao commentDao, UserDao userDao, PostDao postDao) {
        this.commentDao = commentDao;
        this.userDao = userDao;
        this.postDao = postDao;
    }
    @Override
    public CommentDto.SaveCommentDto saveComment(CommentDto.SaveCommentDto saveDto) {
        CommentEntity commentEntity = CommentEntity.builder()
                .userEntity(userDao.getUserByEmail(saveDto.getUserEmail()).get())
                .postEntity(postDao.getPost(saveDto.getPostId()))
                .subComment(false)
                .commentBody(saveDto.getCommentBody())
                .build();
        commentDao.saveComment(commentEntity);
        return null;
    }

    @Override
    public CommentDto.SaveSubDto saveSubComment(CommentDto.SaveSubDto saveSubDto) {
        CommentEntity commentEntity = CommentEntity.builder()
                .ownerCommentId(saveSubDto.getCommentId())
                .subComment(true)
                .userEntity(userDao.getUserByEmail(saveSubDto.getUserEmail()).get())
                .postEntity(postDao.getPost(saveSubDto.getPostId()))
                .commentBody(saveSubDto.getCommentBody())
                .build();
        commentDao.saveComment(commentEntity);
        return null;
    }

    @Override
    public List<CommentDto.GetCommentDto> getComment(Long postId) {
        List<CommentEntity> commentEntityList = commentDao.getMotherComment(postId);
        List<CommentDto.GetCommentDto> getCommentDtoList = new ArrayList<>();
        for (CommentEntity CME : commentEntityList ){
            List<CommentEntity> subCommentList = commentDao.getSubComment(postId, CME.getCommentId());
            List<CommentDto.GetSubCommentDto> getSubCommentDtoList = new ArrayList<>();
            for(CommentEntity SUBCM : subCommentList){
                CommentDto.GetSubCommentDto getSubCommentDto = CommentDto.GetSubCommentDto.builder()
                        .commentId(SUBCM.getCommentId())
                        .ownerCommentId(SUBCM.getOwnerCommentId())
                        .commentBody(SUBCM.getCommentBody())
                        .createdDate(SUBCM.getCreatedDate())
                        .lastModifiedDate(SUBCM.getLastModifiedDate())
                        .build();
                getSubCommentDtoList.add(getSubCommentDto);
            }
            CommentDto.GetCommentDto getCommentDto = CommentDto.GetCommentDto.builder()
                    .commentId(CME.getCommentId())
                    .commentBody(CME.getCommentBody())
                    .createdDate(CME.getCreatedDate())
                    .lastModifiedDate(CME.getLastModifiedDate())
                    .postId(postId)
                    .subCommentDtoList(getSubCommentDtoList)
                    .build();
            getCommentDtoList.add(getCommentDto);
        }

        return getCommentDtoList;
    }
}
