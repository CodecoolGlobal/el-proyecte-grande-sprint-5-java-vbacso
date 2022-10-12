package com.codecool.byteMe.dao.mem;

import com.codecool.byteMe.dao.PostDao;
import com.codecool.byteMe.model.postable.Post;

import java.util.Set;
import java.util.UUID;

public class PostDaoMem implements PostDao {
    @Override
    public Post add(Post post) {
        throw new UnsupportedOperationException("Not implemented method: (add) in class: (PostDaoMem)");
        //TODO: (fergencszeno, 2022. 10. 12.) Implement method: (add) for class: (PostDaoMem)
    }

    @Override
    public Set<Post> findByUser(UUID userId) {
        throw new UnsupportedOperationException("Not implemented method: (findByUser) in class: (PostDaoMem)");
        //TODO: (fergencszeno, 2022. 10. 12.) Implement method: (findByUser) for class: (PostDaoMem)
    }

    @Override
    public Post editPost(Post post) {
        throw new UnsupportedOperationException("Not implemented method: (editPost) in class: (PostDaoMem)");
        //TODO: (fergencszeno, 2022. 10. 12.) Implement method: (editPost) for class: (PostDaoMem)
    }

    @Override
    public void deletePost(UUID postId) {
        throw new UnsupportedOperationException("Not implemented method: (deletePost) in class: (PostDaoMem)");
        //TODO: (fergencszeno, 2022. 10. 12.) Implement method: (deletePost) for class: (PostDaoMem)
    }
}
