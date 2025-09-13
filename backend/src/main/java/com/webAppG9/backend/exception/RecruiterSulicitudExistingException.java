package com.webAppG9.backend.exception;

public class RecruiterSulicitudExistingException extends RuntimeException {

    public RecruiterSulicitudExistingException() {
        super("Ya existe una solicitud de recruiter para este usuario");
    }

    public RecruiterSulicitudExistingException(String message) {
        super(message);
    }
}
