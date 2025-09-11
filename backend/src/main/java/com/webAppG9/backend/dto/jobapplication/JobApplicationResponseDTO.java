package com.webAppG9.backend.dto.jobapplication;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import com.webAppG9.backend.Model.Candidated;
import com.webAppG9.backend.Model.JobApplication;
import com.webAppG9.backend.Model.JobPost;
import com.webAppG9.backend.Model.Skill;
import com.webAppG9.backend.Model.User;

public class JobApplicationResponseDTO {

    private Integer candidateId;
    private String username;
    private String email;
    private String title;
    private String address;
    private String country;
    private String phoneNumber;
    private Boolean active;
    // private String role;
    private LocalDateTime createdAt;
    private String jobDescription;
    private String status;
    private LocalDateTime appliedAt;

    private Set<String> skills; // Nombres de las skills del candidato

    public JobApplicationResponseDTO() {

    }

    public JobApplicationResponseDTO(JobApplication jobApplication) {

        User user = jobApplication.getUser();
        JobPost jobPost = jobApplication.getJobPost();
        Candidated candidated = jobApplication.getCandidated();

        this.setUsername(user.getName() + " " + user.getLastName());
        this.setEmail(user.getEmail());
        this.setTitle(jobPost.getTitle());
        this.setJobDescription(jobPost.getDescription());
        this.setStatus(jobApplication.getStatus().name());
        this.setAppliedAt(jobApplication.getAppliedAt());
        this.setCandidateId(candidated.getId());
        this.setAddress(candidated.getAddress());
        this.setCountry(candidated.getCountry());
        this.setPhoneNumber(candidated.getPhoneNumber());
        this.setActive(candidated.getActive());
        this.setSkills(candidated.getSkills().stream().map(Skill::getName).collect(Collectors.toSet()));
        this.setCreatedAt(LocalDateTime.now());
    }

    public JobApplicationResponseDTO(Integer candidateId, String username, String email, String title,
            String address, String country, String phoneNumber, Boolean active,
            String createdAt, Set<String> skills) {
        this.candidateId = candidateId;
        this.username = username;
        this.email = email;
        this.title = title;
        this.address = address;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.active = active;
        this.createdAt = LocalDateTime.now();
        this.skills = skills;
    }

    // Getters y setters
    public Integer getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    // public String getRole() {
    // return role;
    // }

    // public void setRole(String role) {
    // this.role = role;
    // }

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
