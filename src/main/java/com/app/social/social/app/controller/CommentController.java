package com.app.social.social.app.controller;

import com.app.social.social.app.config.GlobalExceptionHandler;
import com.app.social.social.app.payload.CommentDto;
import com.app.social.social.app.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post/{id}")
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/comments")
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody(required = false) CommentDto comment , BindingResult bindingResult , @PathVariable(name = "id") Long postId){
        GlobalExceptionHandler.checkAndFire(comment , bindingResult);
        CommentDto responseComment = commentService.createComment(postId , comment);
        return new ResponseEntity<>(responseComment , HttpStatus.CREATED);
    }
    @GetMapping("/comments")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable(name = "id") Long postId){
        return new ResponseEntity<>(commentService.getComments(postId) , HttpStatus.OK);
    }
}
