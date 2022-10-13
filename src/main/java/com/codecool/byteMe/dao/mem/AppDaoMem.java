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
        return data.stream()
                .map(User::getPosts)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public Post addPost(Post post) {
        User user = findUserById(post.getUserId());
        user.addPost(post);
        return post;

    }

    @Override
    public Post editPost(Post newPost) {
        Post editablePost = findPostById(newPost.getId());
        editablePost.editPost(newPost);
        return editablePost;
    }

    @Override
    public Post findPostById(UUID postId) {
        Set<Post> posts = getAllPost();
        return posts.stream()
                .filter(post -> post.getId().equals(postId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
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
        Post deletePost = getAllPost().stream()
                .filter(post -> post.getId().equals(postId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        getAllPost().remove(deletePost);
        return deletePost;

    }


    @Override
    public Set<Comment> getAllComment() {
        throw new UnsupportedOperationException("Not implemented method: (getAllComment) in class: (UserDaoMem)");
        //TODO: (fergencszeno, 2022. 10. 13.) Implement method: (getAllComment) for class: (UserDaoMem)
    }

    @Override
    public Comment addComment(Comment comment) {
        throw new UnsupportedOperationException("Not implemented method: (addComment) in class: (UserDaoMem)");
        //TODO: (fergencszeno, 2022. 10. 13.) Implement method: (addComment) for class: (UserDaoMem)
    }

    @Override
    public Comment editComment(Comment comment) {
        throw new UnsupportedOperationException("Not implemented method: (editComment) in class: (UserDaoMem)");
        //TODO: (fergencszeno, 2022. 10. 13.) Implement method: (editComment) for class: (UserDaoMem)
    }

    @Override
    public Comment findCommentsById(UUID commentId) {
        throw new UnsupportedOperationException("Not implemented method: (findCommentsById) in class: (UserDaoMem)");
        //TODO: (fergencszeno, 2022. 10. 13.) Implement method: (findCommentsById) for class: (UserDaoMem)
    }

    @Override
    public Set<Comment> findCommentsByUser(User user) {
        throw new UnsupportedOperationException("Not implemented method: (findCommentsByUser) in class: (UserDaoMem)");
        //TODO: (fergencszeno, 2022. 10. 13.) Implement method: (findCommentsByUser) for class: (UserDaoMem)
    }

    @Override
    public Set<Comment> findCommentsByPostId(UUID postId) {
        throw new UnsupportedOperationException("Not implemented method: (findCommentsByPostId) in class: (UserDaoMem)");
        //TODO: (fergencszeno, 2022. 10. 13.) Implement method: (findCommentsByPostId) for class: (UserDaoMem)
    }

    @Override
    public Comment deleteComment(Comment comment) {
        throw new UnsupportedOperationException("Not implemented method: (deleteComment) in class: (UserDaoMem)");
        //TODO: (fergencszeno, 2022. 10. 13.) Implement method: (deleteComment) for class: (UserDaoMem)
    }
}
