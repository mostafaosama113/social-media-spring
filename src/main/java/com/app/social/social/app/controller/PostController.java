package com.app.social.social.app.controller;

import com.app.social.social.app.payload.PostDto;
import com.app.social.social.app.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String , Object>> getAll(
            @RequestParam(value = "page" , defaultValue = "1" , required = false) int page,
            @RequestParam(value = "pageSize" , defaultValue = "10" , required = false) int pageSize,
            @RequestParam(value = "sortBy" , defaultValue = "id" , required = false) String sortBy
    ){
        return new ResponseEntity<>(postService.getAllPosts(page , pageSize , sortBy) , HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(postService.getPostById(id) , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable(name =  "id")  Long id , @RequestBody PostDto model){
        return ResponseEntity.ok(postService.updatePost(id , model));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<PostDto> deletePost(@PathVariable("id") Long id){
        return ResponseEntity.ok(postService.deletePost(id));
    }
}
