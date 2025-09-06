package com.webAppG9.backend.controller;

import com.webAppG9.backend.Model.Admin;
import com.webAppG9.backend.dto.AdminDTO;
import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
    // ATRIBUTOS
    private final AdminService adminService;

    // CONSTRUCTOR
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Crear un admin para un usuario existente
    @PostMapping
    public ResponseEntity<ResponseDTO<Object>> createAdmin(@RequestBody AdminDTO adminDTO) {
        Admin admin = adminService.createAdmin(adminDTO);
        return ResponseEntity.ok(new ResponseDTO<>(AdminDTO.fromEntity(admin), null));
    }
}
