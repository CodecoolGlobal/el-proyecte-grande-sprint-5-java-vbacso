package com.codecool.byteMe.controller;

import com.codecool.byteMe.dao.UserDao;
import com.codecool.byteMe.model.postable.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private UserDao userDao;

    @Autowired
    private void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("all")
    public Set<Comment> getAllComment() {
        return userDao.getAllComment();
    }

    @GetMapping("find/{commentId}")
    public Comment find(@PathVariable UUID commentId) {
        return userDao.findCommentById(commentId);
    }

    @PostMapping("add")
    public Comment add(@RequestBody Comment comment) {
        return userDao.addComment(comment);
    }

    @PutMapping("update")
    public Comment update(@RequestBody Comment comment) {
        return userDao.editComment(comment);
    }

    @DeleteMapping("delete")
    public Comment delete(@RequestBody Comment comment) {
        return userDao.deleteComment(comment.getId());
    }
}
