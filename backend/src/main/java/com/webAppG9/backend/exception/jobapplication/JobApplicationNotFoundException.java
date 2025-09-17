package com.webAppG9.backend.exception.jobapplication;

public class JobApplicationNotFoundException extends RuntimeException {
    public JobApplicationNotFoundException() {
        super("Aplicación no encontrada");
    }
}
