package com.webAppG9.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.dto.jobpost.JobPostRequestDTO;
import com.webAppG9.backend.dto.jobpost.JobPostResponseDTO;
import com.webAppG9.backend.service.JobPostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/jobPost")
@Tag(name = "Job Posts", description = "APIs para la gestión de publicaciones de trabajo")
public class JobPostController {

    private final JobPostService jobPostService;

    public JobPostController(JobPostService jobPostService) {
        this.jobPostService = jobPostService;
    }

    @Operation(summary = "Obtener todos los posteos", description = "Devuelve una lista con todos los posteos de trabajo disponibles.")
    @GetMapping
    public ResponseEntity<ResponseDTO<List<JobPostResponseDTO>>> getAllJobs() {
        List<JobPostResponseDTO> jobs = jobPostService.getAllJobs();
        return ResponseEntity.ok(ResponseDTO.ok(jobs));
    }

    @Operation(summary = "Actualizar un posteo de trabajo", description = "Actualiza la información de un posteo existente según su ID.")
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> updateJob(
            @Parameter(description = "ID del posteo de trabajo", example = "1") @PathVariable Integer id,
            @RequestBody JobPostRequestDTO requestDTO) {

        String message = jobPostService.updateJob(id, requestDTO);
        return ResponseEntity.ok(ResponseDTO.ok(message));
    }

    @Operation(summary = "Eliminar un posteo de trabajo", description = "Elimina un posteo de trabajo de la base de datos según su ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteJob(
            @Parameter(description = "ID del posteo de trabajo", example = "1") @PathVariable Integer id) {
        String response = jobPostService.deleteJob(id);
        return ResponseEntity.ok(ResponseDTO.ok(response));
    }

    @Operation(summary = "Cambiar estado de un posteo", description = "Activa o desactiva un posteo de trabajo según su ID.")
    @PatchMapping("/{id}/status")
    public ResponseEntity<ResponseDTO<String>> toggleJobPostStatus(
            @Parameter(description = "ID del posteo de trabajo", example = "1") @PathVariable Integer id) {
        String message = jobPostService.toggleJobPostStatus(id);
        return ResponseEntity.ok(ResponseDTO.ok(message));
    }

    @Operation(summary = "Buscar posteos por skill", description = "Busca publicaciones de trabajo que contengan la skill especificada.")
    @GetMapping("/search")
    public ResponseEntity<ResponseDTO<List<JobPostResponseDTO>>> searchJobPostsBySkill(
            @Parameter(description = "Nombre de la skill a buscar", example = "Java") @RequestParam String query) {

        List<JobPostResponseDTO> results = jobPostService.searchBySkillQuery(query);
        return ResponseEntity.ok(ResponseDTO.ok(results));
    }

    @Operation(summary = "Buscar posteos por palabra clave", description = "Busca publicaciones de trabajo que coincidan con una palabra clave en título o descripción.")
    @GetMapping("/search-job")
    public ResponseEntity<ResponseDTO<List<JobPostResponseDTO>>> searchJobPostsByKeyword(
            @Parameter(description = "Palabra clave para buscar en los posteos", example = "backend") @RequestParam String keyword) {

        String normalizedKeyword = keyword.trim().toLowerCase();
        List<JobPostResponseDTO> results = jobPostService.searchByJobKeyword(normalizedKeyword);

        return ResponseEntity.ok(ResponseDTO.ok(results));
    }
}
