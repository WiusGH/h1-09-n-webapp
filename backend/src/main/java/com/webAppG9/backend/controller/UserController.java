package com.webAppG9.backend.controller;

import com.webAppG9.backend.Model.User;
import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.dto.UserDTO;
import com.webAppG9.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ========================
    // CRUD PARA ADMIN
    // ========================

    @GetMapping
    public ResponseEntity<ResponseDTO<List<UserDTO>>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(new ResponseDTO<>(users, null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<Map<String, Object>>> getUser(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        Map<String, Object> data = userService.buildUserResponse(user);
        return ResponseEntity.ok(new ResponseDTO<>(data, null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<Map<String, Object>>> updateUser(
            @PathVariable Integer id,
            @RequestBody UserDTO updatedUser) {
        User user = userService.updateUser(id, updatedUser);
        Map<String, Object> data = userService.buildUserResponse(user);
        return ResponseEntity.ok(new ResponseDTO<>(data, null));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ResponseDTO<Map<String, Object>>> toggleUserStatus(@PathVariable Integer id) {
        User user = userService.toggleUserStatus(id);
        Map<String, Object> data = new HashMap<>();
        data.put("message", user.isActive() ? "Usuario activado" : "Usuario bloqueado");
        data.put("active", user.isActive());
        return ResponseEntity.ok(new ResponseDTO<>(data, null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Map<String, String>>> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        Map<String, String> data = new HashMap<>();
        data.put("message", "Usuario eliminado con éxito");
        return ResponseEntity.ok(new ResponseDTO<>(data, null));
    }

    // ========================
    // ENDPOINTS PARA USER
    // ========================

    @GetMapping("/me")
    public ResponseEntity<ResponseDTO<Map<String, Object>>> getMyProfile(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.getUserByEmail(email);
        Map<String, Object> data = userService.buildUserResponse(user);
        return ResponseEntity.ok(new ResponseDTO<>(data, null));
    }

    @PutMapping("/me")
    public ResponseEntity<ResponseDTO<Map<String, Object>>> updateMyProfile(
            @RequestBody UserDTO updatedUser,
            Authentication authentication) {
        String email = authentication.getName();
        User user = userService.updateMyProfile(email, updatedUser);
        Map<String, Object> data = userService.buildUserResponse(user);
        return ResponseEntity.ok(new ResponseDTO<>(data, null));
    }
}
