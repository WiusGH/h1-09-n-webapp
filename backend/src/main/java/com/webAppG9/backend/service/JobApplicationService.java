package com.webAppG9.backend.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.webAppG9.backend.Model.Candidated;
import com.webAppG9.backend.Model.JobApplication;
import com.webAppG9.backend.Model.JobPost;
import com.webAppG9.backend.Model.User;
import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.dto.jobapplication.JobApplicationResponseDTO;
import com.webAppG9.backend.exception.CandidateAlreadyAppliedException;
import com.webAppG9.backend.exception.CandidateNotFoundException;
import com.webAppG9.backend.exception.JobApplicationNotFoundException;
import com.webAppG9.backend.exception.JobPostInactiveException;
import com.webAppG9.backend.exception.JobPostNotFoundException;
import com.webAppG9.backend.exception.MaxCandidatesReachedException;
import com.webAppG9.backend.exception.ProfileAlreadyCompletedException;
import com.webAppG9.backend.repository.CandidatedRepository;
import com.webAppG9.backend.repository.JobApplicationRepository;
import com.webAppG9.backend.repository.JobPostRepository;
import com.webAppG9.backend.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;
    private final JobPostRepository jobPostRepository;
    private final CandidatedRepository candidatedRepository;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository,
            UserRepository userRepository,
            JobPostRepository jobPostRepository,
            CandidatedRepository candidatedRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.userRepository = userRepository;
        this.jobPostRepository = jobPostRepository;
        this.candidatedRepository = candidatedRepository;
    }

    // obtener usuario logueado desde JWT
    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(CandidateNotFoundException::new);
    }

    @Transactional
    public ResponseDTO<JobApplicationResponseDTO> applyToJob(Integer jobPostId) {
        User user = getCurrentUser();

        if (!user.getProfileCompleted()) {
            throw new ProfileAlreadyCompletedException("Debe completar su CV  para aplicar a esta oferta");
        }

        // Buscar candidato
        Candidated candidate = candidatedRepository.findByUser(user)
                .orElseThrow(CandidateNotFoundException::new);

        // Buscar JobPost
        JobPost jobPost = jobPostRepository.findById(jobPostId)
                .orElseThrow(JobPostNotFoundException::new);

        if (!Boolean.TRUE.equals(jobPost.getIsActive())) {
            throw new JobPostInactiveException();
        }

        if (!jobPost.canAcceptApplication()) {
            throw new MaxCandidatesReachedException();
        }

        // Validar si el candidato ya aplicó
        if (jobApplicationRepository.existsByUserAndJobPost(user, jobPost)) {
            throw new CandidateAlreadyAppliedException();
        }

        // Incrementar y persistir candidatesApplied
        jobPost.setCandidatesApplied(jobPost.getCandidatesApplied() + 1);
        jobPostRepository.save(jobPost);

        // Guardar la aplicación
        JobApplication saved = jobApplicationRepository.save(
                new JobApplication(user, jobPost, JobApplication.Status.PENDING, candidate, null));

        return new ResponseDTO<>(new JobApplicationResponseDTO(saved), null);
    }

    // Cancelar una aplicación
    public String cancelApplication(Integer applicationId) {
        JobApplication jobApplication = jobApplicationRepository.findById(applicationId)
                .orElseThrow(JobApplicationNotFoundException::new);

        // eliminar aplicacion al trabajo
        jobApplicationRepository.delete(jobApplication);
        return "Aplicación cancelada correctamente";
    }

    // Ver mis aplicaciones
    public ResponseDTO<List<JobApplicationResponseDTO>> getApplicationsByCurrentUser() {
        // verificar usuario logueado
        User user = getCurrentUser();
        // crer respuesta en formato de listaDTO
        List<JobApplicationResponseDTO> applications = jobApplicationRepository
                .findByUserId(user.getId())
                .stream()
                .map(JobApplicationResponseDTO::new)
                .toList();

        return new ResponseDTO<>(applications, null);
    }

}
