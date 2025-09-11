package com.webAppG9.backend.dto.auth;

public class RegisterResponseDTO {

    private Integer id;
    private String email;
    private String message;

    public RegisterResponseDTO() {
    }

    public RegisterResponseDTO(Integer id, String email, String message) {
        this.id = id;
        this.email = email;
        this.message = message;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
