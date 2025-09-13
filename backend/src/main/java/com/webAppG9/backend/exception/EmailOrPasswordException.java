package com.webAppG9.backend.exception;

public class EmailOrPasswordException extends RuntimeException {

    public EmailOrPasswordException() {
        super("Usuario o contraseñas incorectos");
    }

}
