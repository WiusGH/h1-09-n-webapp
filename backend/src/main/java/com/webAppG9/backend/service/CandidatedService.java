package com.webAppG9.backend.service;

import com.webAppG9.backend.Model.Candidated;
import com.webAppG9.backend.Model.Skill;
import com.webAppG9.backend.Model.User;
import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.dto.candidates.CandidateResponseDTO;
import com.webAppG9.backend.dto.jobpost.JobPostResponseDTO;
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
    public ResponseDTO<CandidateResponseDTO> getCandidateById(Integer id) {
        Candidated candidate = candidatedRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado"));
        return ResponseDTO.ok(new CandidateResponseDTO(candidate));
    }

    // Actualizar candidato
    public String updateCandidate(Integer id, CandidateResponseDTO request) {
        Candidated candidate = candidatedRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado"));
        // Aplicar todos los campos del DTO a la entidad
        candidate.applyFromDTO(request);
        candidatedRepository.save(candidate);

        return "Candidato actualizado correctamente";
    }

    // Obtener todos los candidatos activos (Recruiter/ admin)
    public List<CandidateResponseDTO> getAllActiveCandidates() {
        return candidatedRepository.findAllByActiveTrue()
                .stream()
                .map(CandidateResponseDTO::new)
                .toList();
    }

    // Obtener ofertas que coincidan con skills del candidato
    @Transactional(readOnly = true)
    public List<JobPostResponseDTO> getMatchingJobPosts(Candidated candidate) {
        // Filtrar skills y nostrar la lista de JobPosts asociados
        Set<Skill> candidateSkills = candidate.getSkills();
        return jobPostRepository.findAll().stream()
                .filter(job -> !candidateSkills.isEmpty() &&
                        job.getSkills().stream().anyMatch(candidateSkills::contains))
                .map(JobPostResponseDTO::new)
                .collect(Collectors.toList());
    }

    // Actualizar skills de un candidato
    @Transactional
    public String updateSkillsByIds(Integer candidateId, Set<Integer> skillIds) {
        Candidated candidate = candidatedRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado"));

        Set<Skill> skills = skillIds.stream()
                .map(id -> skillRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Skill con id " + id + " no encontrada")))
                .collect(Collectors.toSet());

        candidate.setSkills(skills);
        candidatedRepository.save(candidate);

        return "Skills actualizadas correctamente";
    }

    // ctualizar estado de candidato
    public String updateCandidateStatus(Integer id) {
        Candidated candidated = candidatedRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado"));

        candidated.setActive(!candidated.getActive());
        candidatedRepository.save(candidated);

        String message = candidated.getActive() ? "Candidato activo" : "Candidato pausado";
        return message;
    }

}
