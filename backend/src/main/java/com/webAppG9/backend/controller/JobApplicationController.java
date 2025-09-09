package com.webAppG9.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.service.JobApplicationService;
import com.webAppG9.backend.dto.JobApplicationResponseDTO;

@RestController
@RequestMapping("/api/job-apply")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<JobApplicationResponseDTO>> applyToJob(
            @RequestBody Map<String, Integer> request) {
        // tomar el id del request
        Integer jobPostId = request.get("jobPostId");
        // armar la respuesta en service
        ResponseDTO<JobApplicationResponseDTO> response = jobApplicationService.applyToJob(jobPostId);
        // devuelve la respuesta de la logica ede service
        return ResponseEntity.ok(response);
    }

    // Cancelar una jobAaplicación
    @DeleteMapping("/{applicationId}")
    public ResponseEntity<ResponseDTO<String>> cancelApplication(@PathVariable Integer applicationId) {
        jobApplicationService.cancelApplication(applicationId);
        return ResponseEntity.ok(new ResponseDTO<>("Aplicación cancelada correctamente", null));
    }

    // Ver mis aplicaciones (usuario logueado)
    @GetMapping("/me")
    public ResponseEntity<ResponseDTO<List<JobApplicationResponseDTO>>> getMyApplications() {
        ResponseDTO<List<JobApplicationResponseDTO>> response = jobApplicationService.getApplicationsByCurrentUser();
        return ResponseEntity.ok(response);
    }

    // Ver postulacion de un trabajo (recruiter/admin)
    @GetMapping("/job/{jobPostId}")
    public ResponseEntity<ResponseDTO<List<JobApplicationResponseDTO>>> getApplicationsByJob(
            @PathVariable Integer jobPostId) {
        ResponseDTO<List<JobApplicationResponseDTO>> response = jobApplicationService.getApplicationsByJob(jobPostId);
        return ResponseEntity.ok(response);
    }

    // Cambiar estado de la jobAplicación (recruiter/admin)
    @PatchMapping("/{applicationId}/status")
    public ResponseEntity<ResponseDTO<String>> updateApplicationStatus(
            @PathVariable Integer applicationId,
            @RequestBody Map<String, String> request) {
        String status = request.get("status");
        jobApplicationService.updateApplicationStatus(applicationId, status);
        return ResponseEntity.ok(new ResponseDTO<>("Estado de la aplicación actualizado a: " + status, null));
    }

}
