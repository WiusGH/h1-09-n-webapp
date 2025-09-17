package com.webAppG9.backend.dto.candidates;

import java.util.Set;
import java.util.stream.Collectors;

import com.webAppG9.backend.Model.Candidated;
import com.webAppG9.backend.Model.Skill;

public class CandidatedRequestDTO {

    private String title; // Título profesional
    private String address;
    private String country;
    private String phoneNumber;
    private String resumeUrl; // Link a CV

    private Set<String> skills; // Nombres de las skills del candidato

    // Constructor vacío
    public CandidatedRequestDTO() {
    }

    // Constructor desde entidad
    public CandidatedRequestDTO(Candidated candidated) {
        this.title = candidated.getTitle();
        this.address = candidated.getAddress();
        this.country = candidated.getCountry();
        this.phoneNumber = candidated.getPhoneNumber();
        this.resumeUrl = candidated.getResumeUrl();
        this.skills = candidated.getSkills() != null
                ? candidated.getSkills().stream().map(Skill::getName).collect(Collectors.toSet())
                : null;
    }

    // ====== Getters y Setters ======

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

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }
}
