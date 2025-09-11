package com.webAppG9.backend.service;

import com.webAppG9.backend.Model.Skill;
import com.webAppG9.backend.dto.skill.SkillCreateUpdateDTO;
import com.webAppG9.backend.dto.skill.SkillDTO;
import com.webAppG9.backend.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    // Obtener todas las skills
    public List<SkillDTO> getAllSkills() {
        return skillRepository.findAll()
                .stream()
                .map(skill -> new SkillDTO(skill.getId(), skill.getName()))
                .collect(Collectors.toList());
    }

    // Crear nueva skill
    public SkillDTO createSkill(SkillCreateUpdateDTO request) {
        Skill skill = new Skill();
        skill.setName(request.getName());
        Skill saved = skillRepository.save(skill);
        return new SkillDTO(saved.getId(), saved.getName());
    }

    // Actualizar skill existente
    public SkillDTO updateSkill(Integer id, SkillCreateUpdateDTO request) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill no encontrada con id " + id));

        skill.setName(request.getName());
        Skill updated = skillRepository.save(skill);
        return new SkillDTO(updated.getId(), updated.getName());
    }
}
