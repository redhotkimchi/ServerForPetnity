package com.example.petnity.service.impl;

import com.example.petnity.data.dao.PostDao;
import com.example.petnity.data.dao.UserDao;
import com.example.petnity.data.dto.PostDto;
import com.example.petnity.data.entity.PostEntity;
import com.example.petnity.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostDao postDao;
    private final UserDao userDao;

    public PostServiceImpl(PostDao postDao, UserDao userdao){
        this.postDao = postDao;
        this.userDao = userdao;
    }
    @Override
    public PostDto.SaveDto savePost(PostDto.SaveDto saveDto) {
        PostEntity postEntity = PostEntity.builder()
                .postTitle(saveDto.getPostTitle())
                .postBody(saveDto.getPostBody())
                .user(userDao.getUserByEmail(saveDto.getUserEmail()).get())
                .build();
        postDao.savePost(postEntity);
        return saveDto;
    }

    @Override
    public List<PostDto.GetPostDto> getAllPost() {
        List<PostEntity> postEntityList = postDao.getAllPost();
        List<PostDto.GetPostDto> getPostDtoList = new ArrayList<>();
        for(PostEntity PE : postEntityList){
            PostDto.GetPostDto GPD = PostDto.GetPostDto.builder()
                    .postId(PE.getPostId())
                    .postTitle(PE.getPostTitle())
                    .postBody(PE.getPostBody())
                    .createdDate(PE.getCreatedDate())
                    .lastModifiedDate(PE.getLastModifiedDate())
                    .commentCount(PE.getCommentEntityList().size())
                    .build();
            getPostDtoList.add(GPD);
        }
        return getPostDtoList;
    }
}
