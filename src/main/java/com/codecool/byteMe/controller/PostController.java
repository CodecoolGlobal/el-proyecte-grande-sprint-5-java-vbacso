package com.codecool.byteMe.controller;

import com.codecool.byteMe.model.postable.Post;
import com.codecool.byteMe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/post")
public class PostController {

    private PostService postService;

    public PostController() {
        this.postService = new PostService();
    }

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("all")
    public Set<Post> getAllPost() {
        return postService.getAll();
    }

    @PostMapping("add")
    public Post addPost(@RequestBody Post post) {
        return postService.add(post);
    }

    @PutMapping("edit")
    public Post editPost(@RequestBody Post post) {
        return postService.edit(post);
    }

    @GetMapping("{postId}")
    public Post findPostById(@PathVariable UUID postId) {
        return postService.findById(postId);
    }

    @GetMapping("user/{userId}")
    public Set<Post> findPostsByUserId(@PathVariable UUID userId) {
        return postService.findByUserId(userId);
    }

    @DeleteMapping("delete/{postId}")
    public Post deletePost(@PathVariable UUID postId) {
        return postService.delete(postId);
    }

    @GetMapping("friends/{userId}")
    public Set<Post> getAllFriendsPosts(@PathVariable UUID userId) {
        return postService.getAllFriendsPosts(userId);
    }
}
