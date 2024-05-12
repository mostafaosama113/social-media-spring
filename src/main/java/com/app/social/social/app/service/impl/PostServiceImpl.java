package com.app.social.social.app.service.impl;

import com.app.social.social.app.entity.Post;
import com.app.social.social.app.exception.ResourceExistsException;
import com.app.social.social.app.exception.ResourceNotFoundException;
import com.app.social.social.app.payload.PostDto;
import com.app.social.social.app.repositry.PostRepository;
import com.app.social.social.app.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<PostDto> getAllPosts(int page) {
        Pageable pageable = PageRequest.of(page , 10);
        Page<Post> list =  postRepository.findAll(pageable);
        List<PostDto> ret = new ArrayList<>();
        for (Post post : list.getContent()) {
            ret.add(post.toDto());
        }
        return ret;
    }

    @Override
    public PostDto getPostById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isEmpty()){
            throw new ResourceNotFoundException("Post" , "id" , id);
        }
        return post.get().toDto();
    }

    @Override
    public PostDto updatePost(Long id, PostDto model) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isEmpty()){
            throw new ResourceNotFoundException("Post" , "id" , id);
        }

        if(model.getTitle() != null){
            if(!model.getTitle().equals(post.get().getTitle())){
                if(postRepository.getPostByTitle(model.getTitle()).isPresent()){
                    throw new ResourceExistsException();
                }
                post.get().setTitle(model.getTitle());
            }
        }
        if(model.getContent() != null){
            if(!model.getContent().equals(post.get().getContent())){
                post.get().setContent(model.getContent());
            }
        }
        postRepository.save(post.get());
        return post.get().toDto();
    }

    @Override
    public PostDto deletePost(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isEmpty()){
            throw new ResourceNotFoundException("Post" , "id" , id);
        }
        postRepository.delete(post.get());
        return post.get().toDto();
    }
}
