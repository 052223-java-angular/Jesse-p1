package com.revature.music.utils;

public class SongNotFoundException extends RuntimeException{

    public SongNotFoundException(String message)
    {
        super(message);
    }
}
