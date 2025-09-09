package com.webAppG9.backend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.webAppG9.backend.Model.JobPost;
import com.webAppG9.backend.Model.Skill;
import com.webAppG9.backend.dto.JobPostDTO;
import com.webAppG9.backend.dto.JobPostRequestDTO;
import com.webAppG9.backend.repository.JobPostRepository;
import com.webAppG9.backend.repository.SkillRepository;

@Service
public class JobPostService {

    private final JobPostRepository jobPostRepository;
    private final SkillRepository skillRepository;

    public JobPostService(JobPostRepository jobPostRepository, SkillRepository skillRepository) {
        this.jobPostRepository = jobPostRepository;
        this.skillRepository = skillRepository;
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

    // Buscar trabajos por Id (Metodo para CandidatedController)
    public JobPost getJobPostById(Integer jobPostId) {
        JobPost jobPost = jobPostRepository.findById(jobPostId)
                .orElseThrow(() -> new RuntimeException("Posteo de trabajo no encontrado"));

        if (!jobPost.getIsActive()) {
            throw new RuntimeException("Posteo de trabajo no activo");
        }
        return jobPost;
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
    public Map<String, Object> createJob(JobPostRequestDTO request) {
        // Convertir IDs de skills a objetos Skill
        Set<Skill> skillSet = skillRepository.findAllById(request.getSkills())
                .stream()
                .collect(Collectors.toSet());

        JobPost jobPost = new JobPost();
        jobPost.setTitle(request.getTitle());
        jobPost.setDescription(request.getDescription());
        jobPost.setCompanyName(request.getCompanyName());
        jobPost.setIsActive(request.isActive());
        jobPost.setExpiresAt(request.getExpiresAt());
        jobPost.setSkills(skillSet);

        JobPost savedJob = jobPostRepository.save(jobPost);
        return buildJobPostResponse(savedJob);
    }

    // Actualizar un post de trabajo
    public Map<String, Object> updateJob(Integer id, JobPostRequestDTO request) {
        JobPost jobPost = jobPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Posteo de trabajo no encontrado"));

        jobPost.setTitle(request.getTitle());
        jobPost.setDescription(request.getDescription());
        jobPost.setCompanyName(request.getCompanyName());
        jobPost.setIsActive(request.isActive());
        jobPost.setExpiresAt(request.getExpiresAt());

        // Actualizar skills
        Set<Skill> skillSet = skillRepository.findAllById(request.getSkills())
                .stream()
                .collect(Collectors.toSet());
        jobPost.setSkills(skillSet);

        JobPost savedJob = jobPostRepository.save(jobPost);
        return buildJobPostResponse(savedJob);
    }

    // Eliminar un post de trabajo
    public void deleteJob(Integer id) {
        JobPost jobPost = jobPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Posteo de trabajo no encontrado"));

        jobPostRepository.delete(jobPost);
    }

    // Desactivar post de trabajo
    public Map<String, Object> toggleJobPostStatus(Integer id) {
        JobPost jobPost = jobPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job no encontrado"));

        jobPost.setIsActive(!jobPost.getIsActive());
        jobPostRepository.save(jobPost);

        Map<String, Object> response = new HashMap<>();
        response.put("message", jobPost.getIsActive() ? "JobPost pausado" : "JobPost activado");
        response.put("active", jobPost.getIsActive());
        return response;
    }

}
