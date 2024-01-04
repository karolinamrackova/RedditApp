package com.RedditApp.repositories;

import com.RedditApp.models.RedditPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RedditPostRepository extends JpaRepository<RedditPost, Long> {
    List<RedditPost> findAll();
}
