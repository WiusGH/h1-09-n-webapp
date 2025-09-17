package com.webAppG9.backend.dto.candidates;

import java.util.Set;
import java.util.stream.Collectors;

import com.webAppG9.backend.Model.Candidated;
import com.webAppG9.backend.Model.Skill;

public class CandidateResponseDTO {

    private Integer candidateId;
    // private String name;
    // private String lastName;
    private String title;
    private String address;
    private String country;
    private String phoneNumber;
    private String email;
    private String resumeUrl;

    private Boolean active;

    private Set<String> skills;

    // Constructor vacío
    public CandidateResponseDTO() {
    }

    // Constructor desde entidad
    public CandidateResponseDTO(Candidated candidated) {
        this.candidateId = candidated.getId();
        // this.name = candidated.getUser() != null ? candidated.getUser().getName() :
        // null;
        // this.lastName = candidated.getUser() != null ?
        // candidated.getUser().getLastName() : null;
        this.title = candidated.getTitle();
        this.address = candidated.getAddress();
        this.country = candidated.getCountry();
        this.phoneNumber = candidated.getPhoneNumber();
        this.email = candidated.getUser() != null ? candidated.getUser().getEmail() : null;
        this.resumeUrl = candidated.getResumeUrl();
        this.active = candidated.getActive();
        this.skills = candidated.getSkills() != null
                ? candidated.getSkills().stream().map(Skill::getName).collect(Collectors.toSet())
                : null;
    }

    // ====== Getters y Setters ======
    public Integer getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
    }

    // public String getName() {
    // return name;
    // }

    // public void setName(String name) {
    // this.name = name;
    // }

    // public String getLastName() {
    // return lastName;
    // }

    // public void setLastName(String lastName) {
    // this.lastName = lastName;
    // }

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

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
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
