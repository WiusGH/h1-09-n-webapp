package com.webAppG9.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.dto.candidates.CandidateResponseDTO;
import com.webAppG9.backend.dto.jobapplication.JobApplicationResponseDTO;
import com.webAppG9.backend.dto.jobpost.JobPostRequestDTO;
import com.webAppG9.backend.dto.jobpost.JobPostResponseDTO;
import com.webAppG9.backend.dto.recruiter.RecruiterRequestDTO;
import com.webAppG9.backend.dto.recruiter.RecruiterResponseDTO;
import com.webAppG9.backend.dto.recruiter.UpdateStatusRequestDTO;
import com.webAppG9.backend.service.RecruiterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/recruiters")
@Tag(name = "Recruiters", description = "APIs para la gestión de reclutadores y sus publicaciones de trabajo")
public class RecruiterController {

    private final RecruiterService recruiterService;

    public RecruiterController(RecruiterService recruiterService) {
        this.recruiterService = recruiterService;
    }

    @Operation(summary = "Crear una nueva oferta laboral", description = "Permite a un recruiter crear un nuevo post de trabajo con los datos enviados en la petición.")
    @PostMapping("/createJob")
    public ResponseEntity<ResponseDTO<JobPostResponseDTO>> createJob(
            @RequestBody JobPostRequestDTO requestDTO) {
        JobPostResponseDTO createdJob = recruiterService.createJob(requestDTO);
        return ResponseEntity.ok(ResponseDTO.ok(createdJob));
    }

    @Operation(summary = "Obtener perfil propio", description = "Devuelve la información del perfil del recruiter actualmente autenticado.")
    @GetMapping("/get-me")
    public ResponseEntity<ResponseDTO<RecruiterResponseDTO>> getMyProfile() {
        RecruiterResponseDTO profile = recruiterService.getRecruiterByUserId();
        return ResponseEntity.ok(new ResponseDTO<>(profile, null));
    }

    @Operation(summary = "Actualizar perfil de recruiter", description = "Permite actualizar los datos del perfil del recruiter logueado.")
    @PutMapping("/put-me")
    public ResponseEntity<ResponseDTO<RecruiterResponseDTO>> updateProfile(
            @RequestBody RecruiterRequestDTO request) {
        RecruiterResponseDTO updated = recruiterService.updateRecruiterProfile(request);
        return ResponseEntity.ok(new ResponseDTO<>(updated, "Perfil actualizado"));
    }

    @Operation(summary = "Ver postulaciones de un trabajo", description = "Devuelve todas las aplicaciones de candidatos para un job post específico.")
    @GetMapping("/job/{jobPostId}")
    public ResponseEntity<ResponseDTO<List<JobApplicationResponseDTO>>> getApplicationsByJob(
            @Parameter(description = "ID del post de trabajo", example = "15") @PathVariable Integer jobPostId) {
        ResponseDTO<List<JobApplicationResponseDTO>> response = recruiterService.getApplicationsByJob(jobPostId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Actualizar estado de una aplicación", description = "Permite al recruiter cambiar el estado de una aplicación de trabajo (ejemplo: Aceptado, Rechazado, En proceso).")
    @PatchMapping("/{applicationId}/status")
    public ResponseEntity<ResponseDTO<String>> updateStatus(
            @Parameter(description = "ID de la aplicación de trabajo", example = "42") @PathVariable Integer applicationId,
            @RequestBody UpdateStatusRequestDTO request) {
        recruiterService.updateApplicationStatus(applicationId, request.getStatus(), request.getStatusMessage());
        return ResponseEntity.ok(new ResponseDTO<>("Estado actualizado correctamente", null));
    }

    @Operation(summary = "Obtener todos los candidatos activos", description = "Devuelve la lista de candidatos que están actualmente activos en la plataforma. Disponible para Recruiter/Admin.")
    @GetMapping("/active")
    public ResponseEntity<ResponseDTO<List<CandidateResponseDTO>>> getAllActiveCandidates() {
        List<CandidateResponseDTO> candidates = recruiterService.getAllActiveCandidates();
        return ResponseEntity.ok(ResponseDTO.ok(candidates));
    }
}
