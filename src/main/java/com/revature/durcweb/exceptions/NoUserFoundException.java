package com.revature.durcweb.exceptions;

public class NoUserFoundException extends RuntimeException {

    public NoUserFoundException() {
        super("No user was found");
    }
}
