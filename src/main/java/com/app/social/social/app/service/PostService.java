package com.app.social.social.app.service;

import com.app.social.social.app.payload.PostDto;

import java.util.List;
import java.util.Map;


public interface PostService {
    PostDto createPost(PostDto model);

    Map<String , Object> getAllPosts(int page , int pageSize , String sortBy);
    PostDto getPostById(Long id);

    PostDto updatePost(Long id , PostDto model);

    PostDto deletePost(Long id);


}
