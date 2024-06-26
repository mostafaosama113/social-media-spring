package com.app.social.social.app.service.impl;

import com.app.social.social.app.entity.Post;
import com.app.social.social.app.exception.ColumnsNotExistsException;
import com.app.social.social.app.exception.ResourceExistsException;
import com.app.social.social.app.exception.ResourceNotFoundException;
import com.app.social.social.app.payload.PostDto;
import com.app.social.social.app.repositry.PostRepository;
import com.app.social.social.app.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public Map<String , Object> getAllPosts(int page , int pageSize , String sortBy) {
        if(page < 1) throw new ResourceNotFoundException("Page" , "page number" , page);
        if (!postRepository.getAllColumns().contains(sortBy)) {
            throw new ColumnsNotExistsException(sortBy);
        }
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(sortBy));
        Page<Post> list =  postRepository.findAll(pageable);
        List<PostDto> ret = new ArrayList<>();
        for (Post post : list.getContent()) {
            ret.add(post.toDto());
        }
        Map<String , Object> result = new HashMap<>();
        result.put("total_pages" , list.getTotalPages());
        result.put("total_elements" , list.getTotalElements());
        result.put("current_page" , page);
        result.put("content" , ret);
        return result;
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
