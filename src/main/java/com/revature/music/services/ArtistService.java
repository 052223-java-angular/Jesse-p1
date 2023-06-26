package com.revature.music.services;

import com.revature.music.entities.Artist;
import com.revature.music.repositories.ArtistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ArtistService {
    private ArtistRepository artistRepository;


    /**
     * Might have to revise this until front end is built
     *
     * @param firstname
     * @param genreName
     */
    public void createSong(String id, String firstname, String genreName)
    {

      Optional<Artist> existingArtist = artistRepository.findById(id);

      if (existingArtist.isPresent()) {
        existingArtist.get().setName(firstname);

        artistRepository.save(existingArtist.get());
      }
      else{

        Artist newArtist = new Artist(id, firstname);
        artistRepository.save(newArtist);
      }
    }
}
