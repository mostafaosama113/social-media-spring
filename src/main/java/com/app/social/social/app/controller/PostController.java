package com.app.social.social.app.controller;

import com.app.social.social.app.config.GlobalExceptionHandler;
import com.app.social.social.app.exception.NotValidRequestException;
import com.app.social.social.app.payload.PostDto;
import com.app.social.social.app.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.app.social.social.app.utils.AppConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody(required = false) PostDto model , BindingResult bindingResult) {
        GlobalExceptionHandler.checkAndFire(model , bindingResult);
        return new ResponseEntity<>(postService.createPost(model) , HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Map<String , Object>> getAll(
            @RequestParam(value = "page" , defaultValue = DEFAULT_PAGE_NUM , required = false) int page,
            @RequestParam(value = "pageSize" , defaultValue = DEFAULT_PAGE_SIZE , required = false) int pageSize,
            @RequestParam(value = "sortBy" , defaultValue = DEFAULT_SORT_BY , required = false) String sortBy
    ){
        return new ResponseEntity<>(postService.getAllPosts(page , pageSize , sortBy) , HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(postService.getPostById(id) , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable(name =  "id")  Long id , @RequestBody(required = false) PostDto model){
        GlobalExceptionHandler.checkAndFire(model);
        return ResponseEntity.ok(postService.updatePost(id , model));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<PostDto> deletePost(@PathVariable("id") Long id){
        return ResponseEntity.ok(postService.deletePost(id));
    }
}
