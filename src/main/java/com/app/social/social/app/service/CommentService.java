package com.app.social.social.app.service;

import com.app.social.social.app.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long postId , CommentDto comment);

    List<CommentDto> getComments(Long postId);

    CommentDto getCommentById(Long id);

    CommentDto updateComment(Long commentId , CommentDto comment);

}
