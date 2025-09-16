package com.webAppG9.backend.exception.jobpost;

public class JobPostNotFoundException extends RuntimeException {
    public JobPostNotFoundException() {
        super("Oferta laboral no encontrada");
    }
}
