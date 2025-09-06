package com.webAppG9.backend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.webAppG9.backend.Model.JobPost;
import com.webAppG9.backend.dto.JobPostDTO;
import com.webAppG9.backend.repository.JobPostRepository;

@Service
public class JobPostService {

    private final JobPostRepository jobPostRepository;

    public JobPostService(JobPostRepository jobPostRepository) {
        this.jobPostRepository = jobPostRepository;
    }

    // Arma la respuesta del post
    private Map<String, Object> buildJobPostResponse(JobPost jobPost) {
        Map<String, Object> data = new HashMap<>();
        data.put("jobPostData", new JobPostDTO(
                jobPost.getTitle(),
                jobPost.getDescription(),
                jobPost.getCompanyName(),
                jobPost.getIsActive(),
                jobPost.getExpiresAt()));
        return data;
    }

    // Obtener todos los trabajos
    public List<JobPostDTO> getAllJobs() {
        List<JobPostDTO> allJobs = jobPostRepository.findAll().stream()
                .map(jobPost -> new JobPostDTO(
                        jobPost.getTitle(),
                        jobPost.getDescription(),
                        jobPost.getCompanyName(),
                        jobPost.getIsActive(),
                        jobPost.getExpiresAt()))
                .toList();

        if (allJobs.isEmpty()) {
            throw new RuntimeException("No se encontraron posteos de trabajos");
        }

        return allJobs;
    }

    // Crear un post de trabajo
    public Map<String, Object> createJob(JobPost jobPost) {
        JobPost savedJob = jobPostRepository.save(jobPost);
        return buildJobPostResponse(savedJob);
    }

    // Actualizar un post de trabajo
    public Map<String, Object> updateJob(Integer id, JobPost updatedJob) {
        JobPost jobPost = jobPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Posteo de trabajo no encontrado"));

        jobPost.setTitle(updatedJob.getTitle());
        jobPost.setDescription(updatedJob.getDescription());
        jobPost.setCompanyName(updatedJob.getCompanyName());
        jobPost.setIsActive(updatedJob.getIsActive());
        jobPost.setExpiresAt(updatedJob.getExpiresAt());

        JobPost savedJob = jobPostRepository.save(jobPost);
        return buildJobPostResponse(savedJob);
    }

    // Eliminar un post de trabajo
    public void deleteJob(Integer id) {
        JobPost jobPost = jobPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Posteo de trabajo no encontrado"));

        jobPostRepository.delete(jobPost);
    }

}
