package com.revature.music.controllers;

import com.revature.music.dtos.requests.DeleteForumComment;
import com.revature.music.dtos.requests.NewForumComment;
import com.revature.music.dtos.requests.NewThreadRequest;
import com.revature.music.entities.ForumThread;
import com.revature.music.services.ForumService;
import com.revature.music.services.JwtTokenService;
import com.revature.music.services.StringValidationService;
import com.revature.music.utils.InvalidTokenException;
import com.revature.music.utils.ResourceConflictException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/forum")
public class ForumController {
  private final ForumService forumService;
  private final StringValidationService stringValidationService;
  private final JwtTokenService tokenService;

  /**
   * A user can create a thread
   *
   * @param req -
   * @return - status 201 or error
   */
  @PostMapping("/create")
  public ResponseEntity<?> createThread(@RequestBody NewThreadRequest req) {
    //Cant be blank Title
    if (stringValidationService.isBlank(req.getTitle())) {
      throw new ResourceConflictException("Title can't be blank!");
    }
    //Create Title must be at least 8 ( max 30 ?)characters validation
    if (!stringValidationService.checkLength(req.getTitle(), 5, 30)) {
      throw new ResourceConflictException("Title must have between 5 and 30 characters!");
    }

    // Create Description must be at least 15 characters
    if (!stringValidationService.checkLengthMin(req.getDescription(), 15)) {
      throw new ResourceConflictException("Description must be 15 characters!");
    }

    //Extract userid via token
    String userId = tokenService.extractUserId(req.getToken());

    // validate token if token is valid
    if (userId == null || userId.isEmpty()) {
      throw new InvalidTokenException("Token is not valid!");
    }

    forumService.createThread(req, userId);// Creates a new thread associated with user

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  /**
   * Deletes a specific forum thread by id
   *
   * @param req - forum id, token
   * @return - error status or 201 status
   */
  @CrossOrigin
  @DeleteMapping("/delete/{forumThreadId}")
  public ResponseEntity<?> deleteForumThread(@PathVariable String forumThreadId , HttpServletRequest req) {
    String token = req.getHeader("auth-token");
    String userId = tokenService.extractUserId(token);
    // validate token if token is valid
    if (userId == null || userId.isEmpty()) {
      throw new InvalidTokenException("Token is not valid!");
    }

    //validate the forum exists

    forumService.deleteForumThread(forumThreadId);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  /**
   * IMPLEMENTED
   * This method returns all thread posts made by any user
   *
   * @return- All threads created by any user
   */
  @CrossOrigin
  @GetMapping("/all")
  public ResponseEntity<List<ForumThread>> getAllThreads() {
    return ResponseEntity.status(HttpStatus.OK).body(forumService.getAllThreads());
  }

  /**
   * User can get all their form threads
   * @param req
   * @return
   */
  @CrossOrigin
  @GetMapping("/all/user")
  public ResponseEntity<List<ForumThread>>getForumThreadsByUserId(HttpServletRequest req){

    String token = req.getHeader("auth-token");
    String userId = tokenService.extractUserId(token);
    // validate token if token is valid
    if (userId == null || userId.isEmpty()) {
      throw new InvalidTokenException("Token is not valid!");
    }
    return ResponseEntity.status(HttpStatus.OK).body(forumService.getForumThreadsByUserId(userId));

  }

  /**
   * IMPLEMENTED
   * Get a specified forum
   *
   * @param forumId - id
   * @return - 201 status or error status
   */
  @CrossOrigin
  @GetMapping("/get/{forumId}")
  public ResponseEntity<ForumThread> getForumById(@PathVariable String forumId) {
    return ResponseEntity.status(HttpStatus.OK).body(forumService.findById(forumId));
  }

  /**
   * IMPLEMENTED
   * User can update a form thread
   * @param threadId
   * @param req
   * @return
   */
  @CrossOrigin
  @PatchMapping("/update/{threadId}")
  public ResponseEntity<ForumThread> updateThread(@PathVariable String threadId, @RequestBody NewThreadRequest req) {
    String userId = tokenService.extractUserId(req.getToken());
    // validate token if token is valid
    if (userId == null || userId.isEmpty()) {
      throw new InvalidTokenException("Token is not valid!");
    }
    return ResponseEntity.status(HttpStatus.OK).body(forumService.updateFormThread(threadId, req, userId));
  }

//<======================================*ForumCommentMethods*====================================================>

  /**
   * Posts a comment to a user thread
   *
   * @param req
   * @return
   */
  @CrossOrigin
  @PostMapping("/comment")
  public ResponseEntity<?> postComment(@RequestBody NewForumComment req) {
    //Cant be empty comment validation
    if (stringValidationService.isBlank(req.getContent())) {
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


  /**
   * Deletes a forum comment from specified thread
   *
   * @param req - forumThreadId, ForumCommentId, token
   * @return - error or 201 status
   */
  @CrossOrigin
  @DeleteMapping("/delete/comment/{commentId}")
  public ResponseEntity<?> deleteComment( @PathVariable String commentId,HttpServletRequest req) {
    String token = req.getHeader("auth-token");
    String userId = tokenService.extractUserId(token);
    // validate token if token is valid
    if (userId == null || userId.isEmpty()) {
      throw new InvalidTokenException("Token is not valid!");
    }

    forumService.deleteForumComment(commentId, userId);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @CrossOrigin
  @PatchMapping("/update/comment/{commentId}")
  public ResponseEntity<ForumThread> updateComment(@PathVariable String commentId, @RequestBody NewForumComment req) {
    String userId = tokenService.extractUserId(req.getToken());
    // validate token if token is valid
    if (userId == null || userId.isEmpty()) {
      throw new InvalidTokenException("Token is not valid!");
    }
    return ResponseEntity.status(HttpStatus.OK).body(forumService.updateForumComment(commentId, req, userId).getForumThread());


  }
}
