package com.webAppG9.backend.dto;

import com.webAppG9.backend.Model.User;
import java.time.LocalDateTime;

public class UserDTO {

    private String name;
    private String lastName;
    private String email;
    private User.Role role;
    private boolean active;
    private LocalDateTime createdAt;

    // Constructor
    public UserDTO(User.Role role, String name, String lastName, String email, boolean active,
            LocalDateTime createdAt) {
        this.role = role;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
        this.createdAt = createdAt;
    }

    // Getters y Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User.Role getRole() {
        return role;
    }

    public void setRole(User.Role role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
