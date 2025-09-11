package com.webAppG9.backend.controller;

import com.webAppG9.backend.Model.User;
import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.dto.auth.LoginRequestDTO;
import com.webAppG9.backend.dto.auth.LoginResponseDTO;
import com.webAppG9.backend.dto.auth.RegisterResponseDTO;
import com.webAppG9.backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // atributos
    private final AuthService authService;

    // constructor
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody User user) {
        // Crear UserDTO para la respuesta
        RegisterResponseDTO data = authService.register(user);

        // Devolver éxito con ResponseDTO
        return ResponseEntity.ok(data);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<LoginResponseDTO>> login(@RequestBody LoginRequestDTO request) {
        LoginResponseDTO response = authService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(new ResponseDTO<>(response, null));
    }

}
