package com.webAppG9.backend.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webAppG9.backend.Model.JobApplication;
import com.webAppG9.backend.Model.JobPost;
import com.webAppG9.backend.Model.Recruiter;
import com.webAppG9.backend.Model.Skill;
import com.webAppG9.backend.Model.User;
import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.dto.candidates.CandidateResponseDTO;
import com.webAppG9.backend.dto.jobapplication.JobApplicationResponseDTO;
import com.webAppG9.backend.dto.jobpost.JobPostRequestDTO;
import com.webAppG9.backend.dto.jobpost.JobPostResponseDTO;
import com.webAppG9.backend.dto.recruiter.RecruiterRequestDTO;
import com.webAppG9.backend.dto.recruiter.RecruiterResponseDTO;
import com.webAppG9.backend.exception.candidate.ProfileAlreadyCompletedException;
import com.webAppG9.backend.exception.jobapplication.JobApplicationNotFoundException;
import com.webAppG9.backend.exception.jobpost.JobPostNotFoundException;
import com.webAppG9.backend.exception.recruiter.RecruiterNotFoundException;
import com.webAppG9.backend.exception.recruiter.RecruiterSulicitudExistingException;
import com.webAppG9.backend.exception.user.UserNotFoundException;
import com.webAppG9.backend.repository.CandidatedRepository;
import com.webAppG9.backend.repository.JobApplicationRepository;
import com.webAppG9.backend.repository.JobPostRepository;
import com.webAppG9.backend.repository.RecruiterRepository;
import com.webAppG9.backend.repository.SkillRepository;
import com.webAppG9.backend.repository.UserRepository;

@Service
public class RecruiterService {

    private final RecruiterRepository recruiterRepository;
    private final UserRepository userRepository;
    private final JobApplicationRepository jobApplicationRepository;
    private final CandidatedRepository candidatedRepository;
    private final JobPostRepository jobPostRepository;
    private final SkillRepository skillRepository;

    public RecruiterService(RecruiterRepository recruiterRepository,
            UserRepository userRepository,
            JobApplicationRepository jobApplicationRepository,
            CandidatedRepository candidatedRepository,
            JobPostRepository jobPostRepository,
            SkillRepository skillRepository) {
        this.recruiterRepository = recruiterRepository;
        this.userRepository = userRepository;
        this.jobApplicationRepository = jobApplicationRepository;
        this.candidatedRepository = candidatedRepository;
        this.jobPostRepository = jobPostRepository;
        this.skillRepository = skillRepository;
    }

    // Solicitud a Recruiter
    @Transactional
    public void requestRecruiterUpgrade(Integer userId, RecruiterRequestDTO request) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        if (recruiterRepository.findByUserId(userId).isPresent()) {
            throw new RecruiterSulicitudExistingException();
        }

        if (!user.getProfileCompleted()) {
            throw new ProfileAlreadyCompletedException("Debe completar su CV  para solicitar ser Recruiter");
        }

        Recruiter recruiter = new Recruiter();
        recruiter.setUser(user);
        recruiter.setCompanyName(request.getCompanyName());
        recruiter.setCompanyName(request.getCompanyName());
        recruiter.setCompanyCountry(request.getCompanyCountry());
        recruiter.setCompanyAddress(request.getCompanyAddress());
        recruiter.setCompanyEmail(request.getCompanyEmail());
        recruiter.setApproved(false);

        recruiterRepository.save(recruiter);
    }

    // Crear un post de trabajo
    public JobPostResponseDTO createJob(JobPostRequestDTO request, Integer userId) {
        // Mapear skills
        Set<Skill> skillSet = new HashSet<>();
        if (request.getSkills() != null && !request.getSkills().isEmpty()) {
            skillSet = request.getSkills().stream()
                    .map(name -> skillRepository.findByName(name)
                            .orElseThrow(() -> new RuntimeException(
                                    "Skill con nombre '" + name + "' no encontrada")))
                    .collect(Collectors.toSet());
        }

        // Obtener recruiter
        Recruiter recruiter = recruiterRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Recruiter no encontrado"));

        // Crear job post y asignar datos
        JobPost jobPost = new JobPost();
        jobPost.applyFromDTO(request, skillSet, recruiter);
        jobPost.setIsActive(true);

        // Guardar
        JobPost savedJob = jobPostRepository.save(jobPost);

        return new JobPostResponseDTO(savedJob);
    }

    // Obtener perfil de recruiter por userId
    @Transactional(readOnly = true)
    public RecruiterResponseDTO getRecruiterByUserId(Integer userId) {
        Recruiter recruiter = recruiterRepository.findByUserId(userId)
                .orElseThrow(RecruiterNotFoundException::new);

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        RecruiterResponseDTO dto = new RecruiterResponseDTO();
        dto.setUserId(user.getId());
        dto.setCompanyName(recruiter.getCompanyName());
        dto.setCompanyName(recruiter.getCompanyName());
        dto.setCompanyCountry(recruiter.getCompanyCountry());
        dto.setCompanyAddress(recruiter.getCompanyAddress());
        dto.setCompanyEmail(recruiter.getCompanyEmail());
        dto.setApproved(recruiter.getApproved());
        return dto;
    }

    // Actualizar perfil de recruiter
    @Transactional
    public RecruiterResponseDTO updateRecruiterProfile(Integer userId, RecruiterRequestDTO request) {
        Recruiter recruiter = recruiterRepository.findByUserId(userId)
                .orElseThrow(RecruiterNotFoundException::new);

        if (request.getCompanyName() != null) {
            recruiter.setCompanyName(request.getCompanyName());
        }

        if (request.getCompanyCountry() != null) {
            recruiter.setCompanyCountry(recruiter.getCompanyCountry());
        }

        if (request.getCompanyAddress() != null) {
            recruiter.setCompanyAddress(recruiter.getCompanyAddress());
        }

        if (request.getCompanyEmail() != null) {
            recruiter.setCompanyEmail(recruiter.getCompanyEmail());
        }

        Recruiter updated = recruiterRepository.save(recruiter);

        return RecruiterResponseDTO.toResponseDTO(updated);
    }

    // Ver postulacion de un trabajo
    public ResponseDTO<List<JobApplicationResponseDTO>> getApplicationsByJob(Integer jobPostId) {
        //
        List<JobApplicationResponseDTO> applications = jobApplicationRepository
                .findByJobPostId(jobPostId)
                .stream()
                .map(JobApplicationResponseDTO::new)
                .toList();

        return new ResponseDTO<>(applications, null);
    }

    // Ver postulacion de un trabajo
    // public ResponseDTO<List<JobApplicationResponseDTO>> getAllJobpost() {
    // //
    // List<JobApplicationResponseDTO> applications = jobApplicationRepository
    // .findByJobPostId()
    // .stream()
    // .map(JobApplicationResponseDTO::new)
    // .toList();

    // return new ResponseDTO<>(applications, null);
    // }

    // Cambiar estado de la aplicación ( PENDING, REVIEWED, ACCEPTED, REJECTED)
    public void updateApplicationStatus(Integer applicationId, String status, String message) {
        JobApplication jobApplication = jobApplicationRepository.findById(applicationId)
                .orElseThrow(JobApplicationNotFoundException::new);

        try {
            JobApplication.Status newStatus = JobApplication.Status.valueOf(status.toUpperCase());
            jobApplication.setStatus(newStatus);
            jobApplication.setStatusMessage(message); // guarda el mensaje
            jobApplicationRepository.save(jobApplication); // persistir cambios

            JobPost jobPost = jobPostRepository.findById(jobApplication.getId())
                    .orElseThrow(JobPostNotFoundException::new);
            jobPost.setIsActive(false);
            jobPostRepository.save(jobPost);

        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Estado inválido");
        }
    }

    // Obtener todos los candidatos activos (Recruiter/ admin)
    public List<CandidateResponseDTO> getAllActiveCandidates() {
        return candidatedRepository.findAllByActiveTrue()
                .stream()
                .map(CandidateResponseDTO::new)
                .toList();
    }
}
