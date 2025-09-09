package com.webAppG9.backend.service;

import com.webAppG9.backend.Model.Candidated;
import com.webAppG9.backend.Model.Skill;
import com.webAppG9.backend.Model.User;
import com.webAppG9.backend.dto.CandidateResponseDTO;
import com.webAppG9.backend.dto.JobPostResponseDTO;
import com.webAppG9.backend.repository.CandidatedRepository;
import com.webAppG9.backend.repository.JobPostRepository;
import com.webAppG9.backend.repository.SkillRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidatedService {

    private final CandidatedRepository candidatedRepository;
    private final JobPostRepository jobPostRepository;
    private final SkillRepository skillRepository;

    public CandidatedService(CandidatedRepository candidatedRepository,
            JobPostRepository jobPostRepository,
            SkillRepository skillRepository) {
        this.candidatedRepository = candidatedRepository;
        this.jobPostRepository = jobPostRepository;
        this.skillRepository = skillRepository;
    }

    // Crear candidato asociado a un usuario desde el registro
    public Candidated createCandidateForUser(User user) {
        try {
            Candidated candidate = new Candidated();
            candidate.setUser(user);
            candidate.setActive(true);
            return candidatedRepository.save(candidate);
        } catch (Exception e) {
            throw new RuntimeException("Error creando perfil de candidato", e);
        }
    }

    // Obtener candidato por userId
    public CandidateResponseDTO getCandidateById(Integer id) {
        Candidated candidate = candidatedRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado"));
        return mapToDTO(candidate);
    }

    // Actualizar candidato
    public CandidateResponseDTO updateCandidate(Integer id, CandidateResponseDTO updateRequest) {
        Candidated candidate = candidatedRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado"));

        if (updateRequest.getTitle() != null)
            candidate.setTitle(updateRequest.getTitle());
        if (updateRequest.getAddress() != null)
            candidate.setAddress(updateRequest.getAddress());
        if (updateRequest.getCountry() != null)
            candidate.setCountry(updateRequest.getCountry());
        if (updateRequest.getPhoneNumber() != null)
            candidate.setPhoneNumber(updateRequest.getPhoneNumber());

        Candidated updatedCandidate = candidatedRepository.save(candidate);
        return mapToDTO(updatedCandidate);
    }

    // Actualizar skills de un candidato
    @Transactional
    public CandidateResponseDTO updateSkillsByIds(Integer candidateId, Set<Integer> skillIds) {
        Candidated candidate = candidatedRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado"));

        Set<Skill> skills = skillIds.stream()
                .map(id -> skillRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Skill con id " + id + " no encontrada")))
                .collect(Collectors.toSet());

        candidate.setSkills(skills);
        candidatedRepository.save(candidate);

        return mapToDTO(candidate);
    }

    // Obtener todos los candidatos activos (Recruiter/ admin)
    public List<CandidateResponseDTO> getAllActiveCandidates() {
        return candidatedRepository.findAllByActiveTrue()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Obtener ofertas que coincidan con skills del candidato
    @Transactional(readOnly = true)
    public List<JobPostResponseDTO> getMatchingJobPosts(Candidated candidate) {
        Set<Skill> candidateSkills = candidate.getSkills();
        return jobPostRepository.findAll().stream()
                .filter(job -> !candidateSkills.isEmpty() &&
                        job.getSkills().stream().anyMatch(candidateSkills::contains))
                .map(job -> {
                    JobPostResponseDTO dto = new JobPostResponseDTO();
                    dto.setTitle(job.getTitle());
                    dto.setDescription(job.getDescription());
                    dto.setCompanyName(job.getCompanyName());
                    dto.setIsActive(job.getIsActive());
                    dto.setExpiresAt(job.getExpiresAt());
                    dto.setSkills(job.getSkills().stream().map(Skill::getName).collect(Collectors.toSet()));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // Mapper de entidad a DTO para la respuesta
    private CandidateResponseDTO mapToDTO(Candidated candidate) {
        CandidateResponseDTO dto = new CandidateResponseDTO();
        dto.setCandidateId(candidate.getId());
        dto.setUsername(candidate.getUser().getName());
        dto.setEmail(candidate.getUser().getEmail());
        dto.setTitle(candidate.getTitle());
        dto.setAddress(candidate.getAddress());
        dto.setCountry(candidate.getCountry());
        dto.setPhoneNumber(candidate.getPhoneNumber());
        dto.setActive(candidate.getActive());
        dto.setRole(candidate.getUser().getRole().name());
        dto.setCreatedAt(candidate.getUser().getCreatedAt().toString());

        Set<String> skills = candidate.getSkills().stream()
                .map(Skill::getName)
                .collect(Collectors.toSet());
        dto.setSkills(skills);

        return dto;
    }
}
