package com.codecool.byteMe.dao;

import com.codecool.byteMe.model.User;
import com.codecool.byteMe.model.postable.Comment;
import com.codecool.byteMe.model.postable.Post;

import java.util.Set;
import java.util.UUID;

public interface UserDao {
    Set<User> getAllUser();
    Set<Comment> getAllComment();

    User addUser(User user);

    User edit(User user);

    User findByEmail(String email);

    User findUserById(UUID userId);


    User deleteUser(User user);


    Comment addComment(Comment comment);

    Set<Comment> findCommentsByUser(User user);

    Set<Comment> findCommentsByPostId(UUID postId);

    Comment findCommentById(UUID commentId);

    Comment editComment(Comment comment);

    Comment deleteComment(UUID commentId);

}
