package com.webAppG9.backend.dto.jobapplication;

import java.time.LocalDateTime;

import com.webAppG9.backend.Model.JobApplication;

public class JobApplicationSimpleDTO {

    private Integer jobPostId;
    private Integer id;
    private String status;
    private LocalDateTime appliedAt;

    // Constructor desde JobApplication
    public JobApplicationSimpleDTO(JobApplication jobApplication) {
        this.id = jobApplication.getId();
        this.status = jobApplication.getStatus().name();
        this.appliedAt = jobApplication.getAppliedAt();
    }

    public JobApplicationSimpleDTO() {
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getJobPostId() {
        return jobPostId;
    }

    public void setJonPostId(Integer jobPostId) {
        this.jobPostId = jobPostId;
    }
}
