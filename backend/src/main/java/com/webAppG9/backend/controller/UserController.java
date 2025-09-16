package com.webAppG9.backend.controller;

import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.dto.user.UserRequestDTO;
import com.webAppG9.backend.dto.user.UserResponseDTO;
import com.webAppG9.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Buscar todos los usuarios
    @GetMapping
    public ResponseEntity<ResponseDTO<List<UserResponseDTO>>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(ResponseDTO.ok(users));
    }

    // Buscar un usuario por id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> getUser(@PathVariable Integer id) {
        UserResponseDTO user = userService.getUserById(id);
        return ResponseEntity.ok(ResponseDTO.ok(user));
    }

    // Actualizar un usuario
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> updateUser(
            @PathVariable Integer id,
            @RequestBody UserRequestDTO updatedUser) {
        UserResponseDTO user = userService.updateUser(id, updatedUser);
        return ResponseEntity.ok(ResponseDTO.ok(user));
    }

    // cambiar el status del usuario
    @PutMapping("/{id}/status")
    public ResponseEntity<ResponseDTO<String>> toggleUserStatus(@PathVariable Integer id) {
        String response = userService.toggleUserStatus(id);
        return ResponseEntity.ok(ResponseDTO.ok(response));
    }

    // eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteUser(@PathVariable Integer id) {
        String response = userService.deleteUser(id);
        return ResponseEntity.ok(ResponseDTO.ok(response));
    }

}
