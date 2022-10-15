package com.codecool.byteMe.dao;

import com.codecool.byteMe.model.postable.Comment;

import java.util.Set;
import java.util.UUID;

public interface CommentDao {

    Set<Comment> getAll();

    Comment add(Comment comment);

    Comment findById(UUID commentId);

    Set<Comment> findByUserId(UUID userId);

    Set<Comment> findByPostId(UUID postId);

    Comment edit(Comment comment);

    Comment delete(UUID commentId);
}
