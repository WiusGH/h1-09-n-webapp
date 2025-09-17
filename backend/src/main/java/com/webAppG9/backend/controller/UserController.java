package com.webAppG9.backend.controller;

import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.dto.user.UserRequestDTO;
import com.webAppG9.backend.dto.user.UserResponseDTO;
import com.webAppG9.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "APIs para la gestión de usuarios")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Obtener todos los usuarios", description = "Devuelve una lista con todos los usuarios registrados en el sistema.")
    @GetMapping
    public ResponseEntity<ResponseDTO<List<UserResponseDTO>>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(ResponseDTO.ok(users));
    }

    @Operation(summary = "Buscar usuario por ID", description = "Devuelve la información de un usuario específico según su ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> getUser(
            @Parameter(description = "ID del usuario a buscar", example = "5") @PathVariable Integer id) {
        UserResponseDTO user = userService.getUserById(id);
        return ResponseEntity.ok(ResponseDTO.ok(user));
    }

    @Operation(summary = "Actualizar usuario", description = "Actualiza la información de un usuario según su ID.")
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> updateUser(
            @Parameter(description = "ID del usuario a actualizar", example = "5") @PathVariable Integer id,
            @RequestBody UserRequestDTO updatedUser) {
        UserResponseDTO user = userService.updateUser(id, updatedUser);
        return ResponseEntity.ok(ResponseDTO.ok(user));
    }

    @Operation(summary = "Cambiar estado de usuario", description = "Activa o desactiva un usuario según su ID.")
    @PutMapping("/{id}/status")
    public ResponseEntity<ResponseDTO<String>> toggleUserStatus(
            @Parameter(description = "ID del usuario a modificar", example = "5") @PathVariable Integer id) {
        String response = userService.toggleUserStatus(id);
        return ResponseEntity.ok(ResponseDTO.ok(response));
    }

    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario del sistema según su ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteUser(
            @Parameter(description = "ID del usuario a eliminar", example = "5") @PathVariable Integer id) {
        String response = userService.deleteUser(id);
        return ResponseEntity.ok(ResponseDTO.ok(response));
    }
}
