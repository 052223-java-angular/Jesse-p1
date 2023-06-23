package com.revature.music.services;

import com.revature.music.dtos.requests.NewLoginRequest;
import com.revature.music.dtos.requests.NewUserRequest;
import com.revature.music.dtos.responses.Principal;
import com.revature.music.entities.Role;
import com.revature.music.entities.User;
import com.revature.music.repositories.UserRepository;
import com.revature.music.utils.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This is a bean, this is a singleton by default - can only be instantiated with a private constructor, it can only be instantied by
 * by itself to share with other objects
 */
@Service
@AllArgsConstructor
public class UserService {
     private final UserRepository userRepo;
     private final RoleService roleService;


    /**
     * Registers a new user based on the provided information.
     *
     * @param req the NewUserRequest object containing user registration details
     * @return the newly registered User object
     */
    public User registerUser(NewUserRequest req) {
        // find role USER
        Role foundRole = roleService.findByName("USER");
        // hash password
        String hashed = BCrypt.hashpw(req.getPassword(), BCrypt.gensalt());
        // create new user
        User newUser = new User(req.getUsername(), hashed, req.getFirstname(), req.getLastname(), req.getEmail(), foundRole);
        // save and return user
        return userRepo.save(newUser);
    }

    public Principal login(NewLoginRequest req) {
        Optional<User> userOpt = userRepo.findByUsername(req.getUsername());

        if (userOpt.isPresent()) {
            User foundUser = userOpt.get();
            if (BCrypt.checkpw(req.getPassword(), foundUser.getPassword())) {
                return new Principal(foundUser);
            }
        }

        throw new UserNotFoundException("Invalid credential");
    }

    public boolean isUniqueUsername(String username)
    {
        Optional<User> userOpt = userRepo.findByUsername(username);
        return userOpt.isEmpty();
    }

    /**
     * Checks if the provided username is valid
     * @param username - username the username to validate
     * @return - true if the username is valid, if not false
     */
    public boolean isValidUsername(String username) {
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    /**
     * Checks if the provided password and confirm password match.
     *
     * @param password        the password to compare
     * @param confirmPassword the confirm password to compare
     * @return true if the passwords match, false otherwise
     */
    public boolean isSamePassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }


    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    public List<User> findAll() {
      return userRepo.findAll();
    }

   public User findUserById(String id)
    {
      Optional<User> userOpt = userRepo.findById(id);

      if (userOpt.isEmpty()) throw new UserNotFoundException("User not found!");

      return userOpt.get();
    }
}
