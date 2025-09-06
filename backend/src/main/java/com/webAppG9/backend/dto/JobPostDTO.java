package com.webAppG9.backend.dto;

import java.time.LocalDateTime;

public class JobPostDTO {

    // atributos de la clase
    private String title;
    private String description;
    private String companyName;
    private boolean isActive;
    private LocalDateTime expiresAt;

    // Constructor para instancia
    public JobPostDTO(String title, String description, String companyName, Boolean isActive, LocalDateTime expiresAt) {
        this.title = title;
        this.description = description;
        this.companyName = companyName;
        this.isActive = isActive;
        this.expiresAt = expiresAt;
    }

    public JobPostDTO() {
    }

    // title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // companyName
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    // isActive
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    // expiresAt
    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

}
