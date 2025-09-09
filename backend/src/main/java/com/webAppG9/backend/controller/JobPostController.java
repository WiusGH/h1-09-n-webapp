package com.webAppG9.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.webAppG9.backend.dto.JobPostDTO;
import com.webAppG9.backend.dto.JobPostRequestDTO;
import com.webAppG9.backend.dto.ResponseDTO;
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
    public ResponseEntity<ResponseDTO<List<JobPostDTO>>> getAllJobs() {
        List<JobPostDTO> jobs = jobPostService.getAllJobs();
        return ResponseEntity.ok(new ResponseDTO<>(jobs, null));
    }

    // Crear post de trabajo
    @PostMapping
    public ResponseEntity<ResponseDTO<Map<String, Object>>> createJob(
            @RequestBody JobPostRequestDTO requestDTO) {
        Map<String, Object> data = jobPostService.createJob(requestDTO);
        return ResponseEntity.ok(new ResponseDTO<>(data, null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<Map<String, Object>>> updateJob(
            @PathVariable Integer id,
            @RequestBody JobPostRequestDTO requestDTO) {
        Map<String, Object> data = jobPostService.updateJob(id, requestDTO);
        return ResponseEntity.ok(new ResponseDTO<>(data, null));
    }

    // Eliminar un post de trabajo
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteJob(@PathVariable Integer id) {
        jobPostService.deleteJob(id);
        return ResponseEntity.ok(new ResponseDTO<>("Posteo eliminado correctamente", null));
    }

    // desactivar post de trabajo
    @PatchMapping("/{id}/status")
    public ResponseEntity<ResponseDTO<Map<String, Object>>> toggleJobPostStatus(@PathVariable Integer id) {
        Map<String, Object> data = jobPostService.toggleJobPostStatus(id);
        return ResponseEntity.ok(new ResponseDTO<>(data, null));
    }

}
