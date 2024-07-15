package com.example.petnity.data.dao;

import com.example.petnity.data.entity.PostEntity;

import java.util.List;

public interface PostDao {
    PostEntity savePost(PostEntity postEntity);
    PostEntity getPost(Long id);

    List<PostEntity> getAllPost();

}
