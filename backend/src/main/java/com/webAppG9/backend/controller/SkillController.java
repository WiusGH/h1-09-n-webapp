package com.webAppG9.backend.controller;

import com.webAppG9.backend.dto.skill.SkillRequestDTO;
import com.webAppG9.backend.dto.skill.SkillResponseDTO;
import com.webAppG9.backend.service.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@Tag(name = "Skills", description = "APIs para la gestión de habilidades")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @Operation(summary = "Listar todas las habilidades", description = "Devuelve una lista con todas las skills disponibles en el sistema.")
    @GetMapping
    public ResponseEntity<List<SkillResponseDTO>> getAllSkills() {
        return ResponseEntity.ok(skillService.getAllSkills());
    }

    @Operation(summary = "Crear nueva habilidad", description = "Crea una nueva skill a partir de los datos enviados en el cuerpo de la petición.")
    @PostMapping
    public ResponseEntity<SkillResponseDTO> createSkill(
            @RequestBody SkillRequestDTO request) {
        SkillResponseDTO created = skillService.createSkill(request);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Actualizar habilidad existente", description = "Actualiza una skill específica según el ID proporcionado.")
    @PutMapping("/{id}")
    public ResponseEntity<SkillResponseDTO> updateSkill(
            @Parameter(description = "ID de la skill a actualizar", example = "3") @PathVariable Integer id,
            @RequestBody SkillRequestDTO request) {

        SkillResponseDTO updated = skillService.updateSkill(id, request);
        return ResponseEntity.ok(updated);
    }
}
