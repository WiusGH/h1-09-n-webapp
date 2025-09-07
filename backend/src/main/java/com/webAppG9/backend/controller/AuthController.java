package com.webAppG9.backend.controller;

import com.webAppG9.backend.Model.User;
import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public ResponseEntity<ResponseDTO<Map<String, Object>>> register(@RequestBody User user) {
        // Crear UserDTO para la respuesta
        Map<String, Object> data = authService.register(user);

        // Devolver éxito con ResponseDTO
        return ResponseEntity.ok(new ResponseDTO<>(data, null));
    }

    // metodo login
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<Map<String, Object>>> login(@RequestBody Map<String, String> request) {
        // guardo las variavles que necesito del Json que proviene del request
        String email = request.get("email");
        String password = request.get("password");

        // Preparar datos de usuario + token
        Map<String, Object> data = authService.login(email, password);

        // Devolver LA RESPUESTA COMO dto
        return ResponseEntity.ok(new ResponseDTO<>(data, null));
    }

}
