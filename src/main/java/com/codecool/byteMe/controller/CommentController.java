package com.codecool.byteMe.controller;

import com.codecool.byteMe.dao.AppDao;
import com.codecool.byteMe.model.postable.Comment;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private AppDao appDao;

    private CommentController(AppDao appDao) {
        this.appDao = appDao;
    }

    @GetMapping("getAllComment")
    public Set<Comment> getAllComment() {
        return appDao.getAllComment();
    }

    @GetMapping("find/{commentId}")
    public Comment find(@PathVariable UUID commentId) {
        return appDao.findCommentById(commentId);
    }

    @PostMapping("add")
    public Comment add(@RequestBody Comment comment) {
        return appDao.addComment(comment);
    }

    @PutMapping("update")
    public Comment update(@RequestBody Comment comment) {
        return appDao.editComment(comment);
    }
}
