package com.webAppG9.backend.controller;

import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.dto.admin.AdminRequestDTO;
import com.webAppG9.backend.dto.admin.AdminResponseDTO;
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
    // Controller
    @PostMapping
    public ResponseEntity<ResponseDTO<AdminResponseDTO>> createAdmin(@RequestBody AdminRequestDTO adminDTO) {
        AdminResponseDTO admin = adminService.createAdmin(adminDTO);
        return ResponseEntity.ok(ResponseDTO.ok(admin));
    }

    // Listar solicitudes pendientes
    @GetMapping("/recruiters/pending")
    public ResponseEntity<ResponseDTO<List<RecruiterResponseDTO>>> getPendingRecruiters() {
        List<RecruiterResponseDTO> pending = adminService.getPendingRecruiters();
        return ResponseEntity.ok(ResponseDTO.ok(pending));
    }

    // Aprobar solicitud de recruiter
    @PatchMapping("/recruiters/approve/{userId}")
    public ResponseEntity<ResponseDTO<RecruiterResponseDTO>> approveRecruiter(@PathVariable Integer userId) {
        RecruiterResponseDTO recruiter = adminService.approveRecruiter(userId);
        return ResponseEntity.ok(ResponseDTO.ok(recruiter));
    }

    // Rechazar solicitud de recruiter
    @PatchMapping("/recruiters/reject/{userId}")
    public ResponseEntity<ResponseDTO<String>> rejectRecruiter(@PathVariable Integer userId) {
        adminService.rejectRecruiter(userId);
        return ResponseEntity.ok(new ResponseDTO<>("Solicitud de recruiter rechazada.", null));
    }

    // Listar todos los recruiters aprobados (para networking)
    @GetMapping("/recruiters/all")
    public ResponseEntity<ResponseDTO<List<RecruiterResponseDTO>>> getAllRecruiters() {
        List<RecruiterResponseDTO> recruiters = adminService.getAllApprovedRecruiters();
        return ResponseEntity.ok(new ResponseDTO<>(recruiters, null));
    }

}
