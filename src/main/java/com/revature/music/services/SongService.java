package com.revature.music.services;

import com.revature.music.entities.Artist;
import com.revature.music.entities.Genre;
import com.revature.music.entities.Song;
import com.revature.music.repositories.ArtistRepository;
import com.revature.music.repositories.GenreRepository;
import com.revature.music.repositories.SongRepository;
import com.revature.music.utils.SongNotFoundException;
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
    public void createSong(String title, float duration, String name, String genreName)
    { //validate that these are in DB if not create
       Genre genre = genreRepository.findByName(genreName);
       Artist artist = artistRepository.findByName(name);

       Song song = new Song(title, duration, artist, genre);
       songRepository.save(song);
    }

    public Song getSongById(String songId)
    {
      return songRepository.findById(songId)
        .orElseThrow(()-> new SongNotFoundException("Song does not exist!"));
    }
}
