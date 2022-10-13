package com.codecool.byteMe.controller;

import com.codecool.byteMe.model.postable.Post;
import com.codecool.byteMe.service.PostService;
import com.codecool.byteMe.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/post")
public class PostController {

    private PostService postService;
    private UserService userService;
    
    private PostController(PostService postService,UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("all")
    public Set<Post> getAllPost() {
        return postService.getAllPost();
    }

    @PostMapping("add")
    public Post addPost(@RequestBody Post post) {
        userService.findById(post.getUserId()).addPost(post);
        return postService.addPost(post);
    }

    @PostMapping("edit")
    public Post editPost(Post post) {
        return postService.editPost(post);
    }

    @GetMapping("find/{postId}")
    public Post findPostById(@PathVariable UUID postId) {
        return postService.findPostById(postId);
    }

    @GetMapping("/user/{userId}")
    public Set<Post> findPostsByUserId(@PathVariable UUID userId) {
        return postService.findPostsByUserId(userId);
    }

    @DeleteMapping("delete")
    public Post deletePost(@RequestBody Post post) {
        return postService.deletePost(post.getId());
    }
}
