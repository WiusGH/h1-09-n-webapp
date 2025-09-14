package com.webAppG9.backend.controller;

import com.webAppG9.backend.dto.skill.SkillRequestDTO;
import com.webAppG9.backend.dto.skill.SkillResponseDTO;
import com.webAppG9.backend.service.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    // Listar todas las skills
    @GetMapping
    public ResponseEntity<List<SkillResponseDTO>> getAllSkills() {
        return ResponseEntity.ok(skillService.getAllSkills());
    }

    // Crear nueva skill
    @PostMapping
    public ResponseEntity<SkillResponseDTO> createSkill(@RequestBody SkillRequestDTO request) {
        SkillResponseDTO created = skillService.createSkill(request);
        return ResponseEntity.ok(created);
    }

    // Actualizar skill existente
    @PutMapping("/{id}")
    public ResponseEntity<SkillResponseDTO> updateSkill(
            @PathVariable Integer id,
            @RequestBody SkillRequestDTO request) {

        SkillResponseDTO updated = skillService.updateSkill(id, request);
        return ResponseEntity.ok(updated);
    }
}
