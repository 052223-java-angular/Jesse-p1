package com.revature.music.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "Songs")
public class Song {

    @Id
    private String id;
    @Column(nullable = false)
    private String title;
    private float duration;

    @OneToMany(mappedBy = "song", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<PlaylistSong> playlistsSongs;

    @ManyToOne()
    @JoinColumn(name = "genre_id")
    @JsonBackReference
    private Genre genre;

    @ManyToOne()
    @JoinColumn(name = "artist_id")
    @JsonBackReference
    private Artist artist;

    public Song(String title, float duration, Artist artist, Genre genre)
    {
       this.id = UUID.randomUUID().toString();
       this.title = title;
       this.duration = duration;
       this.artist = artist;
       this.genre = genre;
       this.playlistsSongs = new HashSet<>();
    }
}
