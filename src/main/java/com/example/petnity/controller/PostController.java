package com.example.petnity.controller;

import com.example.petnity.data.dto.PostDto;
import com.example.petnity.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService){this.postService = postService;}

    @PostMapping(value = "savepost")
    ResponseEntity<PostDto.SaveDto> savePost(@Valid @RequestBody PostDto.SaveDto saveDto){
        return ResponseEntity.status(HttpStatus.OK).body(postService.savePost(saveDto));
    }

    @GetMapping(value = "getpost")
    ResponseEntity<List<PostDto.GetPostDto>> getAllPost(){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPost());
    }
}
