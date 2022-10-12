package com.codecool.byteMe.model.postable;


import java.util.ArrayList;
import java.util.List;

public class Post extends Postable {

    public static final int VOTE = 0;
    private String title;
    private List<Comment> comments;

    public Post(String title, String body) {
        super(body, VOTE);
        this.title = title;
        comments = new ArrayList<>();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public String getTitle() {
        return title;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
