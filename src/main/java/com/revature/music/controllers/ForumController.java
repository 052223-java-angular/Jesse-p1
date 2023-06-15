package com.revature.music.controllers;

import com.revature.music.dtos.requests.NewForumComment;
import com.revature.music.dtos.requests.NewThreadRequest;
import com.revature.music.entities.ForumThread;
import com.revature.music.services.ForumService;
import com.revature.music.services.JwtTokenService;
import com.revature.music.services.StringValidationService;
import com.revature.music.utils.InvalidTokenException;
import com.revature.music.utils.ResourceConflictException;
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
    private final StringValidationService stringValidationService;
    private final JwtTokenService tokenService;

    /**
     * A user can create a thread
     * @param req -
     * @return - status 201
     */
    @PostMapping("/create")
    public ResponseEntity<?>createThread(@RequestBody NewThreadRequest req)
    {
      //Cant be blank Title
      if(stringValidationService.isBlank(req.getTitle()))
      {
        throw new ResourceConflictException("Title can't be blank!");
      }
        //Create Title must be at least 8 ( max 30 ?)characters validation
        if (!stringValidationService.checkLength(req.getTitle(), 5,30))
      {
        throw new ResourceConflictException("Title must have between 5 and 30 characters!");
      }

        // Create Description must be at least 15 characters
      if (!stringValidationService.checkLengthMin(req.getDescription(), 15))
      {
        throw new ResourceConflictException("Description must be 15 characters!");
      }

        //Extract userid via token
        String userId = tokenService.extractUserId(req.getToken());

        // validate token if token is valid
        if (userId == null || userId.isEmpty()) {
            throw new InvalidTokenException("Token is not valid!");
        }

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
     * Expired token was only thrown on this method and not any other ???
     *
     * How would the user pick which thread they are commenting on the thread id needs to be passed in
     * @param req
     * @return
     */
    @PostMapping("/comment")
    public ResponseEntity<?>postComment(@RequestBody NewForumComment req)
    {
        //Cant be empty comment validation
      if(stringValidationService.isBlank(req.getContent()))
      {
        throw new ResourceConflictException("Comment can't be blank!");
      }

        String userId = tokenService.extractUserId(req.getToken());// user id associated with comment on thread
        // validate token if token is valid
        if (userId == null || userId.isEmpty()) {
            throw new InvalidTokenException("Token is not valid!");
        }
        //Thread Id needs to passed in for the user to comment on a specific thread


        forumService.postCommentToThread(req, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
