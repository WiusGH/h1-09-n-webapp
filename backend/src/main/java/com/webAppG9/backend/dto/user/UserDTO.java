package com.webAppG9.backend.dto.user;

import com.webAppG9.backend.Model.User;
import java.time.LocalDateTime;

public class UserDTO {

    private String name;
    private String lastName;
    private String email;
    private User.Role role;
    private boolean active;
    private boolean profileCompleted;
    private LocalDateTime createdAt;

    // Constructor
    public UserDTO(User.Role role, String name, String lastName, String email, boolean active, Boolean profileCompleted,
            LocalDateTime createdAt) {
        this.role = role;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
        this.profileCompleted = profileCompleted;
        this.createdAt = createdAt;
    }

    public UserDTO(User user) {
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.active = user.getIsActive();
        this.createdAt = user.getCreatedAt();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

    public void applyToEntity(User user) {
        user.setName(this.name);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setRole(this.role);
        user.setIsActive(this.active);
    }

    public UserDTO() {
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

    public Boolean getProfileCompleted() {
        return profileCompleted;
    }

    public void setProfileCompleted(Boolean profileCompleted) {
        this.profileCompleted = profileCompleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
