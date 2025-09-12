package com.webAppG9.backend.dto.candidates;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import com.webAppG9.backend.Model.Candidated;
import com.webAppG9.backend.Model.Skill;

public class CandidatedRequestDTO {

    private Integer candidateId;
    private String title; // Título profesional
    private String address;
    private String country;
    private String phoneNumber;
    private String email; // Email del user asociado
    private LocalDate dateOfBirth;
    private String education; // Educación básica
    private String experience; // Experiencia laboral resumida
    private String linkedinUrl; // Perfil LinkedIn
    private String resumeUrl; // Link a CV
    private String summary; // Breve descripción del perfil
    private Boolean active;

    private Set<Integer> skills; // Nombres de las skills del candidato

    // Constructor vacío
    public CandidatedRequestDTO() {
    }

    // Constructor desde entidad
    public CandidatedRequestDTO(Candidated candidated) {
        this.candidateId = candidated.getId();
        this.title = candidated.getTitle();
        this.address = candidated.getAddress();
        this.country = candidated.getCountry();
        this.phoneNumber = candidated.getPhoneNumber();
        this.email = candidated.getUser() != null ? candidated.getUser().getEmail() : null;
        this.dateOfBirth = candidated.getDateOfBirth();
        this.education = candidated.getEducation();
        this.experience = candidated.getExperience();
        this.linkedinUrl = candidated.getLinkedinUrl();
        this.resumeUrl = candidated.getResumeUrl();
        this.summary = candidated.getSummary();
        this.active = candidated.getActive();
        this.skills = candidated.getSkills() != null
                ? candidated.getSkills().stream().map(Skill::getId).collect(Collectors.toSet())
                : null;
    }

    // ====== Getters y Setters ======
    public Integer getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<Integer> getSkills() {
        return skills;
    }

    public void setSkills(Set<Integer> skills) {
        this.skills = skills;
    }
}
