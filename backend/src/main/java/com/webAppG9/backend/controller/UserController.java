package com.webAppG9.backend.controller;

import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.dto.user.UserDTO;
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
    public ResponseEntity<ResponseDTO<List<UserDTO>>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(new ResponseDTO<>(users, null));
    }

    // Buscar un usuario por id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<UserDTO>> getUser(@PathVariable Integer id) {

        return ResponseEntity.ok((userService.getUserById(id)));
    }

    // Actualizar un usuario
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<UserDTO>> updateUser(
            @PathVariable Integer id,
            @RequestBody UserDTO updatedUser) {
        UserDTO data = userService.updateUser(id, updatedUser);

        ResponseDTO<UserDTO> response = ResponseDTO.ok(data);

        return ResponseEntity.ok(response);
    }

    // cambiar el status del usuario
    @PutMapping("/{id}/status")
    public ResponseEntity<ResponseDTO<String>> toggleUserStatus(@PathVariable Integer id) {
        ResponseDTO<String> response = userService.toggleUserStatus(id);
        return ResponseEntity.ok(response);
    }

    // eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteUser(@PathVariable Integer id) {
        ResponseDTO<String> response = userService.deleteUser(id);
        return ResponseEntity.ok(response);
    }

}
