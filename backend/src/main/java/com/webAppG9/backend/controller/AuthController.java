package com.webAppG9.backend.controller;

import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.dto.auth.LoginRequestDTO;
import com.webAppG9.backend.dto.auth.LoginResponseDTO;
import com.webAppG9.backend.dto.auth.RegisterRequestDTO;
import com.webAppG9.backend.dto.auth.RegisterResponseDTO;
import com.webAppG9.backend.service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "APIs para registro y autenticación de usuarios")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Registrar un nuevo usuario", description = "Permite registrar un usuario en la plataforma. Devuelve un DTO con los datos del usuario registrado.")
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<RegisterResponseDTO>> register(
            @RequestBody RegisterRequestDTO user) {
        RegisterResponseDTO data = authService.register(user);
        return ResponseEntity.ok(ResponseDTO.ok(data));
    }

    @Operation(summary = "Iniciar sesión", description = "Permite al usuario autenticarse usando email y contraseña. Devuelve un DTO con el token y los datos del usuario.")
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<LoginResponseDTO>> login(
            @RequestBody LoginRequestDTO request) {
        LoginResponseDTO response = authService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(ResponseDTO.ok(response));
    }
}
