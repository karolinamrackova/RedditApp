package com.redditapp.repositories;

import com.redditapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
