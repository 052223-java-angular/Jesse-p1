package com.revature.music.services;

import com.revature.music.entities.Artist;
import com.revature.music.entities.Genre;
import com.revature.music.entities.Song;
import com.revature.music.repositories.ArtistRepository;
import com.revature.music.repositories.GenreRepository;
import com.revature.music.repositories.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SongService {
    private SongRepository songRepository;
    private GenreRepository genreRepository;
    private ArtistRepository artistRepository;

    /**
     * Might have to revise this until front end is built
     *
     * @param title
     * @param duration
     * @param artist
     * @param genre
     */
    public void createSong(String title, float duration, String artistName, String genreName)
    { //validate that these are in DB if not create
       Genre genre = genreRepository.findByName(genreName);
       Artist artist = artistRepository.findByName(artistName);

       Song song = new Song(title, duration, artist, genre);
       songRepository.save(song);
    }
}
