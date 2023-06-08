package com.revature.music.services;

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

    public Playlist createPlaylist(NewPlaylistRequest req, String userId)
    {
        User existingUser = userService.findUserById(userId).get();

        Playlist newPlaylist = new Playlist(req.getTitle(), req.getDescription(), existingUser);

        return playlistRepository.save(newPlaylist);
    }

    /**
     *
     * @param userId
     * @return
     */
    public List<Playlist> getAllPlaylists(String userId)
    {

        return playlistRepository.findByUserId(userId);
    }
}
