package com.RedditApp.services;

import com.RedditApp.models.RedditPost;
import com.RedditApp.models.User;
import com.RedditApp.models.Vote;
import com.RedditApp.repositories.RedditPostRepository;
import com.RedditApp.repositories.UserRepository;
import com.RedditApp.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class RedditPostServiceImpl implements RedditPostService {
    private RedditPostRepository redditPostRepository;
    private UserRepository userRepository;
    private VoteRepository voteRepository;
    private int postsPerPage = 4;

    @Autowired
    public RedditPostServiceImpl(RedditPostRepository redditPostRepository, UserRepository userRepository, VoteRepository voteRepository) {
        this.redditPostRepository = redditPostRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
    }

    @Override
    public Page<RedditPost> showAll(int pageNum) {
        return redditPostRepository.findAll(PageRequest.of(pageNum, postsPerPage, Sort.by("numOfVotes").descending()));
    }

    @Override
    public void vote(long post_id, String username, boolean up) {
        RedditPost post = redditPostRepository.getReferenceById(post_id);
        String postAuthor = post.getAuthor_username();
        User user = userRepository.getReferenceById(username);

        if (!Objects.equals(postAuthor, user.getUsername())) {      // if the post is not their own

            Optional<Vote> existingVote = voteRepository.findByPostAndUser(post, user);

            if (existingVote.isEmpty()) {                           // and if they haven't voted for this post yet
                Vote vote = new Vote(up, user, post);
                voteRepository.save(vote);                          // then the vote will happen
                if (up) {
                    post.setNumOfVotes(post.getNumOfVotes() + 1);
                } else {
                    if (post.getNumOfVotes() > 0) {
                        post.setNumOfVotes(post.getNumOfVotes() - 1);
                    }
                }
                redditPostRepository.save(post);
            }
        }
    }

    @Override
    public void upvote(long id, String username) {
        RedditPost post = redditPostRepository.getReferenceById(id);
        post.setNumOfVotes(post.getNumOfVotes() + 1);
        redditPostRepository.save(post);
    }

    @Override
    public void downvote(long id, String username) {
        RedditPost post = redditPostRepository.getReferenceById(id);
        if (post.getNumOfVotes() > 0) {
            post.setNumOfVotes(post.getNumOfVotes() - 1);
            redditPostRepository.save(post);
        }
    }

    @Override
    public void submitNew(String title, String url, String author) {
        RedditPost newPost = new RedditPost(title, url, author);
        redditPostRepository.save(newPost);
    }

    @Override
    public boolean lastPage(int currentPage) {
        return redditPostRepository.findAll().size() / postsPerPage <= currentPage;
    }

    @Override
    public int databaseSize() {
        return redditPostRepository.findAll().size();
    }
}

