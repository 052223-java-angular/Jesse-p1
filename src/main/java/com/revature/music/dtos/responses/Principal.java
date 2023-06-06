package com.revature.music.dtos.responses;

import com.revature.music.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Like a session. This is what is always being sent back so the front end can have this
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Principal {
    private String id;//in order for pulls playlists
    private String username;// to use it on screens
    private String role;//bypass route guarding
    private String token;// used to authentic the user so whenever it makes requests to the backend

    public Principal(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole().getName();
    }
}
