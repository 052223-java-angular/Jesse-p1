package com.revature.music.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table (name = "forumComments")
public class ForumComment {
    @Id
    private String id;

    @Column(name="content", nullable = false)
    private String content;

    @Column(name = "creationDate", nullable = false)
    private Date creationDate;

   @Column(name = "username", nullable = false)
   private String username;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "forumThread_id")
    @JsonBackReference
    private ForumThread forumThread;

    public ForumComment(String content, User user, String username, ForumThread forumThread)
    {
        this.id = UUID.randomUUID().toString();
        this.content = content;
        this.creationDate = new Date();
        this.user = user;
        this.username = username;
        this.forumThread = forumThread;
    }
}
