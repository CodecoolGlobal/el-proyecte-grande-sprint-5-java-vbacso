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
import java.util.stream.Collector;
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
    public User editUserName(User user) {
        data.stream().filter(userU -> userU.getId().equals(user.getId())).findFirst().orElseThrow(NoSuchElementException::new)
                .setName(user.getName());
        return user;
    }

    @Override
    public User findUserById(UUID userId) {
        return data.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
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
        data.remove(data.stream().filter(userU -> userU.getId().equals(user.getId())).findFirst().orElseThrow(NoSuchElementException::new));
        return user;
        }


    @Override
    public Set<Post> getAllPost() {
        throw new UnsupportedOperationException("Not implemented method: (addPost) in class: (UserDaoMem)");
        //TODO: (fergencszeno, 2022. 10. 13.) Implement method: (addPost) for class: (UserDaoMem)
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
