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

@RestController
@RequestMapping("/api/recruiters")
public class RecruiterController {

    private final RecruiterService recruiterService;

    public RecruiterController(RecruiterService recruiterService) {
        this.recruiterService = recruiterService;
    }

    // Candidato solicita ser Recruiter
    @PostMapping("/request/{userId}")
    public ResponseEntity<ResponseDTO<String>> requestRecruiter(
            @PathVariable Integer userId,
            @RequestBody RecruiterRequestDTO request) {
        recruiterService.requestRecruiterUpgrade(userId, request);
        return ResponseEntity.ok(new ResponseDTO<>("Solicitud enviada. Espera aprobación de un administrador.", null));
    }

    // Crear post de trabajo
    @PostMapping
    public ResponseEntity<ResponseDTO<JobPostResponseDTO>> createJob(
            @RequestBody JobPostRequestDTO requestDTO) {

        JobPostResponseDTO createdJob = recruiterService.createJob(requestDTO);
        return ResponseEntity.ok(ResponseDTO.ok(createdJob));
    }

    // Obtener perfil propio (solo recruiter logueado)
    @GetMapping("/me/{userId}")
    public ResponseEntity<ResponseDTO<RecruiterResponseDTO>> getMyProfile(@PathVariable Integer userId) {
        RecruiterResponseDTO profile = recruiterService.getRecruiterByUserId(userId);
        return ResponseEntity.ok(new ResponseDTO<>(profile, "Perfil obtenido"));
    }

    // Actualizar perfil de recruiter
    @PutMapping("/me/{userId}")
    public ResponseEntity<ResponseDTO<RecruiterResponseDTO>> updateProfile(
            @PathVariable Integer userId,
            @RequestBody RecruiterRequestDTO request) {
        RecruiterResponseDTO updated = recruiterService.updateRecruiterProfile(userId, request);
        return ResponseEntity.ok(new ResponseDTO<>(updated, "Perfil actualizado"));
    }

    // Ver postulacion de un trabajo
    @GetMapping("/job/{jobPostId}")
    public ResponseEntity<ResponseDTO<List<JobApplicationResponseDTO>>> getApplicationsByJob(
            @PathVariable Integer jobPostId) {
        ResponseDTO<List<JobApplicationResponseDTO>> response = recruiterService.getApplicationsByJob(jobPostId);
        return ResponseEntity.ok(response);
    }

    // @GetMapping
    // public ResponseEntity<ResponseDTO<List<JobApplicationResponseDTO>>>
    // getAllJobPost() {
    // ResponseDTO<List<JobApplicationResponseDTO>> response =
    // recruiterService.getAllJobPost();
    // return ResponseEntity.ok(response);
    // }

    // Cambiar estado de la jobAplicación
    @PatchMapping("/{applicationId}/status")
    public ResponseEntity<ResponseDTO<String>> updateStatus(
            @PathVariable Integer applicationId,
            @RequestBody UpdateStatusRequestDTO request) {

        recruiterService.updateApplicationStatus(applicationId, request.getStatus(), request.getStatusMessage());
        return ResponseEntity.ok(new ResponseDTO<>("Estado actualizado correctamente", null));
    }

    // obtener todos los candidatos activos (Recruiter/Admin)
    @GetMapping("/active")
    public ResponseEntity<ResponseDTO<List<CandidateResponseDTO>>> getAllActiveCandidates() {
        List<CandidateResponseDTO> candidates = recruiterService.getAllActiveCandidates();
        return ResponseEntity.ok(ResponseDTO.ok(candidates));
    }
}
