package com.revature.music.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table (name = "forumPosts")
public class ForumPost {
    @Id
    private String id;

    @Column(name="content", nullable = false)
    private String content;

    @Column(name = "creationDate", nullable = false)
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "forumThread_id")
    @JsonBackReference
    private ForumThread forumThread;
}
