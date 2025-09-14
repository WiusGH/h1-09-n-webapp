package com.webAppG9.backend.dto.auth;

import com.webAppG9.backend.dto.user.UserResponseDTO;

public class LoginResponseDTO {

    private String token;
    private UserResponseDTO user;

    // Constructor
    public LoginResponseDTO(String token, UserResponseDTO user) {
        this.token = token;
        this.user = user;
    }

    // Getters y setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }
}
