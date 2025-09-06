package com.webAppG9.backend.service;

import com.webAppG9.backend.Model.User;
import com.webAppG9.backend.dto.UserDTO;
import com.webAppG9.backend.repository.UserRepository;
import com.webAppG9.backend.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // Metodo par enviar de forma segura los datos al front
    private Map<String, Object> buildUserResponse(User user) {
        Map<String, Object> data = new HashMap<>();
        data.put("user", new UserDTO(
                user.getRole(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.isActive(),
                user.getCreatedAt()));
        return data;
    }

    // Registrar usuario
    public Map<String, Object> register(User user) {
        // Validaciones
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email ya registrado");
        }

        // Encriptar contraseña
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // guardar user
        userRepository.save(user);
        // cosnstruir usuario DTO
        return buildUserResponse(user);
    }

    // Login
    public Map<String, Object> login(String email, String password) {
        // Buscar usuario por email desde la instancia en memoria
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // comprar contraseña
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }
        // generar Tocken para session
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        // Crear la resouesta DTo
        Map<String, Object> data = buildUserResponse(user);
        // Añadir el token a la respuesta
        data.put("token", token);

        return data;
    }
}
