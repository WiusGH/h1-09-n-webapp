package com.webAppG9.backend.dto.jobapplication;

import java.time.LocalDateTime;

public class JobApplicationRequestDTO {

    private Integer jobPostId;
    private Integer applicationId;
    private String username;
    private String userEmail;
    private String jobTitle;
    private String jobDescription;
    private String status;
    private LocalDateTime appliedAt;

    // Getters y setters

    public Integer getApplicationId() {
        return applicationId;
    }

    public Integer getJobPostId() {
        return jobPostId;
    }

    public void setId(Integer jobPostId) {
        this.jobPostId = jobPostId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
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

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }
}
