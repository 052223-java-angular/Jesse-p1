package com.revature.music.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "artists")
public class Artist {

    @Id
    private String id;
    @Column(nullable = false)
    private String name;




    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Song> songs;

    public Artist(String id,String name)
    {
        this.id = id;
        this.name = name;

        this.songs = new HashSet<>();
    }
}
