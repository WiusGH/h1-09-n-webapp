package com.webAppG9.backend.controller;

import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.dto.admin.AdminRequestDTO;
import com.webAppG9.backend.dto.admin.AdminResponseDTO;
import com.webAppG9.backend.dto.recruiter.RecruiterResponseDTO;
import com.webAppG9.backend.service.AdminService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/admins")
@Tag(name = "Admins", description = "APIs para la gestión de administradores y aprobación de recruiters")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @Operation(summary = "Crear un admin para un usuario existente", description = "Permite asignar privilegios de administrador a un usuario ya registrado.")
    @PostMapping
    public ResponseEntity<ResponseDTO<AdminResponseDTO>> createAdmin(
            @RequestBody AdminRequestDTO adminDTO) {
        AdminResponseDTO admin = adminService.createAdmin(adminDTO);
        return ResponseEntity.ok(ResponseDTO.ok(admin));
    }

    @Operation(summary = "Listar solicitudes de recruiters pendientes", description = "Devuelve una lista de recruiters que han solicitado ser aprobados y están pendientes de revisión.")
    @GetMapping("/recruiters/pending")
    public ResponseEntity<ResponseDTO<List<RecruiterResponseDTO>>> getPendingRecruiters() {
        List<RecruiterResponseDTO> pending = adminService.getPendingRecruiters();
        return ResponseEntity.ok(ResponseDTO.ok(pending));
    }

    @Operation(summary = "Aprobar solicitud de recruiter", description = "Aprueba la solicitud de un recruiter pendiente y lo activa como recruiter en el sistema.")
    @PatchMapping("/recruiters/approve")
    public ResponseEntity<ResponseDTO<RecruiterResponseDTO>> approveRecruiter() {
        RecruiterResponseDTO recruiter = adminService.approveRecruiter();
        return ResponseEntity.ok(ResponseDTO.ok(recruiter));
    }

    @Operation(summary = "Rechazar solicitud de recruiter", description = "Rechaza la solicitud de un recruiter pendiente.")
    @PatchMapping("/recruiters/reject")
    public ResponseEntity<ResponseDTO<String>> rejectRecruiter() {
        adminService.rejectRecruiter();
        return ResponseEntity.ok(new ResponseDTO<>("Solicitud de recruiter rechazada.", null));
    }

    @Operation(summary = "Listar todos los recruiters aprobados", description = "Devuelve una lista completa de todos los recruiters aprobados en el sistema, útil para networking.")
    @GetMapping("/recruiters/all")
    public ResponseEntity<ResponseDTO<List<RecruiterResponseDTO>>> getAllRecruiters() {
        List<RecruiterResponseDTO> recruiters = adminService.getAllApprovedRecruiters();
        return ResponseEntity.ok(new ResponseDTO<>(recruiters, null));
    }
}
