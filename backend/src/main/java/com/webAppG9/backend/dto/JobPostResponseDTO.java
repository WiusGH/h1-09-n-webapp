package com.webAppG9.backend.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class JobPostResponseDTO {

    private String title;
    private String description;
    private String companyName;
    private Boolean isActive;
    private LocalDateTime expiresAt;
    private Set<String> skills; // Solo los nombres de las skills

    // Constructor vacío
    public JobPostResponseDTO() {
    }

    // Constructor con todos los campos
    public JobPostResponseDTO(String title, String description, String companyName, Boolean isActive,
            LocalDateTime expiresAt, Set<String> skills) {
        this.title = title;
        this.description = description;
        this.companyName = companyName;
        this.isActive = isActive;
        this.expiresAt = expiresAt;
        this.skills = skills;
    }

    // Getters y Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }
}
