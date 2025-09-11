package com.webAppG9.backend.dto.skill;

import java.util.Set;

public class SkillCreateUpdateDTO {

    private String name;

    private Set<Integer> skills;

    public Set<Integer> getSkills() {
        return skills;
    }

    public void setSkills(Set<Integer> skills) {
        this.skills = skills;
    }

    public SkillCreateUpdateDTO() {
    }

    public SkillCreateUpdateDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
