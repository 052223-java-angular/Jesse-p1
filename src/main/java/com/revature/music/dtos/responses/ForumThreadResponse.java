package com.revature.music.dtos.responses;

import com.revature.music.entities.ForumComment;
import com.revature.music.entities.ForumThread;
import com.revature.music.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ForumThreadResponse {

  private String id;
  private String title;
  private String description;
  private Date creationDate;
  private User user;
  private Set<ForumComment> forumComments;

  public ForumThreadResponse(ForumThread forumThread) {
    this.id = forumThread.getId();
    this.title = forumThread.getTitle();
    this.description = forumThread.getDescription();
    this.creationDate = new Date();
    this.user = forumThread.getUser();
    this.forumComments = forumThread.getForumComments();
  }
}
