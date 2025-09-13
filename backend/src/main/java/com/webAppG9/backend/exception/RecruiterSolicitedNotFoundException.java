package com.webAppG9.backend.exception;

public class RecruiterSolicitedNotFoundException extends RuntimeException {

    public RecruiterSolicitedNotFoundException() {
        super("Solicitud no encontrada");
    }

    public RecruiterSolicitedNotFoundException(String message) {
        super(message);
    }
}