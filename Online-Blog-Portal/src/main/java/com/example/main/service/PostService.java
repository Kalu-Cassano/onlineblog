package com.example.main.service;

import com.example.main.entity.Post;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PostService {
    Post createPost(Post post, MultipartFile file) throws IOException;
    List<Post> getAllPosts();
    Optional<Post> getPostById(Long id);
    Post updatePost(Long id, Post updatedPost);
    void deletePost(Long id);
}
