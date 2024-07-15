package com.example.petnity.service;

import com.example.petnity.data.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto.SaveDto savePost(PostDto.SaveDto saveDto);
    List<PostDto.GetPostDto> getAllPost();
}
