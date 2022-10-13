package com.codecool.byteMe.dao.mem;

import com.codecool.byteMe.dao.PostDao;
import com.codecool.byteMe.model.postable.Post;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PostDaoMem implements PostDao {

    private Set<Post> data;

    private PostDaoMem(Set<Post> posts) {
        this.data = posts;
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
