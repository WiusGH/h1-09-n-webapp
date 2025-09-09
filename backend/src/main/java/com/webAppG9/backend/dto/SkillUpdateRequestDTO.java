package com.webAppG9.backend.dto;

import java.util.Set;

public class SkillUpdateRequestDTO {
    private Set<Integer> skills;

    public Set<Integer> getSkills() {
        return skills;
    }

    public void setSkills(Set<Integer> skills) {
        this.skills = skills;
    }
}
