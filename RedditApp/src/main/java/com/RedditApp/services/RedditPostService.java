package com.RedditApp.services;

import com.RedditApp.models.RedditPost;
import org.springframework.data.domain.Page;


public interface RedditPostService {
    Page<RedditPost> showAll(int pageNum);

    void vote(long post_id, String username, boolean up);

    void upvote(long id, String username);

    void downvote(long id, String username);

    void submitNew(String title, String url, String author);

    boolean lastPage(int currentPage);

    public int databaseSize();
}
