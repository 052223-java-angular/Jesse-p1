package com.revature.music.services;

import com.revature.music.entities.Artist;
import com.revature.music.entities.Genre;
import com.revature.music.repositories.ArtistRepository;
import com.revature.music.repositories.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArtistService {
    private ArtistRepository artistRepository;
    private GenreRepository genreRepository;

    /**
     * Might have to revise this until front end is built
     *
     * @param firstname
     * @param lastname
     * @param genreName
     */
    public void createSong(String firstname, String genreName)
    {
        //validate genre exists if not create
        Genre genre = genreRepository.findByName(genreName);

        Artist artist = new Artist(firstname, genre);
        artistRepository.save(artist);
    }
}
