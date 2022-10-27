package com.codecool.byteMe.model.postable;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Postable {

    protected UUID id;
    protected UUID userId;
    protected String username;
    protected String body;
    protected int vote;
    protected LocalDateTime created;

    public Postable(UUID userId, String body, int vote, String username) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.body = body;
        this.vote = vote;
        this.username = username;
        this.created = LocalDateTime.now();
    }

    public LocalDateTime getCreated() {
        return created;
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

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
