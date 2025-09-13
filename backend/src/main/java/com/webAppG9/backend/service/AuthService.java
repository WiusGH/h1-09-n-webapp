package com.webAppG9.backend.service;

import com.webAppG9.backend.Model.User;
import com.webAppG9.backend.Model.User.Role;
import com.webAppG9.backend.dto.user.UserDTO;
import com.webAppG9.backend.exception.EmailOrPasswordException;
import com.webAppG9.backend.dto.auth.LoginResponseDTO;
import com.webAppG9.backend.dto.auth.RegisterRequestDTO;
import com.webAppG9.backend.dto.auth.RegisterResponseDTO;
import com.webAppG9.backend.repository.UserRepository;
import com.webAppG9.backend.security.JwtUtil;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
            CandidatedService candidatedService,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // Registrar usuario
    @Transactional
    public RegisterResponseDTO register(RegisterRequestDTO request) {
        // Validar email único
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        // Mapear DTO a entidad
        User user = new User();
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setProfileCompleted(false);
        user.setRole(Role.CANDIDATE); // rol por defecto
        user.setCreatedAt(LocalDateTime.now());

        // Guardar en DB
        User savedUser = userRepository.save(user);

        // Retornar el usuario completo como DTO
        return new RegisterResponseDTO(
                savedUser.getId(),
                savedUser.getEmail(),
                "Usuario registrado correctamente");

    }

    // Login
    public LoginResponseDTO login(String email, String password) {
        // Buscar usuario
        User user = userRepository.findByEmail(email)
                .orElseThrow(EmailOrPasswordException::new);

        // Validar contraseña
        if (email == null || email.isBlank() || !passwordEncoder.matches(password, user.getPassword())) {
            throw new EmailOrPasswordException();
        }

        // Generar token
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

        // Devolver DTO
        return new LoginResponseDTO(
                token,
                new UserDTO(user));
    }

}
