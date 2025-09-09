package com.webAppG9.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.webAppG9.backend.dto.RecruiterResponseDTO;
import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.service.RecruiterService;

@RestController
@RequestMapping("/api/recruiters")
public class RecruiterController {

    private final RecruiterService recruiterService;

    public RecruiterController(RecruiterService recruiterService) {
        this.recruiterService = recruiterService;
    }

    // Candidato solicita ser Recruiter
    @PostMapping("/request/{userId}")
    public ResponseEntity<ResponseDTO<String>> requestRecruiter(
            @PathVariable Integer userId,
            @RequestBody RecruiterResponseDTO request) {
        recruiterService.requestRecruiterUpgrade(userId, request);
        return ResponseEntity.ok(new ResponseDTO<>("Solicitud enviada. Espera aprobación de un administrador.", null));
    }

    // Admin aprueba
    @PatchMapping("/approve/{userId}")
    public ResponseEntity<ResponseDTO<String>> approveRecruiter(@PathVariable Integer userId) {
        recruiterService.approveRecruiter(userId);
        return ResponseEntity.ok(new ResponseDTO<>("Usuario aprobado como Recruiter.", null));
    }
}
