package com.webAppG9.backend.dto.jobpost;

import java.util.Set;

public class JobPostRequestDTO {

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
    private Set<String> skills;

    public JobPostRequestDTO() {
    }

    public JobPostRequestDTO(String title, String description, String recruiterName, String companyName,
            String companyCountry, String companyEmail, Boolean isActive, Integer candidates,
            Integer candidatesApplied, Boolean acceptingAplication,
            Set<String> skills) {
        this.title = title;
        this.description = description;
        this.recruiterName = recruiterName;
        this.companyName = companyName;
        this.companyCountry = companyCountry;
        this.companyEmail = companyEmail;
        this.isActive = isActive;
        this.candidates = candidates;
        this.candidatesApplied = candidatesApplied;
        this.acceptingAplication = acceptingAplication;
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

    public Boolean getAcceptingAplication() {
        return acceptingAplication;
    }

    public void setAcceptingAplication(Boolean acceptingAplication) {
        this.acceptingAplication = acceptingAplication;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }
}
