package com.example.main.service.impl;

import com.example.main.entity.Post;
import com.example.main.repository.PostRepository;
import com.example.main.service.FileStorageService;
import com.example.main.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Override
    public Post createPost(Post post, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String fileUrl = fileStorageService.saveFile(file);
            post.setThumbnailUrl(fileUrl);
        }
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post updatePost(Long id, Post updatedPost) {
        return postRepository.findById(id)
                .map(post -> {
                    post.setTitle(updatedPost.getTitle());
                    post.setContent(updatedPost.getContent());
                    post.setCategory(updatedPost.getCategory());
                    post.setStatus(updatedPost.getStatus());
                    post.setFeatured(updatedPost.getFeatured());
                    post.setPublishedAt(updatedPost.getPublishedAt());
                    return postRepository.save(post);
                })
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
