package com.codecool.byteMe.dao.mem;

import com.codecool.byteMe.dao.CommentDao;
import com.codecool.byteMe.model.postable.Comment;

import java.util.Set;
import java.util.UUID;

public class CommentDaoMem implements CommentDao {
    @Override
    public Comment add(Comment comment) {
        throw new UnsupportedOperationException("Not implemented method: (add) in class: (CommentDaoMem)");
        //TODO: (fergencszeno, 2022. 10. 12.) Implement method: (add) for class: (CommentDaoMem)
    }

    @Override
    public Set<Comment> findByPost(UUID postId) {
        throw new UnsupportedOperationException("Not implemented method: (findByPost) in class: (CommentDaoMem)");
        //TODO: (fergencszeno, 2022. 10. 12.) Implement method: (findByPost) for class: (CommentDaoMem)
    }

    @Override
    public Comment editComment(Comment comment) {
        throw new UnsupportedOperationException("Not implemented method: (editComment) in class: (CommentDaoMem)");
        //TODO: (fergencszeno, 2022. 10. 12.) Implement method: (editComment) for class: (CommentDaoMem)
    }

    @Override
    public void deleteComment(UUID commentId) {
        throw new UnsupportedOperationException("Not implemented method: (deleteComment) in class: (CommentDaoMem)");
        //TODO: (fergencszeno, 2022. 10. 12.) Implement method: (deleteComment) for class: (CommentDaoMem)
    }
}
