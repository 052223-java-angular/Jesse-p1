package com.revature.music.utils;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException (String message)
    {
        super(message);
    }
}
