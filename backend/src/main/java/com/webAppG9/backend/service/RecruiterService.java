package com.webAppG9.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webAppG9.backend.Model.JobApplication;
import com.webAppG9.backend.Model.Recruiter;
import com.webAppG9.backend.Model.User;
import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.dto.candidates.CandidateResponseDTO;
import com.webAppG9.backend.dto.jobapplication.JobApplicationResponseDTO;
import com.webAppG9.backend.dto.recruiter.RecruiterResponseDTO;
import com.webAppG9.backend.exception.JobApplicationNotFoundException;
import com.webAppG9.backend.exception.RecruiterNotFoundException;
import com.webAppG9.backend.exception.UserNotFoundException;
import com.webAppG9.backend.repository.CandidatedRepository;
import com.webAppG9.backend.repository.JobApplicationRepository;
import com.webAppG9.backend.repository.RecruiterRepository;
import com.webAppG9.backend.repository.UserRepository;

@Service
public class RecruiterService {

    private final RecruiterRepository recruiterRepository;
    private final UserRepository userRepository;
    private final JobApplicationRepository jobApplicationRepository;
    private final CandidatedRepository candidatedRepository;

    public RecruiterService(RecruiterRepository recruiterRepository,
            UserRepository userRepository,
            JobApplicationRepository jobApplicationRepository,
            CandidatedRepository candidatedRepository) {
        this.recruiterRepository = recruiterRepository;
        this.userRepository = userRepository;
        this.jobApplicationRepository = jobApplicationRepository;
        this.candidatedRepository = candidatedRepository;
    }

    // Solicitud de upgrade a Recruiter
    @Transactional
    public void requestRecruiterUpgrade(Integer userId, RecruiterResponseDTO request) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        if (recruiterRepository.findByUserId(userId).isPresent()) {
            throw new RuntimeException("Ya existe una solicitud de recruiter para este usuario");
        }

        Recruiter recruiter = new Recruiter();
        recruiter.setUser(user);
        recruiter.setCompanyName(request.getCompanyName());
        recruiter.setWebsite(request.getWebsite());
        recruiter.setDescription(request.getDescription());
        recruiter.setApproved(false);

        recruiterRepository.save(recruiter);
    }

    // Obtener perfil de recruiter por userId
    @Transactional(readOnly = true)
    public RecruiterResponseDTO getRecruiterByUserId(Integer userId) {
        Recruiter recruiter = recruiterRepository.findByUserId(userId)
                .orElseThrow(RecruiterNotFoundException::new);

        return recruiter.toResponseDTO();
    }

    // Actualizar perfil de recruiter
    @Transactional
    public RecruiterResponseDTO updateRecruiterProfile(Integer userId, RecruiterResponseDTO request) {
        Recruiter recruiter = recruiterRepository.findByUserId(userId)
                .orElseThrow(RecruiterNotFoundException::new);

        recruiter.setCompanyName(request.getCompanyName());
        recruiter.setWebsite(request.getWebsite());
        recruiter.setDescription(request.getDescription());

        Recruiter updated = recruiterRepository.save(recruiter);

        return updated.toResponseDTO();
    }

    // Listar todos los recruiters aprobados
    @Transactional(readOnly = true)
    public List<RecruiterResponseDTO> getAllApprovedRecruiters() {
        return recruiterRepository.findAll()
                .stream()
                .filter(r -> Boolean.TRUE.equals(r.getApproved()))
                .map(Recruiter::toResponseDTO)
                .collect(Collectors.toList());
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

    // Cambiar estado de la aplicación ( PENDING, REVIEWED, ACCEPTED, REJECTED)
    public void updateApplicationStatus(Integer applicationId, String status, String message) {
        JobApplication jobApplication = jobApplicationRepository.findById(applicationId)
                .orElseThrow(JobApplicationNotFoundException::new);

        try {
            JobApplication.Status newStatus = JobApplication.Status.valueOf(status.toUpperCase());
            jobApplication.setStatus(newStatus);
            jobApplication.setStatusMessage(message); // guarda el mensaje
            jobApplicationRepository.save(jobApplication); // persistir cambios
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
