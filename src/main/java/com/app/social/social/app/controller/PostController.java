package com.app.social.social.app.controller;

import com.app.social.social.app.payload.PostDto;
import com.app.social.social.app.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto model) {
        return new ResponseEntity<>(postService.createPost(model) , HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity< List<PostDto> > getAll(){
        return new ResponseEntity<>(postService.getAllPosts() , HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(postService.getPostById(id) , HttpStatus.OK);
    }
}
