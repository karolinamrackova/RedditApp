package com.redditapp.repositories;

import com.redditapp.models.RedditPost;
import org.hibernate.sql.Update;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface RedditPostRepository extends JpaRepository<RedditPost, Long> {
    List<RedditPost> findAll();
}
