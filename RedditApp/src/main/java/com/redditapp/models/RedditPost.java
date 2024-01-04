package com.redditapp.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Entity
@Table(name = "posts")
public class RedditPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String title;
    private String url;
    private long numOfVotes;
    private String date;
    private String author_username;

    public RedditPost() {
    }

    public RedditPost(String title, String url, String author_username) {
        this.title = title;
        this.url = url;
        this.author_username = author_username;
        this.numOfVotes = 0;
        LocalDate dateObj = LocalDate.now();
        this.date = dateObj.toString();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getNumOfVotes() {
        return numOfVotes;
    }

    public void setNumOfVotes(long numOfVotes) {
        this.numOfVotes = numOfVotes;
    }

    public String getAuthor_username() {
        return author_username;
    }

    public void setAuthor_username(String author_username) {
        this.author_username = author_username;
    }
}
