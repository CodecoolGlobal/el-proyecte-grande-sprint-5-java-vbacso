package com.codecool.byteMe.dao.mem;

import com.codecool.byteMe.dao.CommentDao;
import com.codecool.byteMe.dao.UserDao;
import com.codecool.byteMe.model.postable.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

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
        throw new UnsupportedOperationException("Not implemented method: (getAll) in class: (CommentDaoMem)");
        //TODO: (fekete, 2022. 10. 15.) Implement method: (getAll) for class: (CommentDaoMem)
    }

    @Override
    public Comment add(Comment comment) {
        throw new UnsupportedOperationException("Not implemented method: (add) in class: (CommentDaoMem)");
        //TODO: (fekete, 2022. 10. 15.) Implement method: (add) for class: (CommentDaoMem)
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
