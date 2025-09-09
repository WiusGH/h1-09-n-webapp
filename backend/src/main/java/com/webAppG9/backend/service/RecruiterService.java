package com.webAppG9.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.webAppG9.backend.dto.RecruiterResponseDTO;
import com.webAppG9.backend.Model.Recruiter;
import com.webAppG9.backend.Model.User;
import com.webAppG9.backend.repository.RecruiterRepository;
import com.webAppG9.backend.repository.UserRepository;

@Service
public class RecruiterService {

    private final RecruiterRepository recruiterRepository;
    private final UserRepository userRepository;

    public RecruiterService(RecruiterRepository recruiterRepository, UserRepository userRepository) {
        this.recruiterRepository = recruiterRepository;
        this.userRepository = userRepository;
    }

    // Solicitud de upgrade a Recruiter
    @Transactional
    public void requestRecruiterUpgrade(Integer userId, RecruiterResponseDTO request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

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

    // Listar solicitudes pendientes
    @Transactional(readOnly = true)
    public List<RecruiterResponseDTO> getPendingRecruiters() {
        return recruiterRepository.findAll()
                .stream()
                .filter(r -> r.getApproved() != null && !r.getApproved())
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // Aprobar recruiter
    @Transactional
    public void approveRecruiter(Integer userId) {
        Recruiter recruiter = recruiterRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("No existe solicitud de recruiter"));

        recruiter.setApproved(true);
        recruiter.getUser().setRole(User.Role.RECRUITER);

        recruiterRepository.save(recruiter);
        userRepository.save(recruiter.getUser());
    }

    // Rechazar recruiter
    @Transactional
    public void rejectRecruiter(Integer userId) {
        Recruiter recruiter = recruiterRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("No existe solicitud de recruiter"));

        recruiterRepository.delete(recruiter);
    }

    // Mapear para response DTO
    private RecruiterResponseDTO mapToResponseDTO(Recruiter recruiter) {
        RecruiterResponseDTO dto = new RecruiterResponseDTO();
        dto.setUserId(recruiter.getUser().getId());
        dto.setUsername(recruiter.getUser().getName());
        dto.setUserEmail(recruiter.getUser().getEmail());
        dto.setCompanyName(recruiter.getCompanyName());
        dto.setWebsite(recruiter.getWebsite());
        dto.setDescription(recruiter.getDescription());
        dto.setApproved(recruiter.getApproved());
        return dto;
    }
}
