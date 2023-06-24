package com.revature.music.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeleteForumComment {

  private String forumthreadId;
  private String forumCommentId;
  private String token;
}
