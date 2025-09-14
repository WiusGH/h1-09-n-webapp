package com.webAppG9.backend.Model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.webAppG9.backend.dto.candidates.CandidatedRequestDTO;

import jakarta.persistence.*;

@Entity
@Table(name = "candidated")
public class Candidated {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String address;

    private String country;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    private String education;

    private String experience;

    private String linkedinUrl;

    private String resumeUrl;

    private String summary;

    private Boolean active = true;

    @ManyToMany
    @JoinTable(name = "candidate_skills", joinColumns = @JoinColumn(name = "candidate_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    private User user;

    // Constructor vacío para JPA
    public Candidated() {
    }

    // Constructor con campos principales
    public Candidated(String title, String address, String country, String phoneNumber,
            LocalDate dateOfBirth, String education, String experience,
            String linkedinUrl, String resumeUrl, String summary, User user) {
        this.title = title;
        this.address = address;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.education = education;
        this.experience = experience;
        this.linkedinUrl = linkedinUrl;
        this.resumeUrl = resumeUrl;
        this.summary = summary;
        this.user = user;
        this.active = true;
    }

    public void applyFromDTO(CandidatedRequestDTO dto) {
        if (dto.getTitle() != null)
            this.title = dto.getTitle();
        if (dto.getAddress() != null)
            this.address = dto.getAddress();
        if (dto.getCountry() != null)
            this.country = dto.getCountry();
        if (dto.getPhoneNumber() != null)
            this.phoneNumber = dto.getPhoneNumber();
        if (dto.getDateOfBirth() != null)
            this.dateOfBirth = dto.getDateOfBirth();
        if (dto.getEducation() != null)
            this.education = dto.getEducation();
        if (dto.getExperience() != null)
            this.experience = dto.getExperience();
        if (dto.getLinkedinUrl() != null)
            this.linkedinUrl = dto.getLinkedinUrl();
        if (dto.getResumeUrl() != null)
            this.resumeUrl = dto.getResumeUrl();
        if (dto.getSummary() != null)
            this.summary = dto.getSummary();
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getLinkedinUrl() {
        return linkedinUrl;
    }

    public void setLinkedinUrl(String linkedinUrl) {
        this.linkedinUrl = linkedinUrl;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    // Métodos auxiliares para manipular skills individualmente
    public void addSkill(Skill skill) {
        this.skills.add(skill);
    }

    public void removeSkill(Skill skill) {
        this.skills.remove(skill);
    }
}
