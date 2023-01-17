package com.codecool.byteMe.service;

import com.codecool.byteMe.dao.PostRepository;
import com.codecool.byteMe.dao.UserRepository;
import com.codecool.byteMe.model.UserModel;
import com.codecool.byteMe.model.postable.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service("postService")
public class PostService {
    private PostRepository postRepository;
    private UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    public Post findById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new NoSuchElementException("No post with given id"));
    }

    public List<Post> findByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    public Post edit(Post post) {
        return postRepository.save(post);
    }

    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }

    public List<Post> getFeedPosts(Long userId) {
        List<Long> userIdsForFeed = new ArrayList<>(
                userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("No User with provided id"))
                        .getFriendList()
                        .stream()
                        .map(UserModel::getId).toList());
        userIdsForFeed.add(userId);
        return postRepository.findByUser_IdIn(userIdsForFeed);
    }
}
