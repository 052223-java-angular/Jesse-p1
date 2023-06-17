package com.revature.music.utils;

public class PlaylistNotFoundException extends RuntimeException{

    public PlaylistNotFoundException(String message)
    {
        super(message);
    }
}
