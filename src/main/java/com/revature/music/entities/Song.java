package com.revature.music.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "songs")
public class Song {

    @Id
    private String id;
    @Column(nullable = false)
    private String title;
    private float duration;

    @Column(name="name", nullable = false)
    private String name;



    @ManyToOne()
    @JoinColumn(name = "artist_id")
    @JsonBackReference
    private Artist artist;

    public Song(String id, String title, float duration, Artist artist, String name)
    {
       this.id = id;
       this.title = title;
       this.duration = duration;
       this.artist = artist;
       this.name = name;


    }
}
