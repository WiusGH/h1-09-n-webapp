package com.webAppG9.backend.controller;

import com.webAppG9.backend.Model.User;
import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.dto.UserDTO;
import com.webAppG9.backend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private Map<String, Object> buildUserResponse(User user) {
        Map<String, Object> data = new HashMap<>();
        data.put("user", new UserDTO(
                user.getRole(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt()));
        return data;
    }

    // ========================
    // CRUD PARA ADMIN
    // ========================

    // Listar todos los usuarios
    @GetMapping
    public ResponseEntity<ResponseDTO<List<UserDTO>>> getAllUsers() {
        try {
            // Convertir todos los usuarios a DTO
            List<UserDTO> users = userRepository.findAll().stream()
                    .map(user -> new UserDTO(
                            user.getRole(),
                            user.getUsername(),
                            user.getEmail(),
                            user.getCreatedAt()))
                    .toList();

            // Si no hay usuarios
            if (users.isEmpty()) {
                return ResponseEntity.status(404)
                        .body(new ResponseDTO<>(null, "No se encontraron usuarios"));
            }

            // Retornar lista de usuarios
            return ResponseEntity.ok(new ResponseDTO<>(users, null));

        } catch (Exception e) {
            // Capturar cualquier error inesperado
            return ResponseEntity.status(500)
                    .body(new ResponseDTO<>(null, "Error al obtener usuarios: " + e.getMessage()));
        }
    }

    // Ver usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<Map<String, Object>>> getUser(@PathVariable Long id) {
        try {
            return userRepository.findById(id)
                    .map(user -> {
                        Map<String, Object> data = buildUserResponse(user);

                        return ResponseEntity.ok(new ResponseDTO<>(data, null));
                    })
                    .orElseGet(() -> ResponseEntity.status(404)
                            .body(new ResponseDTO<>(null, "Usuario no encontrado")));
        } catch (Exception e) {
            // Captura errores inesperados de la DB u otra causa
            ResponseDTO<Map<String, Object>> response = new ResponseDTO<>(null,
                    "Error al obtener usuario: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    // Actualizar usuario por ID
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<Map<String, Object>>> updateUser(
            @PathVariable Long id,
            @RequestBody User updatedUser) {
        try {
            return userRepository.findById(id)
                    .map(user -> {
                        // Actualizar usuario
                        user.setUsername(updatedUser.getUsername());
                        user.setEmail(updatedUser.getEmail());
                        user.setRole(updatedUser.getRole());
                        userRepository.save(user);

                        // Preparar DTO de respuesta
                        Map<String, Object> data = buildUserResponse(user);

                        return ResponseEntity.ok(new ResponseDTO<>(data, null));
                    })
                    .orElseGet(() -> ResponseEntity.status(404)
                            .body(new ResponseDTO<>(null, "Usuario no encontrado")));
        } catch (Exception e) {
            // Captura errores inesperados (ej: duplicado de email)
            ResponseDTO<Map<String, Object>> response = new ResponseDTO<>(null,
                    "Error al actualizar usuario: " + e.getMessage());
            return ResponseEntity.status(400).body(response);
        }
    }

    // Bloquear o activar usuario
    @PutMapping("/{id}/status")
    public ResponseEntity<ResponseDTO<Map<String, Object>>> toggleUserStatus(@PathVariable Long id) {
        try {
            return userRepository.findById(id)
                    .map(user -> {
                        // Invertir el valor actual
                        boolean nuevoEstado = !user.isActive();
                        user.setActive(nuevoEstado);
                        userRepository.save(user);

                        // Preparar la respuesta
                        Map<String, Object> data = new HashMap<>();
                        data.put("message", nuevoEstado ? "Usuario activado" : "Usuario bloqueado");
                        data.put("active", nuevoEstado);

                        return ResponseEntity.ok(new ResponseDTO<>(data, null));
                    })
                    .orElseGet(() -> ResponseEntity.status(404)
                            .body(new ResponseDTO<>(null, "Usuario no encontrado")));
        } catch (Exception e) {
            // Captura errores inesperados
            ResponseDTO<Map<String, Object>> response = new ResponseDTO<>(null,
                    "Error al actualizar usuario: " + e.getMessage());
            return ResponseEntity.status(400).body(response);
        }
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Map<String, String>>> deleteUser(@PathVariable Long id) {
        try {
            return userRepository.findById(id)
                    .map(user -> {
                        // Borrar usuario en la DB
                        userRepository.delete(user);

                        // Preparar respuesta
                        Map<String, String> data = new HashMap<>();
                        data.put("message", "Usuario eliminado con éxito");

                        return ResponseEntity.ok(new ResponseDTO<>(data, null));
                    })
                    .orElseGet(() -> ResponseEntity.status(404)
                            .body(new ResponseDTO<>(null, "Usuario no encontrado")));
        } catch (Exception e) {
            // Captura errores inesperados
            ResponseDTO<Map<String, String>> response = new ResponseDTO<>(null,
                    "Error al eliminar usuario: " + e.getMessage());
            return ResponseEntity.status(400).body(response);
        }
    }

    // ENDPOINTS PARA USER

    // Ver perfil propio
    @GetMapping("/me")
    public ResponseEntity<ResponseDTO<Map<String, Object>>> getMyProfile(Authentication authentication) {
        try {
            String email = authentication.getName();

            return userRepository.findByEmail(email)
                    .map(user -> {
                        // Preparar DTO de usuario
                        Map<String, Object> data = buildUserResponse(user);

                        return ResponseEntity.ok(new ResponseDTO<>(data, null));
                    })
                    .orElseGet(() -> ResponseEntity.status(404)
                            .body(new ResponseDTO<>(null, "Usuario no encontrado")));
        } catch (Exception e) {
            // Captura errores inesperados (ej: DB, problemas de autenticación)
            ResponseDTO<Map<String, Object>> response = new ResponseDTO<>(null,
                    "Error al obtener perfil: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    // Actualizar perfil propio
    @PutMapping("/me")
    public ResponseEntity<ResponseDTO<Map<String, Object>>> updateMyProfile(
            @RequestBody User updatedUser,
            Authentication authentication) {

        try {
            String email = authentication.getName();

            return userRepository.findByEmail(email)
                    .map(user -> {
                        // Actualizar usuario
                        user.setUsername(updatedUser.getUsername());
                        // user.setEmail(updatedUser.getEmail()); // si querés permitir cambiar email
                        userRepository.save(user);

                        // Preparar DTO de respuesta
                        Map<String, Object> data = buildUserResponse(user);

                        return ResponseEntity.ok(new ResponseDTO<>(data, null));
                    })
                    .orElseGet(() -> ResponseEntity.status(404)
                            .body(new ResponseDTO<>(null, "Usuario no encontrado")));

        } catch (Exception e) {
            // Captura errores inesperados
            ResponseDTO<Map<String, Object>> response = new ResponseDTO<>(null,
                    "Error al actualizar perfil: " + e.getMessage());
            return ResponseEntity.status(400).body(response);
        }
    }

}
