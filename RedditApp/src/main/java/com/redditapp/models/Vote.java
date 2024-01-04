package com.redditapp.models;

import javax.persistence.*;

@Entity
@Table(name = "votes")
public class Vote {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private boolean up;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    private  RedditPost post;


    public Vote(boolean up, User user, RedditPost post) {
        this.up = up;
        this.user = user;
        this.post = post;
    }

    public Vote() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RedditPost getPost() {
        return post;
    }

    public void setPost(RedditPost post) {
        this.post = post;
    }
}
