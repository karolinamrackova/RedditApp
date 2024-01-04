package com.RedditApp.repositories;

import com.RedditApp.models.RedditPost;
import com.RedditApp.models.User;
import com.RedditApp.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

    Optional<Vote> findByPostAndUser(RedditPost post, User user);
}
