package com.webAppG9.backend.controller;

import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.dto.admin.AdminDTO;
import com.webAppG9.backend.dto.recruiter.RecruiterResponseDTO;
import com.webAppG9.backend.service.AdminService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Crear un admin para un usuario existente
    @PostMapping
    public ResponseEntity<ResponseDTO<Object>> createAdmin(@RequestBody AdminDTO adminDTO) {
        return ResponseEntity.ok(new ResponseDTO<>(AdminDTO.fromEntity(adminService.createAdmin(adminDTO)), null));
    }

    // Aprobar solicitud de recruiter
    @PatchMapping("/recruiters/approve/{userId}")
    public ResponseEntity<ResponseDTO<String>> approveRecruiter(@PathVariable Integer userId) {
        adminService.approveRecruiter(userId);
        return ResponseEntity.ok(new ResponseDTO<>("Usuario aprobado como Recruiter.", null));
    }

    // Rechazar solicitud de recruiter
    @PatchMapping("/recruiters/reject/{userId}")
    public ResponseEntity<ResponseDTO<String>> rejectRecruiter(@PathVariable Integer userId) {
        adminService.rejectRecruiter(userId);
        return ResponseEntity.ok(new ResponseDTO<>("Solicitud de recruiter rechazada.", null));
    }

    // Listar solicitudes pendientes
    @GetMapping("/recruiters/pending")
    public ResponseEntity<ResponseDTO<List<RecruiterResponseDTO>>> getPendingRecruiters() {
        List<RecruiterResponseDTO> pending = adminService.getPendingRecruiters();
        return ResponseEntity.ok(new ResponseDTO<>(pending, null));
    }

    // Listar todos los recruiters aprobados (para networking)
    @GetMapping("/all")
    public ResponseEntity<ResponseDTO<List<RecruiterResponseDTO>>> getAllRecruiters() {
        List<RecruiterResponseDTO> recruiters = adminService.getAllApprovedRecruiters();
        return ResponseEntity.ok(new ResponseDTO<>(recruiters, null));
    }

}
