package com.webAppG9.backend.dto.candidates;

import java.util.Set;
import java.util.stream.Collectors;

import com.webAppG9.backend.Model.Candidated;
import com.webAppG9.backend.Model.Skill;

public class CandidateResponseDTO {

    private Integer candidateId;
    private String title;
    private String address;
    private String country;
    private String phoneNumber;
    private Boolean active;

    private Set<String> skills; // Nombres de las skills del candidato

    // Cosntructor vacio para JPA
    public CandidateResponseDTO() {

    }

    // Constructor desde entidad
    public CandidateResponseDTO(Candidated candidated) {
        this.candidateId = candidated.getId();
        this.title = candidated.getTitle();
        this.address = candidated.getAddress();
        this.country = candidated.getCountry();
        this.phoneNumber = candidated.getPhoneNumber();
        this.active = candidated.getActive();
        this.skills = candidated.getSkills() != null
                ? candidated.getSkills().stream().map(Skill::getName).collect(Collectors.toSet())
                : null;

    }

    // Getters y setters
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }
}
