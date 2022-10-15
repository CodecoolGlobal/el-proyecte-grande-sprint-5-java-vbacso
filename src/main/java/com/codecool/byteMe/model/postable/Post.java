package com.codecool.byteMe.model.postable;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Post extends Postable {

    public static final int VOTE = 0;
    private String title;
    private Set<Comment> comments;

    public Post(UUID userId, String title, String body) {
        super(userId, body, VOTE);
        this.title = title;
        comments = new HashSet<>();
    }

    public Comment addComment(Comment comment) {
        comments.add(comment);
        return comment;
    }

    public String getTitle() {
        return title;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
    }

    public Post editPost(Post newPost) {
        this.title = newPost.title;
        this.body = newPost.body;
        return this;
    }
}
