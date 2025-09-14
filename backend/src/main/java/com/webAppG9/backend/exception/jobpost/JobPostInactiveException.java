package com.webAppG9.backend.exception.jobpost;

public class JobPostInactiveException extends RuntimeException {
    public JobPostInactiveException() {
        super("La oferta laboral no está activa");
    }
}
