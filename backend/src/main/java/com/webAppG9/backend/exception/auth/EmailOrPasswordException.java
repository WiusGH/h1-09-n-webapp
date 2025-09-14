package com.webAppG9.backend.exception.auth;

public class EmailOrPasswordException extends RuntimeException {

    public EmailOrPasswordException() {
        super("Usuario o contraseñas incorectos");
    }

}
