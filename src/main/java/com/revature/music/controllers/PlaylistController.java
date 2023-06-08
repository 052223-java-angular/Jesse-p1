package com.revature.music.controllers;

import com.revature.music.dtos.requests.NewGetAllPlaylistsRequest;
import com.revature.music.dtos.requests.NewPlaylistRequest;
import com.revature.music.entities.Playlist;
import com.revature.music.services.JwtTokenService;
import com.revature.music.services.PlaylistService;
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
     * Creats a new playlist
     * @param req-
     * @return - new play list for the user
     */
    @PostMapping("/create")
    public ResponseEntity<?> createPlayList(@RequestBody NewPlaylistRequest req)
    {
        //validation for title cant be empty
        //Validation for title for certain number of characters

        String userId = tokenService.extractUserId(req.getToken());

        //validation to make sure user exists

        playlistService.createPlaylist(req,userId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Gets all playlists in the database
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<List<Playlist>>getAllPlaylists(@RequestBody NewGetAllPlaylistsRequest req)
    {
        String userId = tokenService.extractUserId(req.getToken());
        //need to figure out how to make it only for the user id
        return ResponseEntity.status(HttpStatus.OK).body(playlistService.getAllPlaylists(userId));
    }



}
