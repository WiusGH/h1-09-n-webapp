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
import com.webAppG9.backend.repository.CandidatedRepository;
import com.webAppG9.backend.repository.JobApplicationRepository;
import com.webAppG9.backend.repository.JobPostRepository;
import com.webAppG9.backend.repository.UserRepository;

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
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // Método para aplicar a una oferta laboral
    public ResponseDTO<JobApplicationResponseDTO> applyToJob(Integer jobPostId) {
        // Buscar usuario
        User user = getCurrentUser();

        // Buscar candidato asociado
        Candidated candidate = candidatedRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado"));

        // Buscar oferta laboral
        JobPost jobPost = jobPostRepository.findById(jobPostId)
                .orElseThrow(() -> new RuntimeException("JobPost no encontrado"));

        if (!Boolean.TRUE.equals(jobPost.getIsActive())) {
            throw new RuntimeException("La oferta laboral no está activa");
        }
        // Crear y guardar JobPost
        JobApplication jobApplication = new JobApplication(user, jobPost, JobApplication.Status.PENDING, candidate,
                null);

        JobApplication saved = jobApplicationRepository.save(jobApplication);
        JobApplicationResponseDTO responseDTO = new JobApplicationResponseDTO(saved);

        return new ResponseDTO<>(responseDTO, null);
    }

    // Cancelar una aplicación
    public String cancelApplication(Integer applicationId) {
        JobApplication jobApplication = jobApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Aplicación no encontrada"));
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

    // Ver postulacion de un trabajo (recruiter/admin)
    public ResponseDTO<List<JobApplicationResponseDTO>> getApplicationsByJob(Integer jobPostId) {
        //
        List<JobApplicationResponseDTO> applications = jobApplicationRepository
                .findByJobPostId(jobPostId)
                .stream()
                .map(JobApplicationResponseDTO::new)
                .toList();

        return new ResponseDTO<>(applications, null);
    }

    // Cambiar estado de la aplicación (recruiter/admin)
    public void updateApplicationStatus(Integer applicationId, String status) {
        JobApplication jobApplication = jobApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Aplicación no encontrada"));

        try {
            JobApplication.Status newStatus = JobApplication.Status.valueOf(status.toUpperCase());
            jobApplication.setStatus(newStatus);
            jobApplicationRepository.save(jobApplication);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Estado inválido");
        }
    }

}
