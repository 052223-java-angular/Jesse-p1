package com.revature.music.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    private String id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    //@Column(nullable = false)
    //private String firstName;
    //private String lastName;
    //private String email;


    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonBackReference
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<ForumThread> forumThreads;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<ForumPost> forumPosts;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Playlist> playlists;

    /**
     * Constructor to create a user
     * @param username - username
     * @param password- password
     * @param role - role
     * @param firstName - firstname
     * @param lastName- lastname
     * @param email - email
     */
    public User(String username, String password, Role role)//, String firstName, String lastName, String email)
    {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.role = role;
      //  this.firstName = firstName;
        //this.lastName = lastName;
        //this.email = email;
        this.forumThreads = new HashSet<>();
        this.forumPosts = new HashSet<>();
        this.playlists = new HashSet<>();
    }
}
