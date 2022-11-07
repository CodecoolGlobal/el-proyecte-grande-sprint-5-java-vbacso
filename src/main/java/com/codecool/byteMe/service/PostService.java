package com.codecool.byteMe.service;

import com.codecool.byteMe.dao.PostRepository;
import com.codecool.byteMe.model.postable.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("postService")
public class PostService {
    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public Post add(Post post) {
        return postRepository.save(post);
    }

    public Post findById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new NoSuchElementException("No post with ginven id"));
    }

    public List<Post> findByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    public Post edit(Post post) {
        return postRepository.save(post);
    }

    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }

//    public List<Post> getFeedPosts(UUID userId) {
//        return postRepository.getFeedPosts(userId);
//    }
}
