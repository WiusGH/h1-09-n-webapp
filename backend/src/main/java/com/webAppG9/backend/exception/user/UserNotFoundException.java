package com.webAppG9.backend.exception.user;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("Usuario no encontrado");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
