package com.codecool.byteMe.model.postable;

import java.util.UUID;

public abstract class Postable {

    private UUID id;
    private String body;
    private int vote;

    public Postable(String body, int vote) {
        id = UUID.randomUUID();
        this.body = body;
        this.vote = vote;
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

    @Override
    public String toString() {
        return "PostAbles{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", vote=" + vote +
                '}';
    }
}
