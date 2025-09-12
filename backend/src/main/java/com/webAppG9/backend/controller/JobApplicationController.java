package com.webAppG9.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.dto.jobapplication.JobApplicationRequestDTO;
import com.webAppG9.backend.dto.jobapplication.JobApplicationResponseDTO;
import com.webAppG9.backend.service.JobApplicationService;

@RestController
@RequestMapping("/api/job-apply")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    // Aplicar a una oferta laboral
    @PostMapping
    public ResponseEntity<ResponseDTO<JobApplicationResponseDTO>> applyToJob(
            @RequestBody JobApplicationRequestDTO request) {
        // tomar el id del request
        Integer jobPostId = request.getJobPostId();

        // armar la respuesta en service
        ResponseDTO<JobApplicationResponseDTO> response = jobApplicationService.applyToJob(jobPostId);
        // devuelve la respuesta de la logica ede service
        return ResponseEntity.ok(response);
    }

    // Cancelar una jobAaplicación
    @DeleteMapping("/{applicationId}")
    public ResponseEntity<String> cancelApplication(@PathVariable Integer applicationId) {
        String response = jobApplicationService.cancelApplication(applicationId);
        return ResponseEntity.ok(response);
    }

    // Ver mis aplicaciones (usuario logueado)
    @GetMapping("/me")
    public ResponseEntity<ResponseDTO<List<JobApplicationResponseDTO>>> getMyApplications() {
        ResponseDTO<List<JobApplicationResponseDTO>> response = jobApplicationService.getApplicationsByCurrentUser();
        return ResponseEntity.ok(response);
    }

}
