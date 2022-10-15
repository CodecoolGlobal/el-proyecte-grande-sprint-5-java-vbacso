package com.codecool.byteMe.controller;

import com.codecool.byteMe.model.postable.Comment;
import com.codecool.byteMe.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private CommentService commentService;

    public CommentController() {
        this.commentService = new CommentService();
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("all")
    public Set<Comment> getAllComment() {
        return commentService.getAll();
    }

    @PostMapping("add")
    public Comment add(@RequestBody Comment comment) {
        return commentService.add(comment);
    }

    @GetMapping("{commentId}")
    public Comment find(@PathVariable UUID commentId) {
        return commentService.findById(commentId);
    }

    @GetMapping("user/{userId}")
    public Set<Comment> findByUserId(@PathVariable UUID userId) {
        return commentService.findByUserId(userId);
    }

    @GetMapping("post/{postId}")
    public Set<Comment> findByPostId(@PathVariable UUID postId) {
        return commentService.findByPostId(postId);
    }

    @PutMapping("update")
    public Comment update(@RequestBody Comment comment) {
        return commentService.edit(comment);
    }

    @DeleteMapping("delete/{commentId}")
    public Comment delete(@PathVariable UUID commentId) {
        return commentService.delete(commentId);
    }
}
