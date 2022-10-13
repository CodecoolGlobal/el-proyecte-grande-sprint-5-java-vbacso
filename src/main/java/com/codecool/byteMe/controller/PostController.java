package com.codecool.byteMe.controller;

import com.codecool.byteMe.dao.mem.UserDaoMem;
import com.codecool.byteMe.model.User;
import com.codecool.byteMe.model.postable.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private UserDaoMem userDaoMem;

    @PostMapping("/add")
    public void addPost(@RequestBody Post post) {
        userDaoMem.addPost(post);
    }

    @GetMapping("/find")
    public Set<Post> findByUser(@RequestBody User user) {
        return userDaoMem.findPostsByUserId(user.getId());
    }

    @DeleteMapping("/delete")
    public void deletePost(@RequestBody Post post) {
        userDaoMem.deletePost(post.getId());
    }
}
