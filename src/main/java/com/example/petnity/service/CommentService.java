package com.example.petnity.service;

import com.example.petnity.data.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto.SaveCommentDto saveComment(CommentDto.SaveCommentDto saveDto);
    CommentDto.SaveSubDto saveSubComment(CommentDto.SaveSubDto saveSubDto);

    List<CommentDto.GetCommentDto> getComment(Long postId);
}
