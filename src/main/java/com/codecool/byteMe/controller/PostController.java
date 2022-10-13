package com.codecool.byteMe.controller;

import com.codecool.byteMe.dao.mem.AppDaoMem;
import com.codecool.byteMe.model.postable.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private AppDaoMem appDaoMem;

    @GetMapping("all")
    public Set<Post> getAllPost() {
        return appDaoMem.getAllPost();
    }

    @PostMapping("add")
    public Post addPost(@RequestBody Post post) {
        return appDaoMem.addPost(post);
    }

    @PostMapping("edit")
    public Post editPost(Post newPost) {
        return appDaoMem.editPost(newPost);
    }

    @GetMapping("{postId}")
    public Post findPostById(@PathVariable UUID postId) {
        return appDaoMem.findPostById(postId);
    }

    @GetMapping("/user/{userId}")
    public Set<Post> findPostsByUserId(@PathVariable UUID userId) {
        return appDaoMem.findPostsByUserId(userId);
    }

    @DeleteMapping("delete")
    public Post deletePost(@RequestBody Post post) {
        return appDaoMem.deletePost(post.getId());
    }
}
