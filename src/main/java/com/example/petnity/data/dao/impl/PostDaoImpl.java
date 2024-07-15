package com.example.petnity.data.dao.impl;

import com.example.petnity.data.dao.PostDao;
import com.example.petnity.data.entity.PostEntity;
import com.example.petnity.data.repository.PostRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostDaoImpl implements PostDao {

    PostRepository postRepository;
    public PostDaoImpl(PostRepository postRepository){this.postRepository = postRepository;}

    @Override
    public PostEntity savePost(PostEntity postEntity) {
        postRepository.save(postEntity);
        return null;
    }

    @Override
    public PostEntity getPost(Long id) {
        return postRepository.findById(id).get();
    }

    @Override
    public List<PostEntity> getAllPost() {
        return postRepository.findAll(Sort.by(Sort.Direction.ASC, "createdDate"));
    }
}
