package com.app.social.social.app.service.impl;

import com.app.social.social.app.entity.Comment;
import com.app.social.social.app.entity.Post;
import com.app.social.social.app.exception.ResourceNotFoundException;
import com.app.social.social.app.payload.CommentDto;
import com.app.social.social.app.repositry.CommentRepository;
import com.app.social.social.app.repositry.PostRepository;
import com.app.social.social.app.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    @Override
    public CommentDto createComment(Long postId, CommentDto comment) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("Post" , "id" , postId)
        );
        Comment commentEntity = comment.toEntity();
        commentEntity.setPost(post);
        commentRepository.save(commentEntity);
        return commentEntity.toDto();
    }

    @Override
    public List<CommentDto> getComments(Long postId) {
        List<Comment> comments = commentRepository.getCommentByPostId(postId);
        List<CommentDto> commentsDto = new ArrayList<>();
        for(Comment comment : comments)
            commentsDto.add(comment.toDto());
        return commentsDto;
    }
}
