package com.webAppG9.backend.controller;

import com.webAppG9.backend.Model.User;
import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.dto.UserDTO;
import com.webAppG9.backend.repository.UserRepository;
import com.webAppG9.backend.security.JwtUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // atributos
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // constructor
    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // metodo para mostrar user
    private Map<String, Object> buildUserResponse(User user) {
        Map<String, Object> data = new HashMap<>();
        data.put("user", new UserDTO(
                user.getRole(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt()));
        return data;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<Map<String, Object>>> register(@RequestBody User user) {
        try {
            // Encriptar contraseña
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            // Guardar usuario en la base de datos
            userRepository.save(user);

            // Crear UserDTO para la respuesta
            Map<String, Object> data = buildUserResponse(user);

            // Devolver éxito con ResponseDTO
            return ResponseEntity.ok(new ResponseDTO<>(data, null));

        } catch (Exception e) {
            // Capturar errores inesperados (ej: email duplicado)
            ResponseDTO<Map<String, Object>> response = new ResponseDTO<>(null,
                    "Error al registrar usuario: " + e.getMessage());
            return ResponseEntity.status(400).body(response);
        }
    }

    // metodo login
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<Map<String, Object>>> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        return userRepository.findByEmail(email)
                .map(user -> {
                    // Verificar contraseña
                    if (!passwordEncoder.matches(password, user.getPassword())) {
                        ResponseDTO<Map<String, Object>> response = new ResponseDTO<>(null, "Contraseña incorrecta");
                        return ResponseEntity.status(401).body(response);
                    }

                    // Generar token JWT
                    String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

                    // Preparar datos de usuario + token
                    Map<String, Object> data = buildUserResponse(user);

                    data.put("token", token);

                    // Devolver éxito
                    return ResponseEntity.ok(new ResponseDTO<>(data, null));
                })
                .orElseGet(() -> {
                    // Usuario no encontrado
                    ResponseDTO<Map<String, Object>> response = new ResponseDTO<>(null, "Usuario no encontrado");
                    return ResponseEntity.status(404).body(response);
                });
    }

}
