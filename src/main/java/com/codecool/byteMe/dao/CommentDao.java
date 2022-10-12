package com.codecool.byteMe.dao;

import com.codecool.byteMe.model.postable.Comment;

import java.util.Set;
import java.util.UUID;

public interface CommentDao {
    Comment add(Comment comment);

    Set<Comment> findByPost(UUID postId);

    Comment editComment(Comment comment);

    void deleteComment(UUID commentId);
}
