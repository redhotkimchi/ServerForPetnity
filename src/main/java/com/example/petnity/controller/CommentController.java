package com.example.petnity.controller;

import com.example.petnity.data.dto.CommentDto;
import com.example.petnity.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.stream.events.Comment;
import java.util.List;

@RestController
@RequestMapping("/api/v1/comment-api")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService){this.commentService = commentService;}
    @PostMapping(value = "savecomment")
    public CommentDto.SaveCommentDto commentSave(@Valid @RequestBody CommentDto.SaveCommentDto saveDto){

        return commentService.saveComment(saveDto);
    }
    @PostMapping(value = "savesubcomment")
    public CommentDto.SaveSubDto subCommentSave(@Valid @RequestBody CommentDto.SaveSubDto saveSubDto){

        return commentService.saveSubComment(saveSubDto);
    }
    @GetMapping(value = "getcomment")
    public List<CommentDto.GetCommentDto> getComment(@RequestParam("postId") Long postId){
        return commentService.getComment(postId);
    }

}
