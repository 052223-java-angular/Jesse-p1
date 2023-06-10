package com.revature.music.dtos.requests;

import com.revature.music.entities.Artist;
import com.revature.music.entities.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewSongRequest {
    private String title;
    private float duration;
    private Genre genre;
    private Artist artist;

}
