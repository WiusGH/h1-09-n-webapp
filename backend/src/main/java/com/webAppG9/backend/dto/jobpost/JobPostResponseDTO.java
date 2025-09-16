package com.webAppG9.backend.dto.jobpost;

import java.time.LocalDateTime;
import java.util.Set;

import com.webAppG9.backend.Model.JobPost;
import com.webAppG9.backend.Model.Skill;
import java.util.stream.Collectors;

public class JobPostResponseDTO {

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
    private LocalDateTime createdAt;
    private Set<String> skills; // n

    public JobPostResponseDTO() {
    }

    // Constructor desde entidad
    public JobPostResponseDTO(JobPost jobPost) {
        this.id = jobPost.getId();
        this.title = jobPost.getTitle();
        this.description = jobPost.getDescription();
        this.recruiterName = jobPost.getRecruiterName();
        this.companyName = jobPost.getCompanyName();
        this.companyCountry = jobPost.getCompanyCountry();
        this.companyEmail = jobPost.getCompanyEmail();
        this.isActive = jobPost.getIsActive();
        this.candidates = jobPost.getCandidates();
        this.candidatesApplied = jobPost.getCandidatesApplied();
        this.createdAt = jobPost.getCreatedAt();
        this.skills = jobPost.getSkills() != null
                ? jobPost.getSkills().stream().map(Skill::getName).collect(Collectors.toSet())
                : null;
    }

    // Getters y Setters
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }
}
