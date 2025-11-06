package com.example.main.service;

import com.example.main.entity.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentService {
    Comment createComment(Comment comment, Long postId, Long userId, Long parentId);
    List<Comment> getCommentsByPost(Long postId);
    Optional<Comment> getCommentById(Long id);
    void deleteComment(Long id);
}
