package com.revature.music.controllers;

import com.revature.music.dtos.requests.DeletePlaylistRequest;
import com.revature.music.dtos.requests.NewGetAllPlaylistsRequest;
import com.revature.music.dtos.requests.NewPlaylistRequest;
import com.revature.music.entities.Playlist;
import com.revature.music.services.JwtTokenService;
import com.revature.music.services.PlaylistService;
import com.revature.music.utils.InvalidTokenException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/playlist")
public class PlaylistController {
    private final JwtTokenService tokenService;
    private final PlaylistService playlistService;

    /**
     * Creates a new playlist where users can add songs
     * @param req- token, title, description
     * @return - new play list for the user
     */
    @PostMapping("/create")
    public ResponseEntity<?> createPlayList(@RequestBody NewPlaylistRequest req)
    {
        //validation for title cant be empty
        //Validation for title for certain number of characters

        String userId = tokenService.extractUserId(req.getToken());
        // validate token if token is valid
        if (userId == null || userId.isEmpty()) {
            throw new InvalidTokenException("Token is not valid!");
        }

        playlistService.createPlaylist(req,userId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * User able to successfully delete a playlist
     * @param req - token, playlist id
     * @return - successful deletion of a playlist
     */

    @DeleteMapping("/delete")
    public ResponseEntity<?> deletePlaylist(@RequestBody DeletePlaylistRequest req)
    {
        String userId = tokenService.extractUserId(req.getToken());
        // validate token if token is valid
        if (userId == null || userId.isEmpty()) {
            throw new InvalidTokenException("Token is not valid!");
        }

        //validate the playlist exists

        playlistService.deletePlaylist(req);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Gets all playlists in the database
     * @return All the playlists created by a user
     */
    @GetMapping("/all")
    public ResponseEntity<List<Playlist>>getAllPlaylists(@RequestBody NewGetAllPlaylistsRequest req)
    {
        String userId = tokenService.extractUserId(req.getToken());

        // validate token if token is valid
        if (userId == null || userId.isEmpty()) {
            throw new InvalidTokenException("Token is not valid!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(playlistService.getAllPlaylists(userId));
    }

}
