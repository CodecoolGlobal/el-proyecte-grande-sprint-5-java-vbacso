package com.codecool.byteMe.dao;

import com.codecool.byteMe.model.User;
import com.codecool.byteMe.model.postable.Comment;
import com.codecool.byteMe.model.postable.Post;

import java.util.Set;
import java.util.UUID;

public interface AppDao {
    Set<User> getAllUser();

    Set<Post> getAllPost();

    Set<Comment> getAllComment();

    User addUser(User user);

    User editUser(User user);

    User findUserByEmail(String email);

    User findUserById(UUID userId);


    User deleteUser(User user);

    Set<Post> findPostsByUserId(UUID userId);

    Post findPostById(UUID postId);

    Post addPost(Post post);

    Post editPost(Post post);

    Post deletePost(UUID postId);

    Comment addComment(Comment comment);

    Set<Comment> findCommentsByUser(User user);

    Set<Comment> findCommentsByPostId(UUID postId);

    Comment findCommentById(UUID commentId);

    Comment editComment(Comment comment);

    Comment deleteComment(UUID commentId);

}
