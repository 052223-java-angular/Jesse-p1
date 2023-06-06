package com.revature.music.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

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

    @OneToMany(mappedBy = "playlist", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<PlaylistSong> playlistSongs;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

}
