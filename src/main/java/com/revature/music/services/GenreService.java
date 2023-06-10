package com.revature.music.services;

import com.revature.music.entities.Genre;
import com.revature.music.repositories.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenreService {
    private GenreRepository genreRepository;

    /**
     * Might have to revise this until front end is built
     *
     * @param title
     * @param duration
     * @param artist
     * @param genre
     */
    public void createGenre(String name)
    {
        Genre genre = new Genre(name);
        genreRepository.save(genre);
    }
}
