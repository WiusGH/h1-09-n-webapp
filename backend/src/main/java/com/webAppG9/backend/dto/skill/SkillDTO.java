package com.webAppG9.backend.dto.skill;

public class SkillDTO {

    private Integer id;
    private String name;

    public SkillDTO() {
    }

    public SkillDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
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
}
