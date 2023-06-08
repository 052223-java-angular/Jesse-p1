package com.revature.music.controllers;

import com.revature.music.dtos.requests.NewForumComment;
import com.revature.music.dtos.requests.NewThreadRequest;
import com.revature.music.entities.ForumThread;
import com.revature.music.services.ForumService;
import com.revature.music.services.JwtTokenService;
import com.revature.music.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/forum")
public class ForumController {
    private final ForumService forumService;
    private final UserService userService;
    private final JwtTokenService tokenService;

    /**
     * A user can create a thread
     * @param req -
     * @return - status 201
     */
    @PostMapping("/create")
    public ResponseEntity<?>createThread(@RequestBody NewThreadRequest req)
    {
        //Create Title must be at least 8 ( max 30 ?)characters validation

        //Cant be blank Title

        // Create Description must be at least 15 characters

        //Is this right im not sure if this is the right thing to do
        String userId = tokenService.extractUserId(req.getToken());

        forumService.createThread(req,userId);// Creates a new thread associated with user

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * This method returns all thread posts made by any user
     * @return- All threads created by any user
     */
    @GetMapping("/all")
    public ResponseEntity<List<ForumThread>> getAllThreads()
    {
        return ResponseEntity.status(HttpStatus.OK).body(forumService.getAllThreads());
    }



    /**
     * How would the user pick which thread they are commenting on the thread id needs to be passed in
     * @param req
     * @return
     */
    @PostMapping("/comment")
    public ResponseEntity<?>postComment(@RequestBody NewForumComment req)
    {
        //Cant be empty comment validation

        String userId = tokenService.extractUserId(req.getToken());// user id associated with comment on thread

        //Thread Id needs to passed in for the user to comment on a specific thread

       // String forumId = forumService.getThreadId(req.getThreadId());//need to get the thread id that the user wants to comment on
        forumService.postCommenToThread(req, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }





}
