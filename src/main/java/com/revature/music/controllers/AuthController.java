package com.revature.music.controllers;

import com.revature.music.dtos.requests.NewLoginRequest;
import com.revature.music.dtos.requests.NewUserRequest;
import com.revature.music.dtos.responses.Principal;
import com.revature.music.services.JwtTokenService;
import com.revature.music.services.UserService;
import com.revature.music.utils.ResourceConflictException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController class provide authentication-related operations
 */
@AllArgsConstructor
@RestController//Declare this as a controller
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final JwtTokenService tokenService;

    @PostMapping("/register")//redirect, tells the front where to redirect //Can return a response entity with an object or statuscode or ? = void
    public ResponseEntity<?> registerUser(@RequestBody NewUserRequest req)//Json = Request body, its DTO, DTO comes in two forms response or Request
    {
        // check unique username
        if (!userService.isValidUsername(req.getUsername())) {
            throw new ResourceConflictException(
                    "Username needs to be 8-20 characters long and can only contain letters, numbers, periods, and underscores");
        }

        // if username is not unique, throw exception
        if (!userService.isUniqueUsername(req.getUsername())) {
            throw new ResourceConflictException("Username is not unique");
        }

        // if password is not valid, throw exception
        if (!userService.isValidPassword(req.getPassword())) {
            throw new ResourceConflictException(
                    "Password needs to be at least 8 characters long and contain at least one letter and one number");
        }

        // if password and confirm password do not match, throw exception
        if (!userService.isSamePassword(req.getPassword(), req.getConfirmPassword())) {
            throw new ResourceConflictException("Passwords do not match");
        }

        // register user
        userService.registerUser(req);

        // return 201 - CREATED
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/login")
    public ResponseEntity<Principal> login(@RequestBody NewLoginRequest req) {
        // userservice to call login method
        Principal principal = userService.login(req);

        // create a jwt token
        String token = tokenService.generateToken(principal);

        principal.setToken(token);

        // return status ok and return principal object
        return ResponseEntity.status(HttpStatus.OK).body(principal);
    }

}
