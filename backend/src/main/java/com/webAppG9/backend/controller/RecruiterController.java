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

    // Crear post de trabajo
    @PostMapping("/createJob")
    public ResponseEntity<ResponseDTO<JobPostResponseDTO>> createJob(
            @RequestBody JobPostRequestDTO requestDTO) {

        JobPostResponseDTO createdJob = recruiterService.createJob(requestDTO);
        return ResponseEntity.ok(ResponseDTO.ok(createdJob));
    }

    // Obtener perfil propio (solo recruiter logueado)
    @GetMapping("/get-me")
    public ResponseEntity<ResponseDTO<RecruiterResponseDTO>> getMyProfile() {
        RecruiterResponseDTO profile = recruiterService.getRecruiterByUserId();
        return ResponseEntity.ok(new ResponseDTO<>(profile, null));
    }

    // Actualizar perfil de recruiter
    @PutMapping("/put-me")
    public ResponseEntity<ResponseDTO<RecruiterResponseDTO>> updateProfile(
            @RequestBody RecruiterRequestDTO request) {
        RecruiterResponseDTO updated = recruiterService.updateRecruiterProfile(request);
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
