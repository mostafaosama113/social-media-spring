package com.app.social.social.app.service;

import com.app.social.social.app.payload.PostDto;


public interface PostService {
    PostDto createPost(PostDto model);
}
