package com.codecool.byteMe.controller;

import com.codecool.byteMe.dao.mem.AppDaoMem;
import com.codecool.byteMe.model.User;
import com.codecool.byteMe.model.postable.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private AppDaoMem appDaoMem;

    @PostMapping("/add")
    public Post addPost(@RequestBody Post post) {
        return appDaoMem.addPost(post);
    }

    @GetMapping("/find")
    public Set<Post> findByUser(@RequestBody User user) {
        return appDaoMem.findPostsByUserId(user.getId());
    }

    @DeleteMapping("/delete")
    public Post deletePost(@RequestBody Post post) {
        return appDaoMem.deletePost(post.getId());
    }
}
