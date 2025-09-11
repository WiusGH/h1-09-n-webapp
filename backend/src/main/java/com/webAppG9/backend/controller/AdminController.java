package com.webAppG9.backend.controller;

import com.webAppG9.backend.Model.Admin;
import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.dto.admin.AdminDTO;
import com.webAppG9.backend.dto.recruiter.RecruiterResponseDTO;
import com.webAppG9.backend.service.AdminService;
import com.webAppG9.backend.service.RecruiterService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
    // ATRIBUTOS
    private final AdminService adminService;
    private final RecruiterService recruiterService;

    // CONSTRUCTOR
    public AdminController(AdminService adminService, RecruiterService recruiterService) {
        this.adminService = adminService;
        this.recruiterService = recruiterService;
    }

    // Crear un admin para un usuario existente
    @PostMapping
    public ResponseEntity<ResponseDTO<Object>> createAdmin(@RequestBody AdminDTO adminDTO) {
        Admin admin = adminService.createAdmin(adminDTO);
        return ResponseEntity.ok(new ResponseDTO<>(AdminDTO.fromEntity(admin), null));
    }

    // Aprobar solicitud de recruiter
    @PatchMapping("/recruiters/approve/{userId}")
    public ResponseEntity<ResponseDTO<String>> approveRecruiter(@PathVariable Integer userId) {
        recruiterService.approveRecruiter(userId);
        return ResponseEntity.ok(new ResponseDTO<>("Usuario aprobado como Recruiter.", null));
    }

    // Rechazar solicitud de recruiter
    @PatchMapping("/recruiters/reject/{userId}")
    public ResponseEntity<ResponseDTO<String>> rejectRecruiter(@PathVariable Integer userId) {
        recruiterService.rejectRecruiter(userId);
        return ResponseEntity.ok(new ResponseDTO<>("Solicitud de recruiter rechazada.", null));
    }

    // Listar solicitudes pendientes
    @GetMapping("/recruiters/pending")
    public ResponseEntity<ResponseDTO<List<RecruiterResponseDTO>>> getPendingRecruiters() {
        List<RecruiterResponseDTO> pending = recruiterService.getPendingRecruiters();
        return ResponseEntity.ok(new ResponseDTO<>(pending, null));
    }
}
