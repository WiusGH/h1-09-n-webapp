package com.webAppG9.backend.service;

import com.webAppG9.backend.Model.User;
import com.webAppG9.backend.dto.UserDTO;
import com.webAppG9.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Map<String, Object> buildUserResponse(User user) {
        Map<String, Object> data = new HashMap<>();
        data.put("user", new UserDTO(
                user.getRole(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getIsActive(),
                user.getCreatedAt()));
        return data;
    }

    // metodo para buscar todoos los usuarios
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                // Mapear cada user y convertir a DTO
                .map(u -> new UserDTO(
                        u.getRole(),
                        u.getName(),
                        u.getLastName(),
                        u.getEmail(),
                        u.getIsActive(),
                        u.getCreatedAt()))
                // Devolver una lista con todos los users
                .toList();
    }

    // Buscar usuarios por id
    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // actualizar usurio
    public User updateUser(Integer id, UserDTO updatedUser) {
        User user = getUserById(id);
        user.setName(updatedUser.getName());
        user.setLastName(updatedUser.getLastName());
        user.setRole(updatedUser.getRole());
        // Agregar otros campos si es necesario
        return userRepository.save(user);
    }

    // Cambiar estadpo de usuario
    // Service
    public Map<String, Object> toggleUserStatus(Integer id) {
        User user = getUserById(id);
        user.setIsActive(!user.getIsActive());
        userRepository.save(user);
        Map<String, Object> response = new HashMap<>();
        response.put("message", user.getIsActive() ? "Usuario activado" : "Usuario bloqueado");
        response.put("active", user.getIsActive());
        return response;
    }

    // Eliminar usuario
    public Map<String, String> deleteUser(Integer id) {
        getUserById(id); // lanza excepción si no existe
        userRepository.deleteById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario eliminado con éxito");
        return response;
    }

    // metodo para buscar usuario por email
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // metodo para actualziar un usuario
    public User updateMyProfile(String email, UserDTO updatedUser) {
        User user = getUserByEmail(email);
        user.setName(updatedUser.getName());
        user.setLastName(updatedUser.getLastName());
        user.setRole(updatedUser.getRole());
        // agregarmas campos de ser necesario
        return userRepository.save(user);
    }
}
