package com.codecool.byteMe.service;

import com.codecool.byteMe.dao.PostRepository;
import com.codecool.byteMe.model.postable.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service("postService")
public class PostService {
    private PostRepository postRepository;
    private UserService userService;

    @Autowired
    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    public Post findById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new NoSuchElementException("No post with given id"));
    }

    public List<Post> findByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    public Post editPost(Post post) {
        return postRepository.save(post);
    }

    public void deletePostById(Long postId) {
        postRepository.deleteById(postId);
    }

    public List<Post> getFeedPosts(Long userId) {
        List<Long> userIdsForFeed = new ArrayList<>(userService.getFriendIds(userId));
        userIdsForFeed.add(userId);
        return postRepository.findByUser_IdIn(userIdsForFeed);
    }
}
