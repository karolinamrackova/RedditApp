package com.redditapp.services;

public interface UserService {
    boolean createUser(String username, String password);
    boolean loginSuccessful(String username, String password);
}
