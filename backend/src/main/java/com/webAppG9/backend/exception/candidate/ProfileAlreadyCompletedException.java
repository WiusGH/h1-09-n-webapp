package com.webAppG9.backend.exception.candidate;

public class ProfileAlreadyCompletedException extends RuntimeException {

    public ProfileAlreadyCompletedException() {
        super("El perfil del usuario ya fue completado");
    }

    public ProfileAlreadyCompletedException(String message) {
        super(message);
    }
}
