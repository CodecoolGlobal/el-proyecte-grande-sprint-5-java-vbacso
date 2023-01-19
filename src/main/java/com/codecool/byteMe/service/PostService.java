package com.codecool.byteMe.service;

import com.codecool.byteMe.dao.GroupRepository;
import com.codecool.byteMe.dao.PostRepository;
import com.codecool.byteMe.dao.UserRepository;
import com.codecool.byteMe.model.Group;
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
    private GroupRepository groupRepository;
    private UserService userService;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository, GroupRepository groupRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.userService = userService;
    }

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public Post add(Post post) {
        return postRepository.save(post);
    }

    public Post findById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new NoSuchElementException("No post with ginven id"));
    }

    public List<Post> findByUserId(Long userId) {
        return postRepository.findByUserIdAndGroupIdIsNull(userId);
    }

    public Post edit(Post post) {
        return postRepository.save(post);
    }

    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }

    public List<Post> getFeedPosts(Long userId) {
        List<Long> userIdsForFeed = new ArrayList<>(userService.getFriendIds(userId));
        userIdsForFeed.add(userId);
        System.out.println(postRepository.findByUser_IdInAndGroupIdIsNull(userIdsForFeed));
        return postRepository.findByUser_IdInAndGroupIdIsNull(userIdsForFeed);
    }

    public Post addPostToGroup(Post post, Long groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException("Invalid id: " + groupId));
        post.setGroup(group);
        return postRepository.save(post);
    }

    public List<Post> findByGroupId(Long groupId) {
        return postRepository.findByGroupId(groupId);
    }
}
