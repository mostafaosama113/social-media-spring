package com.app.social.social.app.service.impl;

import com.app.social.social.app.entity.Post;
import com.app.social.social.app.exception.ResourceExistsException;
import com.app.social.social.app.exception.ResourceNotFoundException;
import com.app.social.social.app.payload.PostDto;
import com.app.social.social.app.repositry.PostRepository;
import com.app.social.social.app.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    @Override
    public PostDto createPost(PostDto model) {
        Post post = model.toPost();
        if(postRepository.getPostByTitle(post.getTitle()).isPresent()){
            throw new ResourceExistsException();
        }
        postRepository.save(post);
        model.setId(post.getId());
        return model;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> list =  postRepository.findAll();
        List<PostDto> ret = new ArrayList<>();
        for(Post post : list){
            ret.add(post.toDto());
        }
        return ret;
    }

    @Override
    public PostDto getPostById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isEmpty()){
            throw new ResourceNotFoundException();
        }
        return post.get().toDto();
    }
}
