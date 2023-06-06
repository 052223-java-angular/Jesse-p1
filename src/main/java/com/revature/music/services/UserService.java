package com.revature.music.services;

import com.revature.music.entities.User;
import com.revature.music.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This is a bean, this is a singleton by default - can only be instantiated with a private constructor, it can only be instantied by
 * by itself to share with other objects
 */
@Service
@AllArgsConstructor
public class UserService {
     private final UserRepository userRepo;

    public boolean isUniqueUsername(String username)
    {
        Optional<User> userOpt = userRepo.findByUserName(username);
        return userOpt.isEmpty();
    }
}
