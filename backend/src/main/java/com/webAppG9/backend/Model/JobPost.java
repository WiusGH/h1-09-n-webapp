package com.webAppG9.backend.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_post")
public class JobPost {

    // atributos de la entidad
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private String recruiterName;
    private String companyName;
    private String companyCountry;
    private String companyEmail;
    private Boolean isActive;
    private Integer candidates;
    private Integer candidatesApplied;
    private Boolean acceptingAplication;
    private LocalDateTime expiresAt;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    // Constructor vacio para JPA
    public JobPost() {

    }

    // getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getRecruiterName() {
        return recruiterName;
    }

    public void setRecruiterName(String recruiterName) {
        this.recruiterName = recruiterName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCountry() {
        return companyCountry;
    }

    public void setCompanyCountry(String companyCountry) {
        this.companyCountry = companyCountry;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setComapanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getCandidates() {
        return candidates;
    }

    public void setCandidates(Integer candidates) {
        this.candidates = candidates;
    }

    public Integer getCandidatesApplied() {
        return candidatesApplied;
    }

    public void setCandidateApplied(Integer candidatesApplied) {
        this.candidatesApplied = candidatesApplied;
    }

    public Boolean getAcceptingAplication() {
        return acceptingAplication;
    }

    public void setAcceptingAplication(Boolean acceptingAplication) {
        this.acceptingAplication = acceptingAplication;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public LocalDateTime getUpdateAt() {
        return updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

}
