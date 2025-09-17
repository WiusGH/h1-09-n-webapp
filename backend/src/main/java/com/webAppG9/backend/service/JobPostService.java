package com.webAppG9.backend.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.webAppG9.backend.Model.JobPost;
import com.webAppG9.backend.Model.Skill;
import com.webAppG9.backend.dto.jobpost.JobPostRequestDTO;
import com.webAppG9.backend.dto.jobpost.JobPostResponseDTO;
import com.webAppG9.backend.exception.candidate.CandidateNotFoundException;
import com.webAppG9.backend.exception.jobpost.JobPostNotFoundException;
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

    // Obtener todos los trabajos
    public List<JobPostResponseDTO> getAllJobs() {
        return jobPostRepository.findAll().stream()
                .map(JobPostResponseDTO::new)
                .toList();
    }

    // Actualizar un post de trabajo
    public String updateJob(Integer id, JobPostRequestDTO request) {
        JobPost jobPost = jobPostRepository.findById(id)
                .orElseThrow(CandidateNotFoundException::new);

        // Convertir nombres de skills a objetos Skill
        Set<Skill> skillSet = new HashSet<>();
        if (request.getSkills() != null && !request.getSkills().isEmpty()) {
            skillSet = request.getSkills().stream()
                    .map(name -> skillRepository.findByName(name)
                            .orElseThrow(() -> new RuntimeException("Skill con name " + name + " no encontrada")))
                    .collect(Collectors.toSet());
        }

        jobPost.setSkills(skillSet);

        jobPostRepository.save(jobPost);

        return "Job actualizado correctamente";
    }

    // Eliminar un posteo de trabajo
    public String deleteJob(Integer id) {
        JobPost jobPost = jobPostRepository.findById(id)
                .orElseThrow(CandidateNotFoundException::new);

        jobPostRepository.delete(jobPost);

        return "Posteo eliminado correctamente";
    }

    // Desactivar post de trabajo
    public String toggleJobPostStatus(Integer id) {
        JobPost jobPost = jobPostRepository.findById(id)
                .orElseThrow(CandidateNotFoundException::new);

        // Cambiar estado
        jobPost.setIsActive(!jobPost.getIsActive());
        jobPostRepository.save(jobPost);

        // Devolver mensaje según el estado
        return jobPost.getIsActive() ? "JobPost activado" : "JobPost desactivado";
    }

    // Buscar trabajos por Id (Metodo para CandidatedController)
    public JobPostResponseDTO getJobPostById(Integer jobPostId) {
        JobPost jobPost = jobPostRepository.findById(jobPostId)
                .orElseThrow(JobPostNotFoundException::new);

        if (!jobPost.getIsActive()) {
            throw new RuntimeException("Posteo de trabajo no activo");
        }

        return new JobPostResponseDTO(jobPost);
    }

    // Buscar trabajos por habilidades en el buscador
    public List<JobPostResponseDTO> searchBySkillQuery(String query) {
        return jobPostRepository.findBySkillQuery(query)
                .stream()
                .map(JobPostResponseDTO::new) // convierto entidad -> DTO
                .toList();
    }

    public List<JobPostResponseDTO> searchByJobKeyword(String keyword) {
        return jobPostRepository.findByJobKeyword(keyword)
                .stream()
                .map(JobPostResponseDTO::new) // convierto entidad -> DTO
                .toList();
    }

}
