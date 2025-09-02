package com.webAppG9.backend.dto;

import java.time.LocalDateTime;

//clase para exponer datos de forma segura al front (Data Transfer Object)
public class UserDTO {

    // atributos de la clase
    private String role;
    private String username;
    private String email;
    private LocalDateTime createdAt;

    // constructor con los datos requeridos por el front
    public UserDTO(String role, String username, String email, LocalDateTime createdAt) {

        this.role = role;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
    }

    // Metodos Getters y setters

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
