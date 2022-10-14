package com.codecool.byteMe.dao;

import com.codecool.byteMe.model.User;
import com.codecool.byteMe.model.postable.Comment;

import java.util.Set;
import java.util.UUID;

public interface UserDao {
    Set<User> getAll();

    Set<Comment> getAllComment();

    User add(User user);

    User edit(User user);

    User findByEmail(String email);

    User findById(UUID userId);

    User delete(UUID userId);


    Comment addComment(Comment comment);

    Set<Comment> findCommentsByUser(User user);

    Set<Comment> findCommentsByPostId(UUID postId);

    Comment findCommentById(UUID commentId);

    Comment editComment(Comment comment);

    Comment deleteComment(UUID commentId);

}
