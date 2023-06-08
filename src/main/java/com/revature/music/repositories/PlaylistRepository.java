package com.revature.music.repositories;

import com.revature.music.entities.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist,String> {
    /**
     * Returns all the play lists associated with the userid
     * @param userId- userid
     * @return - playlists of the user
     */
    List<Playlist> findByUserId(String userId);
}
