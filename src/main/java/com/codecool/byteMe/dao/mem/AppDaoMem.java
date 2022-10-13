package com.codecool.byteMe.dao.mem;

import com.codecool.byteMe.dao.AppDao;
import com.codecool.byteMe.model.User;
import com.codecool.byteMe.model.postable.Comment;
import com.codecool.byteMe.model.postable.Post;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Component("appDaoMem")
public class AppDaoMem implements AppDao {

    private static Set<User> data;

    private AppDaoMem(Set<User> users) {
        data = new HashSet<>();
        data.addAll(users);
    }

    @Override
    public Set<User> getAllUser() {
        return data;
    }

    @Override
    public User addUser(User user) {
        data.add(user);
        return user;
    }

    @Override
    public User editUser(User user) {
        throw new UnsupportedOperationException("Not implemented method: (editUser) in class: (UserDaoMem)");
        //TODO: (fergencszeno, 2022. 10. 13.) Implement method: (editUser) for class: (UserDaoMem)
    }

    @Override
    public User findUserById(UUID userId) {
        throw new UnsupportedOperationException("Not implemented method: (findUserById) in class: (UserDaoMem)");
        //TODO: (fergencszeno, 2022. 10. 13.) Implement method: (findUserById) for class: (UserDaoMem)
    }

    @Override
    public User findUserByEmail(String email) {
        return data.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User deleteUser(User user) {
        throw new UnsupportedOperationException("Not implemented method: (deleteUser) in class: (UserDaoMem)");
        //TODO: (fergencszeno, 2022. 10. 13.) Implement method: (deleteUser) for class: (UserDaoMem)
    }


    @Override
    public Set<Post> getAllPost() {
        throw new UnsupportedOperationException("Not implemented method: (getAllPost) in class: (UserDaoMem)");
        //TODO: (fergencszeno, 2022. 10. 13.) Implement method: (getAllPost) for class: (UserDaoMem)
    }

    @Override
    public Post addPost(Post post) {
        throw new UnsupportedOperationException("Not implemented method: (addPost) in class: (UserDaoMem)");
        //TODO: (fergencszeno, 2022. 10. 13.) Implement method: (addPost) for class: (UserDaoMem)
    }

    @Override
    public Post editPost(Post post) {
        throw new UnsupportedOperationException("Not implemented method: (editPost) in class: (UserDaoMem)");
        //TODO: (fergencszeno, 2022. 10. 13.) Implement method: (editPost) for class: (UserDaoMem)
    }

    @Override
    public Post findPostById(UUID postId) {
        throw new UnsupportedOperationException("Not implemented method: (findPostById) in class: (UserDaoMem)");
        //TODO: (fergencszeno, 2022. 10. 13.) Implement method: (findPostById) for class: (UserDaoMem)
    }

    @Override
    public Set<Post> findPostsByUserId(UUID userId) {
        return new HashSet<>(data.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new)
                .getPosts());
    }

    @Override
    public Post deletePost(UUID postId) {
        return getAllPost().stream()
                .filter(post -> post.getId().equals(postId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }


    @Override
    public Set<Comment> getAllComment() {
        return data.stream()
                .map(User::getComments)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public Comment addComment(Comment comment) {
        data.stream()
                .filter(user -> user.getId().equals(comment.getUserId()))
                .findFirst().orElseThrow(NoSuchElementException::new)
                .addComment(comment);
        data.stream()
                .map(User::getPosts)
                .flatMap(Set::stream)
                .filter(post -> post.getId().equals(comment.getPostId()))
                .findFirst().orElseThrow(NoSuchElementException::new)
                .addComment(comment);
        return comment;
    }

    @Override
    public Comment findCommentById(UUID commentId) {
        return data.stream()
                .map(User::getComments)
                .flatMap(Set::stream)
                .filter(comment -> comment.getId().equals(commentId))
                .findFirst().orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Set<Comment> findCommentsByUser(User user) {
        return data.stream()
                .filter(user1 -> user1.getId().equals(user.getId()))
                .findFirst().orElseThrow(NoSuchElementException::new)
                .getComments();
    }

    @Override
    public Set<Comment> findCommentsByPostId(UUID postId) {
        return getAllComment().stream()
                .filter(comment -> comment.getPostId().equals(postId))
                .collect(Collectors.toSet());
    }

    @Override
    public Comment editComment(Comment comment) {
        Comment editableComment = findCommentById(comment.getId());
        editableComment.editComment(comment);
        return editableComment;
    }

    @Override
    public Comment deleteComment(UUID commentId) {
        Comment comment = findCommentById(commentId);
        data.stream()
                .filter(user -> user.getId().equals(comment.getUserId()))
                .findFirst().orElseThrow(NoSuchElementException::new)
                .removeComment(comment);
        data.stream()
                .map(User::getPosts)
                .flatMap(Set::stream)
                .filter(post -> post.getId().equals(comment.getPostId()))
                .findFirst().orElseThrow(NoSuchElementException::new)
                .removeComment(comment);
        return comment;
    }
}
