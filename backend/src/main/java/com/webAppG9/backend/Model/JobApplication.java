package com.webAppG9.backend.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_applications")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Relación con User
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Relación con JobPostt
    @ManyToOne
    @JoinColumn(name = "job_post_id", nullable = false)
    private JobPost jobPost;

    @Column(name = "applied_at", nullable = false, updatable = false)
    private LocalDateTime appliedAt;

    @ManyToOne
    @JoinColumn(name = "candidated_id", nullable = false)
    private Candidated candidated;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    // --- Enum para status ---
    public enum Status {
        PENDING, REVIEWED, ACCEPTED, REJECTED
    }

    // Constructor vacío para JPA
    public JobApplication() {
    }

    // Constructor útil
    public JobApplication(User user, JobPost jobPost, Status status, Candidated candidated, LocalDateTime appliedAt) {
        this.user = user;
        this.jobPost = jobPost;
        this.status = status;
        this.candidated = candidated;
        this.appliedAt = LocalDateTime.now();
    }

    // --- Getters y Setters ---
    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JobPost getJobPost() {
        return jobPost;
    }

    public void setJobPost(JobPost jobPost) {
        this.jobPost = jobPost;
    }

    public Candidated getCandidated() {
        return candidated;
    }

    // Setter
    public void setCandidated(Candidated candidated) {
        this.candidated = candidated;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
