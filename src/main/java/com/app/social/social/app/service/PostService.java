package com.app.social.social.app.service;

import com.app.social.social.app.payload.PostDto;

import java.util.List;


public interface PostService {
    PostDto createPost(PostDto model);

    List<PostDto> getAllPosts(int page);
    PostDto getPostById(Long id);

    PostDto updatePost(Long id , PostDto model);

    PostDto deletePost(Long id);


}
