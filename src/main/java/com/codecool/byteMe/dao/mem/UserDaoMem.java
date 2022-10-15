package com.codecool.byteMe.dao.mem;

import com.codecool.byteMe.dao.UserDao;
import com.codecool.byteMe.model.User;
import com.codecool.byteMe.model.postable.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Component
public class UserDaoMem implements UserDao {

    private Set<User> data;

    public UserDaoMem() {
        data = new HashSet<>();
    }

    @Autowired
    @Qualifier("baseUsers")
    public void setData(Set<User> data) {
        this.data = data;
    }

    @Override
    public Set<User> getAll() {
        return data;
    }

    @Override
    public User add(User user) {
        data.add(user);
        return user;
    }

    @Override
    public User edit(User user) {
        data.stream().filter(userU -> userU.getId().equals(user.getId())).findFirst().orElseThrow(NoSuchElementException::new)
                .setName(user.getName());
        return user;
    }

    @Override
    public User findById(UUID userId) {
        return data.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User findByEmail(String email) {
        return data.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User delete(UUID userId) {
        User userToDelete = data.stream().filter(user -> user.getId().equals(userId)).findFirst().orElseThrow(NoSuchElementException::new);
        data.remove(userToDelete);
        return userToDelete;
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
        if (comment.getPostId() == null && comment.getUserId() == null)
            throw new IllegalArgumentException("Missing object attribute: userId and postId can't be null.");
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
