package com.webAppG9.backend.exception;

public class CandidateNotFoundException extends RuntimeException {
    public CandidateNotFoundException() {
        super("Candidato no encontrado");
    }
}
