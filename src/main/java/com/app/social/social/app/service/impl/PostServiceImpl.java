package com.app.social.social.app.service.impl;

import com.app.social.social.app.entity.Post;
import com.app.social.social.app.exception.ResourceExistsException;
import com.app.social.social.app.payload.PostDto;
import com.app.social.social.app.repositry.PostRepository;
import com.app.social.social.app.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    @Override
    public PostDto createPost(PostDto model) {
        Post post = new Post();
        post.setTitle(model.getTitle());
        post.setContent(model.getContent());
        if(postRepository.getPostByTitle(post.getTitle()).isPresent()){
            throw new ResourceExistsException();
        }
        postRepository.save(post);
        model.setId(post.getId());
        return model;
    }
}
