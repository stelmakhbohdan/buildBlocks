package com.simplerest.buildblocks.exceptions;

public class UserNotFoundException extends Exception{

    public static final long serialVersionUID =1L;

    public UserNotFoundException(String message) {
        super(message);
    }
}
