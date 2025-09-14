package com.webAppG9.backend.exception;

public class MaxCandidatesReachedException extends RuntimeException {
    public MaxCandidatesReachedException() {
        super("No se pueden aceptar más aplicaciones para esta oferta");
    }
}
