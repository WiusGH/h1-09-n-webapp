package com.webAppG9.backend.dto.admin;

public class AdminResponseDTO {

    private Integer id;
    private Integer userId;
    private String username;
    private String email;
    private String role;

    // Constructor vacío
    public AdminResponseDTO() {
    }

    // Constructor con parámetros
    public AdminResponseDTO(Integer id, Integer userId, String username, String email, String role) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
