package com.webAppG9.backend.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

import com.webAppG9.backend.dto.jobpost.JobPostRequestDTO;

@Entity
@Table(name = "job_post")
public class JobPost {

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
    private Integer candidates = 0;
    private Integer candidatesApplied = 0;
    private LocalDateTime expiresAt;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(name = "jobpost_skills", joinColumns = @JoinColumn(name = "jobpost_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills;

    public boolean canAcceptApplication() {
        return Boolean.TRUE.equals(isActive)
                && (candidatesApplied < candidates);
    }

    // Cobstructor para mapear un JovPost y validar parametros
    public void applyFromDTO(JobPostRequestDTO dto, Set<Skill> skills) {
        if (dto.getTitle() != null)
            this.title = dto.getTitle();
        if (dto.getDescription() != null)
            this.description = dto.getDescription();
        if (dto.getRecruiterName() != null)
            this.recruiterName = dto.getRecruiterName();
        if (dto.getCompanyName() != null)
            this.companyName = dto.getCompanyName();
        if (dto.getCompanyCountry() != null)
            this.companyCountry = dto.getCompanyCountry();
        if (dto.getCompanyEmail() != null)
            this.companyEmail = dto.getCompanyEmail();
        if (dto.getIsActive() != null)
            this.isActive = dto.getIsActive();
        if (dto.getCandidates() != null)
            this.candidates = dto.getCandidates();
        if (dto.getCandidatesApplied() != null)
            this.candidatesApplied = dto.getCandidatesApplied();
        if (dto.getExpiresAt() != null)
            this.expiresAt = dto.getExpiresAt();
        if (skills != null && !skills.isEmpty())
            this.skills = skills;
    }

    // Constructor vacío para JPA
    public JobPost() {
    }

    // Getters y Setters corregidos
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

    public void setCompanyEmail(String companyEmail) {
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

    public void setCandidatesApplied(Integer candidatesApplied) {
        this.candidatesApplied = candidatesApplied;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public LocalDateTime getUpdatedAt() {
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

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }
}
