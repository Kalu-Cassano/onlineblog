package com.example.main.controller;

import com.example.main.entity.Comment;
import com.example.main.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(
            @RequestBody Comment comment,
            @RequestParam Long postId,
            @RequestParam Long userId,
            @RequestParam(required = false) Long parentId
    ) {
        Comment savedComment = commentService.createComment(comment, postId, userId, parentId);
        return ResponseEntity.ok(savedComment);
    }

    // READ
    @GetMapping("/post/{postId}")
    public List<Comment> getCommentsByPost(@PathVariable Long postId) {
        return commentService.getCommentsByPost(postId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        return commentService.getCommentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
