package com.webAppG9.backend.exception;

public class CandidateAlreadyAppliedException extends RuntimeException {

    public CandidateAlreadyAppliedException() {
        super("El candidato ya aplicó a esta oferta");
    }

    public CandidateAlreadyAppliedException(String message) {
        super(message);
    }
}