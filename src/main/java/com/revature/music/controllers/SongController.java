package com.revature.music.controllers;

import com.revature.music.dtos.requests.NewSongRequest;
import com.revature.music.services.SongService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/song")
@AllArgsConstructor
public class SongController {

    private SongService songService;
    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<?>createSong(@RequestBody NewSongRequest req)
    {
        songService.createSong(req.getId(),req.getTitle(),req.getDuration(),req.getName(),req.getGenreName());
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }


}
