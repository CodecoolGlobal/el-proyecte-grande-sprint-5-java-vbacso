package com.codecool.byteMe.service;

import com.codecool.byteMe.dao.CommentRepository;
import com.codecool.byteMe.model.postable.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("commentService")

public class CommentService {
    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    public Comment add(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment findById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new NoSuchElementException("No comment with given id"));
    }

    public List<Comment> findByUserId(Long userId) {
        return commentRepository.findByUserId(userId);
    }

    public List<Comment> findByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public Comment edit(Comment comment) {
        return commentRepository.save(comment);
    }

    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}

