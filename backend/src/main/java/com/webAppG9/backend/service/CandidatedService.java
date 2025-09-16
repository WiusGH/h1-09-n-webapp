package com.webAppG9.backend.service;

import com.webAppG9.backend.Model.Candidated;

import com.webAppG9.backend.Model.Skill;
import com.webAppG9.backend.Model.User;
import com.webAppG9.backend.dto.ResponseDTO;
// import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.dto.candidates.CandidateResponseDTO;
import com.webAppG9.backend.dto.candidates.CandidatedRequestDTO;
import com.webAppG9.backend.dto.jobapplication.ApplicationStatusDTO;
import com.webAppG9.backend.dto.jobpost.JobPostResponseDTO;
import com.webAppG9.backend.exception.candidate.CandidateNotFoundException;
import com.webAppG9.backend.exception.candidate.ProfileAlreadyCompletedException;
import com.webAppG9.backend.repository.CandidatedRepository;
import com.webAppG9.backend.repository.JobApplicationRepository;
import com.webAppG9.backend.repository.JobPostRepository;
import com.webAppG9.backend.repository.SkillRepository;
import com.webAppG9.backend.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        private final UserRepository userRepository;
        private final JobApplicationRepository jobApplicationRepository;

        public CandidatedService(CandidatedRepository candidatedRepository,
                        JobPostRepository jobPostRepository,
                        SkillRepository skillRepository,
                        UserRepository userRepository,
                        JobApplicationRepository jobApplicationRepository) {
                this.candidatedRepository = candidatedRepository;
                this.jobPostRepository = jobPostRepository;
                this.skillRepository = skillRepository;
                this.userRepository = userRepository;
                this.jobApplicationRepository = jobApplicationRepository;
        }

        // obtener usuario logueado desde JWT
        private User getCurrentUser() {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                String email = auth.getName();

                return userRepository.findByEmail(email)
                                .orElseThrow(CandidateNotFoundException::new);
        }

        // Crear candidato asociado a un usuario desde el registro
        public Candidated createCandidateForRegister(User user) {
                try {
                        Candidated candidate = new Candidated();
                        candidate.setUser(user);
                        candidate.setActive(true);
                        return candidatedRepository.save(candidate);
                } catch (Exception e) {
                        throw new RuntimeException("Error creando perfil de candidato", e);
                }
        }

        // Completar CV asociado al usuario autenticado
        public CandidateResponseDTO createCandidateForUser(CandidatedRequestDTO candidateRequest) {
                User user = getCurrentUser();

                if (user.getProfileCompleted()) {
                        throw new ProfileAlreadyCompletedException();
                }

                Candidated candidate = candidatedRepository.findByUser(user)
                                .orElseThrow(CandidateNotFoundException::new);

                // Aplicar todos los campos del request de forma centralizada
                candidate.applyFromDTO(candidateRequest);

                // Mapear skills si vienen
                // Mapear skills si vienen como nombres
                if (candidateRequest.getSkills() != null && !candidateRequest.getSkills().isEmpty()) {
                        Set<Skill> skills = candidateRequest.getSkills().stream()
                                        .map(name -> skillRepository.findByName(name) // buscar por nombre
                                                        .orElseThrow(() -> new RuntimeException(
                                                                        "Skill con nombre '" + name
                                                                                        + "' no encontrada")))
                                        .collect(Collectors.toSet());
                        candidate.setSkills(skills);
                }

                // Guardar candidato
                Candidated saved = candidatedRepository.save(candidate);

                // Cambiar estado del usuario
                user.setProfileCompleted(true);
                userRepository.save(user);

                // Retornar DTO
                return new CandidateResponseDTO(saved);
        }

        // Obtener candidato por userId
        public ResponseDTO<CandidateResponseDTO> getCandidateById(Integer id) {
                Candidated candidate = candidatedRepository.findById(id)
                                .orElseThrow(CandidateNotFoundException::new);
                return ResponseDTO.ok(new CandidateResponseDTO(candidate));
        }

        // Actualizar candidato
        public String updateCandidate(CandidatedRequestDTO request) {
                User user = getCurrentUser();

                Candidated candidate = candidatedRepository.findByUser(user)
                                .orElseThrow(CandidateNotFoundException::new);
                // Aplicar todos los campos del DTO a la entidad
                candidate.applyFromDTO(request);
                candidatedRepository.save(candidate);

                return "Candidato actualizado correctamente";
        }

        // Obtener ofertas que coincidan con skills del candidato
        @Transactional(readOnly = true)
        public List<JobPostResponseDTO> getMatchingJobPosts() {
                User user = getCurrentUser();
                Candidated candidate = candidatedRepository.findByUser(user)
                                .orElseThrow(CandidateNotFoundException::new);
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
        public String updateSkillsByIds(Set<Integer> skillIds) {
                User user = getCurrentUser();
                Candidated candidate = candidatedRepository.findByUser(user)
                                .orElseThrow(CandidateNotFoundException::new);

                Set<Skill> skills = skillIds.stream()
                                .map(id -> skillRepository.findById(id)
                                                .orElseThrow(() -> new RuntimeException(
                                                                "Skill con id " + id + " no encontrada")))
                                .collect(Collectors.toSet());

                candidate.setSkills(skills);
                candidatedRepository.save(candidate);

                return "Skills actualizadas correctamente";
        }

        // ctualizar estado de candidato
        public String updateCandidateStatus() {
                User user = getCurrentUser();
                Candidated candidate = candidatedRepository.findByUser(user)
                                .orElseThrow(CandidateNotFoundException::new);

                candidate.setActive(!candidate.getActive());
                candidatedRepository.save(candidate);

                String message = candidate.getActive() ? "Candidato activo" : "Candidato pausado";
                return message;
        }

        // Encontrar los status de lsa applications y su msg.
        public List<ApplicationStatusDTO> getStatusMessagesForCandidate() {
                User user = getCurrentUser(); // obtener usuario logueado

                return jobApplicationRepository
                                .findByUserId(user.getId())
                                .stream()
                                .map(app -> new ApplicationStatusDTO(app.getStatus().name(), app.getStatusMessage()))
                                .toList();
        }

}
