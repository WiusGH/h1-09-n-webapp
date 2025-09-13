package com.webAppG9.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webAppG9.backend.Model.Admin;
import com.webAppG9.backend.Model.Recruiter;
import com.webAppG9.backend.Model.User;
import com.webAppG9.backend.dto.admin.AdminDTO;
import com.webAppG9.backend.dto.recruiter.RecruiterResponseDTO;
import com.webAppG9.backend.exception.CandidateNotFoundException;
import com.webAppG9.backend.exception.RecruiterSolicitedNotFoundException;
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

    // metodo para crear usuario admin con sus atributos
    public Admin createAdmin(AdminDTO adminDTO) {
        // Verificar que el usuario exista
        User user = userRepository.findById(adminDTO.getUserId())
                .orElseThrow(CandidateNotFoundException::new);

        // Verificar que no sea ya admin
        if (adminRepository.existsById(adminDTO.getUserId())) {
            throw new RuntimeException("Este usuario ya es admin");
        }

        // Crear Admin
        Admin admin = new Admin();
        admin.setUser(user); // asocia la entidad User

        // actualizar rol en User
        user.setRole(User.Role.ADMIN);
        userRepository.save(user);

        // Guardar el usuario admon
        return adminRepository.save(admin);
    }

    // Listar solicitudes pendientes
    @Transactional(readOnly = true)
    public List<RecruiterResponseDTO> getPendingRecruiters() {
        return recruiterRepository.findAll()
                .stream()
                .filter(r -> r.getApproved() != null && !r.getApproved())
                .map(Recruiter::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Aprobar recruiter
    @Transactional
    public void approveRecruiter(Integer userId) {
        Recruiter recruiter = recruiterRepository.findByUserId(userId)
                .orElseThrow(RecruiterSolicitedNotFoundException::new);

        recruiter.setApproved(true);
        recruiter.getUser().setRole(User.Role.RECRUITER);

        recruiterRepository.save(recruiter);
        userRepository.save(recruiter.getUser());
    }

    // Rechazar recruiter
    @Transactional
    public void rejectRecruiter(Integer userId) {
        Recruiter recruiter = recruiterRepository.findByUserId(userId)
                .orElseThrow(RecruiterSolicitedNotFoundException::new);

        recruiterRepository.delete(recruiter);
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

}
