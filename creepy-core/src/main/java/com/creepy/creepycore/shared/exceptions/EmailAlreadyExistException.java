package com.creepy.creepycore.shared.exceptions;

public class EmailAlreadyExistException extends IllegalArgumentException{
    private static final long serialVersionUID = 1L;

    public EmailAlreadyExistException(String message) {
        super(message);
    }
}
