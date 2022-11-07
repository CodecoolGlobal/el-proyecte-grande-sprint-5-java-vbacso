package com.codecool.byteMe.controller;

import com.codecool.byteMe.model.postable.Post;
import com.codecool.byteMe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

    private PostService postService;


    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("all")
    public List<Post> getAllPost() {
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
    public Post findPostById(@PathVariable Long postId) {
        return postService.findById(postId);
    }

    @GetMapping("user/{userId}")
    public List<Post> findPostsByUserId(@PathVariable Long userId) {
        return postService.findByUserId(userId);
    }

    @DeleteMapping("delete/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.delete(postId);
    }

    @GetMapping("feed/{userId}")
    public List<Post> getFeedPosts(@PathVariable Long userId) {
        System.out.println(postService.getFeedPosts(userId));
        return postService.getFeedPosts(userId);
    }
}
