package com.revature.music.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "playlists")
public class Playlist {
    @Id
    private String id;
    @Column(nullable = false)
    private String title;
    private String description;

    @ManyToMany
    @JoinTable(name = "song_id")
    private Set<Song> songs;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;


    public Playlist(String title, String description, User user)
    {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.user = user;
        this.songs = new HashSet<>();
    }

}
