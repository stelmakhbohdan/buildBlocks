package com.simplerest.buildblocks.exceptions;

public class UserExistsException extends Exception{
    public static final long serialVersionUID =1L;

    public UserExistsException(String message) {
        super(message);
    }
}
