package com.revature.music.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * New request for creating a playlist
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewPlaylistRequest {
    String title;
    String description;
    String token;
}
