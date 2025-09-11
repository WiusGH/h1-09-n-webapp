package com.webAppG9.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.dto.jobpost.JobPostRequestDTO;
import com.webAppG9.backend.dto.jobpost.JobPostResponseDTO;
import com.webAppG9.backend.service.JobPostService;

@RestController
@RequestMapping("/api/jobPost")
public class JobPostController {

    private final JobPostService jobPostService;

    public JobPostController(JobPostService jobPostService) {
        this.jobPostService = jobPostService;
    }

    // Obtener todos los posteos
    @GetMapping
    public ResponseEntity<ResponseDTO<List<JobPostResponseDTO>>> getAllJobs() {
        List<JobPostResponseDTO> jobs = jobPostService.getAllJobs();
        return ResponseEntity.ok(ResponseDTO.ok(jobs));
    }

    // Crear post de trabajo
    @PostMapping
    public ResponseEntity<ResponseDTO<JobPostResponseDTO>> createJob(
            @RequestBody JobPostRequestDTO requestDTO) {

        JobPostResponseDTO createdJob = jobPostService.createJob(requestDTO);
        return ResponseEntity.ok(ResponseDTO.ok(createdJob));
    }

    // Actualizar Posteo de trabajo
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> updateJob(
            @PathVariable Integer id,
            @RequestBody JobPostRequestDTO requestDTO) {

        String message = jobPostService.updateJob(id, requestDTO);
        return ResponseEntity.ok(ResponseDTO.ok(message));
    }

    // Eliminar un post de trabajo
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteJob(@PathVariable Integer id) {
        String response = jobPostService.deleteJob(id);
        return ResponseEntity.ok(ResponseDTO.ok(response));
    }

    // Actualozar status de posteo
    @PatchMapping("/{id}/status")
    public ResponseEntity<ResponseDTO<String>> toggleJobPostStatus(@PathVariable Integer id) {
        String message = jobPostService.toggleJobPostStatus(id);
        return ResponseEntity.ok(ResponseDTO.ok(message));
    }

}
