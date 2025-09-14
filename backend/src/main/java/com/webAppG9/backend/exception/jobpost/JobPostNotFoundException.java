package com.webAppG9.backend.exception;

public class JobPostNotFoundException extends RuntimeException {
    public JobPostNotFoundException() {
        super("Oferta laboral no encontrada");
    }
}
