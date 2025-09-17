package com.webAppG9.backend.exception.recruiter;

public class RecruiterNotFoundException extends RuntimeException {

    public RecruiterNotFoundException() {
        super("Recruiter no encontrado");
    }

    public RecruiterNotFoundException(String message) {
        super(message);
    }
}