package com.webAppG9.backend.exception;

public class UserAlreadyAdminException extends RuntimeException {

    public UserAlreadyAdminException() {
        super("Ya existe una solicitud de recruiter para este usuario");
    }

    public UserAlreadyAdminException(String message) {
        super(message);
    }
}
