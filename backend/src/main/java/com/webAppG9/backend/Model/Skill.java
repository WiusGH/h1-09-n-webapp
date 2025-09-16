package com.webAppG9.backend.Model;

import java.util.Set;
import jakarta.persistence.*;

@Entity
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    // Relación con candidatos
    @ManyToMany(mappedBy = "skills")
    private Set<Candidated> candidates;

    // Relación con JobPosts
    @ManyToMany(mappedBy = "skills")
    private Set<JobPost> jobPosts;

    // Constructor vacío
    public Skill() {
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Candidated> getCandidates() {
        return candidates;
    }

    public void setCandidates(Set<Candidated> candidates) {
        this.candidates = candidates;
    }

    public Set<JobPost> getJobPosts() {
        return jobPosts;
    }

    public void setJobPosts(Set<JobPost> jobPosts) {
        this.jobPosts = jobPosts;
    }
}
