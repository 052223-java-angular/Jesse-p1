package com.revature.music.services;

import com.revature.music.entities.Artist;
import com.revature.music.entities.Song;
import com.revature.music.repositories.ArtistRepository;
import com.revature.music.repositories.SongRepository;
import com.revature.music.utils.SongNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SongService {
    private SongRepository songRepository;

    private ArtistRepository artistRepository;

    /**
     * Might have to revise this until front end is built
     *
     * @param title
     * @param duration
     * @param artist
     * @param genre
     */
    public void createSong(String id, String title, float duration, String name, String genreName)
    { //validate that these are in DB if not create

       Artist artist = artistRepository.findByName(name);
       Optional<Song> existingSong = songRepository.findById(id);

       if(existingSong.isPresent())
       {
         existingSong.get().setTitle(title);
         existingSong.get().setDuration(duration);
         songRepository.save(existingSong.get());
       }
      {
        Song song = new Song(id, title, duration, artist, artist.getName());
        songRepository.save(song);
      }
    }

    public Song getSongById(String songId)
    {
      return songRepository.findById(songId)
        .orElseThrow(()-> new SongNotFoundException("Song does not exist!"));
    }
}
