package com.redditapp.services;

import com.redditapp.models.RedditPost;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RedditPostService {
    Page<RedditPost> showAll(int pageNum);

    void vote(long post_id, String username, boolean up);

    void upvote(long id, String username);

    void downvote(long id, String username);

    void submitNew(String title, String url, String author);

    boolean lastPage(int currentPage);

    public int databaseSize();
}
