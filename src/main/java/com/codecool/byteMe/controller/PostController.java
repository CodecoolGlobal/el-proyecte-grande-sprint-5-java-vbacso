package com.codecool.byteMe.controller;

import com.codecool.byteMe.model.postable.Post;
import com.codecool.byteMe.service.PostService;
import com.codecool.byteMe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/post")
public class PostController {

    private static PostService postService;
    private static UserService userService;

    @Autowired
    public void setPostService(PostService postService) {
        PostController.postService = postService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        PostController.userService = userService;
    }

    @GetMapping("all")
    public Set<Post> getAllPost() {
        return postService.getAll();
    }

    @PostMapping("add")
    public Post addPost(@RequestBody Post post) {
        userService.findById(post.getUserId()).addPost(post);
        return postService.add(post);
    }

    @PostMapping("edit")
    public Post editPost(Post post) {
        return postService.edit(post);
    }

    @GetMapping("find/{postId}")
    public Post findPostById(@PathVariable UUID postId) {
        return postService.findById(postId);
    }

    @GetMapping("/user/{userId}")
    public Set<Post> findPostsByUserId(@PathVariable UUID userId) {
        return postService.findByUserId(userId);
    }

    @DeleteMapping("delete")
    public Post deletePost(@RequestBody Post post) {
        return postService.delete(post.getId());
    }
}
