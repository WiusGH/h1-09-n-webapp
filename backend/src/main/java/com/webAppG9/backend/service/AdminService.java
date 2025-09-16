package com.webAppG9.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webAppG9.backend.Model.Admin;
import com.webAppG9.backend.Model.Recruiter;
import com.webAppG9.backend.Model.User;
import com.webAppG9.backend.dto.admin.AdminRequestDTO;
import com.webAppG9.backend.dto.admin.AdminResponseDTO;
import com.webAppG9.backend.dto.recruiter.RecruiterResponseDTO;
import com.webAppG9.backend.exception.candidate.CandidateNotFoundException;
import com.webAppG9.backend.exception.recruiter.RecruiterSolicitedNotFoundException;
import com.webAppG9.backend.exception.user.UserAlreadyAdminException;
import com.webAppG9.backend.exception.user.UserNotFoundException;
import com.webAppG9.backend.repository.AdminRepository;
import com.webAppG9.backend.repository.RecruiterRepository;
import com.webAppG9.backend.repository.UserRepository;

// AdminService.java
@Service
public class AdminService {
    // Atruibutos d la clase
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final RecruiterRepository recruiterRepository;

    // Constructor
    public AdminService(AdminRepository adminRepository,
            UserRepository userRepository,
            RecruiterRepository recruiterRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
        this.recruiterRepository = recruiterRepository;
    }

    // Aprobar solicitud a Admin
    public AdminResponseDTO createAdmin(AdminRequestDTO adminDTO) {
        // Verificar que el usuario exista
        User user = userRepository.findById(adminDTO.getUserId())
                .orElseThrow(UserNotFoundException::new);

        // Verificar que no sea ya admin (mejor por user_id, no por id de admin)
        if (adminRepository.findByUserId(user.getId()).isPresent()) {
            throw new UserAlreadyAdminException();
        }

        // Crear Admin y asociar al User
        Admin admin = new Admin();
        admin.setUser(user);

        // Actualizar rol en User
        user.setRole(User.Role.ADMIN);
        userRepository.save(user);

        // Guardar Admin
        Admin savedAdmin = adminRepository.save(admin);

        // Convertir a DTO
        return new AdminResponseDTO(
                savedAdmin.getId(),
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().name());
    }

    // Listar solicitudes pendientes
    @Transactional(readOnly = true)
    public List<RecruiterResponseDTO> getPendingRecruiters() {
        return recruiterRepository.findAll()
                .stream()
                .filter(r -> r.getApproved() != null && !r.getApproved())
                .map(RecruiterResponseDTO::toResponseDTO)
                .collect(Collectors.toList());
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(CandidateNotFoundException::new);
    }

    // Aprobar recruiter
    @Transactional
    public RecruiterResponseDTO approveRecruiter() {

        User user = getCurrentUser();

        // Buscar recruiter por userId
        Recruiter recruiter = recruiterRepository.findByUserId(user.getId())
                .orElseThrow(RecruiterSolicitedNotFoundException::new);

        // Marcar como aprobado
        recruiter.setApproved(true);

        // Cambiar rol del usuario
        user.setRole(User.Role.RECRUITER);

        // Guardar cambios
        recruiterRepository.save(recruiter);
        userRepository.save(user);

        // Mapear a DTO
        RecruiterResponseDTO dto = new RecruiterResponseDTO();
        dto.setUserId(user.getId());
        dto.setCompanyName(recruiter.getCompanyName());
        dto.setCompanyCountry(recruiter.getCompanyCountry());
        dto.setCompanyAddress(recruiter.getCompanyAddress());
        dto.setCompanyEmail(recruiter.getCompanyEmail());
        dto.setApproved(recruiter.getApproved());

        return dto;
    }

    // Rechazar recruiter
    @Transactional
    public void rejectRecruiter() {

        User user = getCurrentUser();

        // Buscar recruiter por userId
        Recruiter recruiter = recruiterRepository.findByUserId(user.getId())
                .orElseThrow(RecruiterSolicitedNotFoundException::new);

        recruiterRepository.delete(recruiter);
    }

    // Listar todos los recruiters aprobados
    @Transactional(readOnly = true)
    public List<RecruiterResponseDTO> getAllApprovedRecruiters() {
        return recruiterRepository.findAll()
                .stream()
                .filter(r -> Boolean.TRUE.equals(r.getApproved()))
                .map(RecruiterResponseDTO::toResponseDTO)
                .collect(Collectors.toList());
    }

}
