package com.codecool.byteMe.service;

import com.codecool.byteMe.dao.CommentDao;
import com.codecool.byteMe.dao.mem.CommentDaoMem;
import com.codecool.byteMe.model.postable.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service("commentService")

public class CommentService {
    private CommentDao commentDao;

    public CommentService() {
        this.commentDao = new CommentDaoMem();
    }

    @Autowired
    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public Set<Comment> getAll() {
        return commentDao.getAll();
    }

    public Comment add(Comment comment) {
        return commentDao.add(comment);
    }

    public Comment findById(UUID commentId) {
        return commentDao.findById(commentId);
    }

    public Set<Comment> findByUserId(UUID userId) {
        return commentDao.findByUserId(userId);
    }

    public Comment edit(Comment comment) {
        return commentDao.edit(comment);
    }

    public Comment delete(UUID commentId) {
        return commentDao.delete(commentId);
    }
}

