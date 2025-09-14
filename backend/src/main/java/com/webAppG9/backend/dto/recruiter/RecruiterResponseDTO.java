package com.webAppG9.backend.dto.recruiter;

import com.webAppG9.backend.Model.User;
import com.webAppG9.backend.Model.Recruiter;

public class RecruiterResponseDTO {

    private Integer userId;
    private String username;
    private String userEmail;
    private String companyName;
    private String website;
    private String description;
    private Boolean approved;

    // Mapear para response DTO
    public static RecruiterResponseDTO toResponseDTO(Recruiter recruiter) {
        User user = recruiter.getUser();
        RecruiterResponseDTO dto = new RecruiterResponseDTO();
        dto.setUserId(user.getId());
        dto.setUsername(user.getName());
        dto.setUserEmail(user.getEmail());
        dto.setCompanyName(recruiter.getCompanyName());
        dto.setWebsite(recruiter.getWebsite());
        dto.setDescription(recruiter.getDescription());
        dto.setApproved(recruiter.getApproved());
        return dto;
    }

    // Getters y Setters
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
