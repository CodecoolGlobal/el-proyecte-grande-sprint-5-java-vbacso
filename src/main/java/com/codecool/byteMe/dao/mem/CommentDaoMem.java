package com.codecool.byteMe.dao.mem;

import com.codecool.byteMe.dao.CommentDao;
import com.codecool.byteMe.dao.UserDao;
import com.codecool.byteMe.model.User;
import com.codecool.byteMe.model.postable.Comment;
import com.codecool.byteMe.model.postable.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CommentDaoMem implements CommentDao {

    private UserDao userDao;

    public CommentDaoMem() {
        this.userDao = new UserDaoMem();
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Set<Comment> getAll() {
        return userDao.getAll()
                .stream()
                .map(User::getPosts)
                .flatMap(Set::stream)
                .map(Post::getComments)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public Comment add(Comment comment) {
        if (comment.getPostId() == null && comment.getUserId() == null)
            throw new IllegalArgumentException("Missing object attribute: userId and postId can't be null.");
        return userDao.getAll()
                .stream()
                .map(User::getPosts)
                .flatMap(Set::stream)
                .filter(post -> post.getId().equals(comment.getPostId()))
                .findFirst().orElseThrow(NoSuchElementException::new)
                .addComment(comment);

    }

    @Override
    public Comment findById(UUID commentId) {
        throw new UnsupportedOperationException("Not implemented method: (findById) in class: (CommentDaoMem)");
        //TODO: (fekete, 2022. 10. 15.) Implement method: (findById) for class: (CommentDaoMem)
    }

    @Override
    public Set<Comment> findByUserId(UUID userId) {
        throw new UnsupportedOperationException("Not implemented method: (findByUserId) in class: (CommentDaoMem)");
        //TODO: (fekete, 2022. 10. 15.) Implement method: (findByUserId) for class: (CommentDaoMem)
    }

    @Override
    public Set<Comment> findByPostId(UUID postId) {
        throw new UnsupportedOperationException("Not implemented method: (findByPostId) in class: (CommentDaoMem)");
        //TODO: (fekete, 2022. 10. 15.) Implement method: (findByPostId) for class: (CommentDaoMem)
    }

    @Override
    public Comment edit(Comment comment) {
        throw new UnsupportedOperationException("Not implemented method: (edit) in class: (CommentDaoMem)");
        //TODO: (fekete, 2022. 10. 15.) Implement method: (edit) for class: (CommentDaoMem)
    }

    @Override
    public Comment delete(UUID commentId) {
        throw new UnsupportedOperationException("Not implemented method: (delete) in class: (CommentDaoMem)");
        //TODO: (fekete, 2022. 10. 15.) Implement method: (delete) for class: (CommentDaoMem)
    }
}
