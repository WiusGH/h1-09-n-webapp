package com.webAppG9.backend.dto.auth;

import com.webAppG9.backend.dto.user.UserDTO;

public class LoginResponseDTO {

    private String token;
    private UserDTO user;

    // Constructor
    public LoginResponseDTO(String token, UserDTO user) {
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
