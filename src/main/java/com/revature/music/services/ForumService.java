package com.revature.music.services;

import com.revature.music.dtos.requests.DeleteForumComment;
import com.revature.music.dtos.requests.DeleteForumThread;
import com.revature.music.dtos.requests.NewForumComment;
import com.revature.music.dtos.requests.NewThreadRequest;
import com.revature.music.dtos.responses.ForumThreadResponse;
import com.revature.music.entities.ForumComment;
import com.revature.music.entities.ForumThread;
import com.revature.music.entities.User;
import com.revature.music.repositories.ForumCommentRepository;
import com.revature.music.repositories.ForumThreadRepository;
import com.revature.music.utils.ForumThreadNotFoundException;
import com.revature.music.utils.PlaylistNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ForumService {
    private static final Logger logger = LogManager.getLogger(ForumService.class);
    private final ForumThreadRepository forumThreadRepository;
    private final ForumCommentRepository forumCommentRepository;
    private final UserService userService;

    /**
     * User can post start a new thread
     * @param req the request containing the thread title, description, token
     * @param id - the user id
     * @return - confirmation of saving the new thread
     */
    public ForumThread createThread(NewThreadRequest req, String id) {

        User existingUser = userService.findUserById(id);
        ForumThread newThread = new ForumThread(req.getTitle(), req.getDescription(), existingUser);
        logger.info( existingUser.getUsername() + ": creating a new thread: " + newThread.getId());
        return forumThreadRepository.save(newThread);
    }

    public List<ForumThread>getForumThreadsByUserId(String userId)
    {
      return forumThreadRepository.findAllByUserId(userId);
    }

  /**
   * Returns all the forum threads posted by users
   * @return
   */
  public List<ForumThreadResponse> getAllThreads()
    {
//      List<ForumThreadResponse> forumThreadResponses = new ArrayList<>();
//      List<ForumThread> forumThreads = forumThreadRepository.findAll();
//
//      for (ForumThread f : forumThreads) {
//        forumThreadResponses.add(new ForumThreadResponse(f));
//      }
//      return forumThreadResponses;
      return forumThreadRepository.findAll();
    }

  /**
   * Deletes a specific forum thread by id
   * @param req
   */
  public void deleteForumThread(DeleteForumThread req){
      forumThreadRepository.deleteById(req.getForumthreadId());
  }

  public ForumThread findById(String forumId) {
    return forumThreadRepository.findById(forumId)
      .orElseThrow(() -> new ForumThreadNotFoundException("Forum Thread Not found"));
  }

  public ForumThread updateFormThread(String threadId, NewThreadRequest req, String userId) {
    if (!forumThreadRepository.existsById(threadId)) {
      throw new PlaylistNotFoundException("No playlist found");
    }

    User user = userService.findUserById(userId);

    ForumThread forumThread = new ForumThread(req.getTitle(), req.getDescription(), user);
    forumThread.setId(threadId);

    return forumThreadRepository.save(forumThread);
  }

    //<===================================Forum Comment methods====================================================>\\

    /**
     * Users can post a comment to a specific thread. The way it is setup right now the
     * thread id is passed through the req.
     * @param req - the new comment to be posted, the thread id of the thread to post to
     * @param userId - the user making a comment to the post
     * @return - the comment being saved
     */
    public ForumComment postCommentToThread(NewForumComment req, String userId)
    {
        User foundUser = userService.findUserById(userId);
        ForumThread foundThread = forumThreadRepository.findById(req.getThreadId()).get();
        ForumComment newForumPost = new ForumComment(req.getContent(),foundUser, foundThread);
      logger.info( foundUser.getUsername() + ": Posting a comment: " + newForumPost.getContent() + "to thread :" + foundThread.getId());
       return forumCommentRepository.save(newForumPost);
    }


  /**
   * Deletes a forum comment from specified thread
   * @param req - forum comment id
   * @param userId - user id
   */
  public void deleteForumComment(DeleteForumComment req, String userId)
  {
    User foundUser = userService.findUserById(userId);
    ForumThread foundThread = forumThreadRepository.findById(req.getForumthreadId()).get();

    forumCommentRepository.deleteById(req.getForumCommentId());

  }

  /**
   *
   * @param commentId
   * @param req
   * @param userId
   * @return
   */
  public ForumComment updateForumComment(String commentId, NewForumComment req, String userId) {

    if (!forumCommentRepository.existsById(commentId)) {
      throw new PlaylistNotFoundException("No playlist found");
    }


    User user = userService.findUserById(userId);

    ForumComment forumComment = new ForumComment(req.getContent(), user,forumThreadRepository.findById(req.getThreadId()).get());
    forumComment.setId(commentId);

    return forumCommentRepository.save(forumComment);
  }
}
