package com.revature.music.services;

import com.revature.music.dtos.requests.DeletePlaylistRequest;
import com.revature.music.dtos.requests.NewPlaylistRequest;
import com.revature.music.entities.Playlist;
import com.revature.music.entities.User;
import com.revature.music.repositories.PlaylistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlaylistService {

    private PlaylistRepository playlistRepository;
    private final UserService userService;

    /**
     * A user can create a playlist
     * @param req title, description, token
     * @param userId - id of the user creating the playlist
     * @return - a new playlist
     */
    public Playlist createPlaylist(NewPlaylistRequest req, String userId)
    {
        User existingUser = userService.findUserById(userId).get();

        Playlist newPlaylist = new Playlist(req.getTitle(), req.getDescription(), existingUser);

        return playlistRepository.save(newPlaylist);
    }
//This did not work when passing the playlist id and user id
   /* public Playlist deletePlaylist(DeletePlaylistRequest req, String userId) {
        User existingUser = userService.findUserById(userId).get();
        return playlistRepository.deleteByIdAndUserId(req.getPlaylistId(), userId);
    }*/

    /**
     * Delete a specific play list
     * @param req - playlist id
     */
    public void deletePlaylist(DeletePlaylistRequest req) {
        //User existingUser = userService.findUserById(userId).get();
        playlistRepository.deleteById(req.getPlaylistId());
    }

    /**
     * Gets playlists associated with userid
     * @param userId - the userid
     * @return - All the playlist associated with the userid
     */
    public List<Playlist> getAllPlaylists(String userId)
    {
        //validate if user created a playlist

        return playlistRepository.findByUserId(userId);
    }
}
