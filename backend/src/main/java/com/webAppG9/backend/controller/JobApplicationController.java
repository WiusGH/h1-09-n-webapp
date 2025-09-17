package com.webAppG9.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.dto.jobapplication.JobApplicationRequestDTO;
import com.webAppG9.backend.dto.jobapplication.JobApplicationResponseDTO;
import com.webAppG9.backend.service.JobApplicationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/job-apply")
@Tag(name = "Job Applications", description = "APIs para la gestión de postulaciones a ofertas laborales")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @Operation(summary = "Aplicar a una oferta laboral", description = "Permite al usuario actual aplicar a un post de trabajo usando el ID de la oferta.")
    @PostMapping
    public ResponseEntity<ResponseDTO<JobApplicationResponseDTO>> applyToJob(
            @RequestBody JobApplicationRequestDTO request) {
        Integer jobPostId = request.getJobPostId();
        JobApplicationResponseDTO response = jobApplicationService.applyToJob(jobPostId);
        return ResponseEntity.ok(ResponseDTO.ok(response));
    }

    @Operation(summary = "Cancelar una aplicación", description = "Permite al usuario cancelar su aplicación a una oferta laboral específica según el ID de la aplicación.")
    @DeleteMapping("/{applicationId}")
    public ResponseEntity<ResponseDTO<String>> cancelApplication(
            @Parameter(description = "ID de la aplicación a cancelar", example = "42") @PathVariable Integer applicationId) {
        String response = jobApplicationService.cancelApplication(applicationId);
        return ResponseEntity.ok(ResponseDTO.ok(response));
    }

    @Operation(summary = "Ver mis aplicaciones", description = "Devuelve la lista de aplicaciones realizadas por el usuario actualmente autenticado.")
    @GetMapping("/me")
    public ResponseEntity<ResponseDTO<List<JobApplicationResponseDTO>>> getMyApplications() {
        List<JobApplicationResponseDTO> response = jobApplicationService.getApplicationsByCurrentUser();
        return ResponseEntity.ok(ResponseDTO.ok(response));
    }
}
