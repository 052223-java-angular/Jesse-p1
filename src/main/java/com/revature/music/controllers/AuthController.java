package com.revature.music.controllers;

import com.revature.music.dtos.requests.NewUserRequest;
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
    //private final JwtTokenService tokenService;

    @PostMapping("/register")//redirect, tells the front where to redirect //Can return a response entity with an object or statuscode or ? = void
    public ResponseEntity<?> registerUser(@RequestBody NewUserRequest req)//Json = Request body, its DTO, DTO comes in two forms response or Request
    {
        // check unique username
        if (!userService.isUniqueUsername(req.getUsername())){
            throw new ResourceConflictException(
                    "Username needs to be 8-20 characters long and can only contain letters, numbers, periods, and underscores");
        }
        //username is valid
        //check password
        //check passwords are the same
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
