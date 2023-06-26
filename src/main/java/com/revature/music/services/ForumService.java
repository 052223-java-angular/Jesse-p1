package com.revature.music.services;

import com.revature.music.dtos.requests.NewForumComment;
import com.revature.music.dtos.requests.NewThreadRequest;
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
        ForumThread newThread = new ForumThread(req.getTitle(), req.getDescription(), existingUser, existingUser.getUsername());
        logger.info( existingUser.getUsername() + ": creating a new thread: " + newThread.getId());
        return forumThreadRepository.save(newThread);
    }

    public List<ForumThread>getForumThreadsByUserId(String userId)
    {
      return forumThreadRepository.findAllByUserId(userId);
    }

  /**
   * Returns all the forum threads posted by users
   *
   * @return
   */
  public List<ForumThread> getAllThreads()
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
  public void deleteForumThread(String forumThreadId){
    forumCommentRepository.deleteAllByForumThreadId(forumThreadId);
    forumThreadRepository.deleteById(forumThreadId);
  }

  public ForumThread findById(String forumThreadId) {
    return forumThreadRepository.findById(forumThreadId)
      .orElseThrow(() -> new ForumThreadNotFoundException("Forum Thread Not found"));
  }

  public ForumThread updateFormThread(String threadId, NewThreadRequest req, String userId) {
    if (!forumThreadRepository.existsById(threadId)) {
      throw new PlaylistNotFoundException("No playlist found");
    }

    User user = userService.findUserById(userId);

    ForumThread forumThread = forumThreadRepository.findById(threadId)
      .orElseThrow(() -> new ForumThreadNotFoundException("No thread found"));
//    ForumThread forumThread = new ForumThread(req.getTitle(), req.getDescription(), user, user.getUsername());
//    forumThread.setId(threadId);
    forumThread.setTitle(req.getTitle());
    forumThread.setDescription(req.getDescription());
    forumThread.setUser(user);
    forumThread.setUsername(user.getUsername());

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
        ForumComment newForumPost = new ForumComment(req.getContent(),foundUser, foundUser.getUsername(), foundThread);
      logger.info( foundUser.getUsername() + ": Posting a comment: " + newForumPost.getContent() + "to thread :" + foundThread.getId());
       return forumCommentRepository.save(newForumPost);
    }


  /**
   * Deletes a forum comment from specified thread
   * @param req - forum comment id
   * @param userId - user id
   */
  public void deleteForumComment(String commentId, String userId)
  {
    User foundUser = userService.findUserById(userId);
   // ForumThread foundThread = forumThreadRepository.findById(req.getForumthreadId()).get();

    forumCommentRepository.deleteById(commentId);

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

    ForumComment forumComment = new ForumComment(req.getContent(), user, user.getUsername(),forumThreadRepository.findById(req.getThreadId()).get());
    forumComment.setId(commentId);

    return forumCommentRepository.save(forumComment);
  }
}
