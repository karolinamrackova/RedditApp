package com.RedditApp.services;

import com.RedditApp.models.User;
import com.RedditApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean createUser(String username, String password) {
        if (userRepository.findById(username).isEmpty()) {
            User newUser = new User(username, password);
            userRepository.save(newUser);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean loginSuccessful(String username, String password) {
        if (userRepository.findById(username).isPresent()) {
            User userTryingToLogin = userRepository.findById(username).get();
            return userTryingToLogin.getPassword().equals(password);
        } else {
            return false;
        }
    }
}
