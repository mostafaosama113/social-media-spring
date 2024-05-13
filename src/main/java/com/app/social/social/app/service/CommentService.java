package com.app.social.social.app.service;

import com.app.social.social.app.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(Long postId , CommentDto comment);
}
