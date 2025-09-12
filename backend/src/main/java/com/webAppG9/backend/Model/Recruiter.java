package com.webAppG9.backend.Model;

import com.webAppG9.backend.dto.recruiter.RecruiterResponseDTO;

import jakarta.persistence.*;

@Entity
@Table(name = "recruiters")
public class Recruiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String companyName;
    private String website;
    private String description;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    private User user;

    private Boolean approved = false;

    public Recruiter() {
    }

    // Mapear para response DTO
    public RecruiterResponseDTO toResponseDTO() {
        RecruiterResponseDTO dto = new RecruiterResponseDTO();
        dto.setUserId(this.user.getId());
        dto.setUsername(this.user.getName());
        dto.setUserEmail(this.user.getEmail());
        dto.setCompanyName(this.companyName);
        dto.setWebsite(this.website);
        dto.setDescription(this.description);
        dto.setApproved(this.approved);
        return dto;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
