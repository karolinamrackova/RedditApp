package com.redditapp.repositories;

import com.redditapp.models.RedditPost;
import com.redditapp.models.User;
import com.redditapp.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

    Optional<Vote> findByPostAndUser(RedditPost post, User user);
}
