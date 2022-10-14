package com.codecool.byteMe.dao.mem;

import com.codecool.byteMe.dao.PostDao;
import com.codecool.byteMe.dao.UserDao;
import com.codecool.byteMe.model.User;
import com.codecool.byteMe.model.postable.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PostDaoMem implements PostDao {

    private Set<Post> data;

    @Autowired
    private void setData(UserDao userDao) {

        this.data = userDao.getAllUser()
                .stream()
                .map(User::getPosts)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Post> getAllPost() {
        return data;
    }

    @Override
    public Post findById(UUID postId) {
        Set<Post> posts = getAllPost();
        return posts.stream()
                .filter(post -> post.getId().equals(postId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Post addPost(Post post) {
        data.add(post);
        return post;

    }

    @Override
    public Post editPost(Post post) {
        return findById(post.getId()).editPost(post);
    }


    @Override
    public Set<Post> findPostsByUserId(UUID userId) {
        return data.stream()
                .filter(post -> post.getUserId().equals(userId))
                .collect(Collectors.toSet());
    }

    @Override
    public Post deletePost(UUID postId) {
        Post removablePost = data.stream()
                .filter(post -> post.getId().equals(postId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        data.remove(removablePost);
        return removablePost;

    }


}
