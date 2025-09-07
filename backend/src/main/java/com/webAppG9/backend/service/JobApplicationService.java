package com.webAppG9.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.webAppG9.backend.Model.JobApplication;
import com.webAppG9.backend.Model.JobPost;
import com.webAppG9.backend.Model.User;
import com.webAppG9.backend.dto.JobApplicationResponseDTO;
import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.repository.JobApplicationRepository;
import com.webAppG9.backend.repository.JobPostRepository;
import com.webAppG9.backend.repository.UserRepository;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;
    private final JobPostRepository jobPostRepository;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository,
            UserRepository userRepository,
            JobPostRepository jobPostRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.userRepository = userRepository;
        this.jobPostRepository = jobPostRepository;
    }

    // Método para transformar la entidad en DTO
    private JobApplicationResponseDTO toResponseDTO(JobApplication jobApplication) {
        // crear respuesta combinada DTO de la union de las entidades
        JobApplicationResponseDTO dto = new JobApplicationResponseDTO();
        User user = jobApplication.getUser();
        JobPost jobPost = jobApplication.getJobPost();

        dto.setApplicationId(jobApplication.getId());
        dto.setUsername(user.getName() + " " + user.getLastName());
        dto.setUserEmail(user.getEmail());
        dto.setJobTitle(jobPost.getTitle());
        dto.setJobDescription(jobPost.getDescription());
        dto.setStatus(jobApplication.getStatus().name());
        dto.setAppliedAt(jobApplication.getAppliedAt());

        return dto;
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
        // Obtener usuario logueado desde JWT
        User user = getCurrentUser();

        // Buscar oferta laboral
        JobPost jobPost = jobPostRepository.findById(jobPostId)
                .orElseThrow(() -> new RuntimeException("Oferta laboral no encontrada"));

        if (!Boolean.TRUE.equals(jobPost.getIsActive())) {
            throw new RuntimeException("La oferta laboral no está activa");
        }

        // Crear y guardar JobPost
        JobApplication jobApplication = new JobApplication();
        jobApplication.setUser(user);
        jobApplication.setJobPost(jobPost);
        jobApplication.setStatus(JobApplication.Status.PENDING);
        jobApplication.setAppliedAt(LocalDateTime.now());

        JobApplication saved = jobApplicationRepository.save(jobApplication);
        JobApplicationResponseDTO responseDTO = toResponseDTO(saved);

        return new ResponseDTO<>(responseDTO, null);
    }

    // eliminar una aplicación
    public void cancelApplication(Integer applicationId) {
        JobApplication jobApplication = jobApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Aplicación no encontrada"));
        // Validar usuario
        User currentUser = getCurrentUser();
        if (!jobApplication.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("No puedes cancelar la aplicación de otro usuario");
        }
        // eliminar aplicacion al trabajo
        jobApplicationRepository.delete(jobApplication);
    }

    // Ver mis aplicaciones
    public ResponseDTO<List<JobApplicationResponseDTO>> getApplicationsByCurrentUser() {
        // verificar usuario logueado
        User user = getCurrentUser();
        // crer respuesta en formato de listaDTO
        List<JobApplicationResponseDTO> applications = jobApplicationRepository
                .findByUserId(user.getId())
                .stream()
                .map(this::toResponseDTO)
                .toList();

        return new ResponseDTO<>(applications, null);
    }

    // Ver postulaciones de un trabajo (recruiter/admin)
    public ResponseDTO<List<JobApplicationResponseDTO>> getApplicationsByJob(Integer jobPostId) {
        //
        List<JobApplicationResponseDTO> applications = jobApplicationRepository
                .findByJobPostId(jobPostId)
                .stream()
                .map(this::toResponseDTO)
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
