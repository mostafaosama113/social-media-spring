package com.app.social.social.app.repositry;

import com.app.social.social.app.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment , Long> {
    List<Comment> getCommentByPostId(long postId);
}
