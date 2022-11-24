package com.codecool.byteMe.controller;

import com.codecool.byteMe.model.postable.Comment;
import com.codecool.byteMe.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("all")
    public List<Comment> getAllComment() {
        return commentService.getAll();
    }

    @PostMapping("add")
    public Comment add(@RequestBody Comment comment) {
        return commentService.add(comment);
    }

    @GetMapping("{commentId}")
    public Comment find(@PathVariable Long commentId) {
        return commentService.findById(commentId);
    }

    @GetMapping("user/{userId}")
    public List<Comment> findByUserId(@PathVariable Long userId) {
        return commentService.findByUserId(userId);
    }

    @GetMapping("post/{postId}")
    public List<Comment> findByPostId(@PathVariable Long postId) {
        return commentService.findByPostId(postId);
    }

    @PutMapping("update")
    public Comment update(@RequestBody Comment comment) {
        return commentService.edit(comment);
    }

    @DeleteMapping("delete/{commentId}")
    public void delete(@PathVariable Long commentId) {
        commentService.delete(commentId);
    }
}
