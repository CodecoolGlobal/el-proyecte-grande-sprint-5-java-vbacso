package com.codecool.byteMe.dao.mem;

import com.codecool.byteMe.dao.PostDao;
import com.codecool.byteMe.model.postable.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.codecool.byteMe.model.User.toSingleton;

@Component("postDaoMem")
public class PostDaoMem implements PostDao {

    @Autowired
    UserDaoMem userDaoMem;
    private static Set<Post> data = new HashSet<>();
    @Override
    public void add(Post post) {
        data.add(post);
    }

    @Override
    public Set<Post> findByUser(UUID userId) {
        return userDaoMem.findByUser(userId);
    }

    @Override
    public Post editPost(Post post) {
        throw new UnsupportedOperationException("Not implemented method: (editPost) in class: (PostDaoMem)");
        //TODO: (fergencszeno, 2022. 10. 12.) Implement method: (editPost) for class: (PostDaoMem)
    }

    @Override
    public void deletePost(UUID postId) {
        data.remove(data.stream().filter(post -> post.getId().equals(postId)).collect(toSingleton()));
    }
}
