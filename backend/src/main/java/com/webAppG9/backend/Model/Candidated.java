package com.webAppG9.backend.Model;

import java.util.HashSet;
import java.util.Set;

import com.webAppG9.backend.dto.candidates.CandidateResponseDTO;

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

    public void applyFromDTO(CandidateResponseDTO dto) {
        if (dto.getTitle() != null) {
            this.title = dto.getTitle();
        }
        if (dto.getAddress() != null) {
            this.address = dto.getAddress();
        }
        if (dto.getCountry() != null) {
            this.country = dto.getCountry();
        }
        if (dto.getPhoneNumber() != null) {
            this.phoneNumber = dto.getPhoneNumber();
        }
        if (dto.getActive() != null) {
            this.active = dto.getActive();
        }

    }

    // Getters y setters
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
