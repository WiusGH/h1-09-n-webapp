package com.webAppG9.backend.dto.jobapplication;

import java.time.LocalDateTime;

import com.webAppG9.backend.Model.Candidated;
import com.webAppG9.backend.Model.JobApplication;
import com.webAppG9.backend.Model.JobPost;

public class JobApplicationResponseDTO {

    private Integer candidateId;
    private Integer jobPostId;
    private Integer applicationId;;

    private String status;
    private LocalDateTime appliedAt;

    public JobApplicationResponseDTO() {

    }

    public JobApplicationResponseDTO(JobApplication jobApplication) {

        Candidated candidated = jobApplication.getCandidated();
        JobPost jobPost = jobApplication.getJobPost();

        this.setStatus(jobApplication.getStatus().name());
        this.setAppliedAt(jobApplication.getAppliedAt());
        this.setApplicationId(jobApplication.getId());
        this.setJobPostId(jobPost.getId());
        this.setCandidateId(candidated.getId());
        this.setAppliedAt(LocalDateTime.now());
    }

    // Getters y setters
    public Integer getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(Integer jobPostId) {
        this.jobPostId = jobPostId;
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
