package com.revature.music.controllers;

import com.revature.music.dtos.requests.NewThreadRequest;
import com.revature.music.services.ForumService;
import com.revature.music.services.JwtTokenService;
import com.revature.music.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/forum")
public class ForumController {
    private final ForumService forumService;
    private final UserService userService;
    private final JwtTokenService tokenService;


    @PostMapping("/create")
    public ResponseEntity<?>createThread(@RequestBody NewThreadRequest req)
    {
        String userId = tokenService.extractUserId(req.getToken());

        forumService.createThread(req,userId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }





}
