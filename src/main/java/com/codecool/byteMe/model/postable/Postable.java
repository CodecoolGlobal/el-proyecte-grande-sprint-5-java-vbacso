package com.codecool.byteMe.model.postable;

import java.util.UUID;

public abstract class Postable {

    protected UUID id;
    protected UUID userId;
    protected String username;
    protected String body;
    protected int vote;

    public Postable(UUID userId, String body, int vote, String username) {
        id = UUID.randomUUID();
        this.userId = userId;
        this.body = body;
        this.vote = vote;
        this.username = username;
    }

    public void increaseVote() {
        vote++;
    }

    public void decreaseVote() {
        vote--;
    }

    public UUID getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public int getVote() {
        return vote;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "PostAbles{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", vote=" + vote +
                '}';
    }
}
