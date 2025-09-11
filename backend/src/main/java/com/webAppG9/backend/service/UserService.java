package com.webAppG9.backend.service;

import com.webAppG9.backend.Model.User;
import com.webAppG9.backend.dto.ResponseDTO;
import com.webAppG9.backend.dto.user.UserDTO;
import com.webAppG9.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // metodo para buscar todoos los usuarios
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                // Mapear cada user y convertir a DTO
                .map(u -> new UserDTO(u))
                // Devolver una lista con todos los users
                .toList();
    }

    // actualizar usurio
    public UserDTO updateUser(Integer id, UserDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado"));
        // Metodo para mapear usuario en UserDTO
        dto.applyToEntity(user);
        // Fuardar el usuario y enviarlo al controller
        User updated = userRepository.save(user);
        return new UserDTO(updated);

    }

    // Cambiar estado de usuario
    public ResponseDTO<String> toggleUserStatus(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado"));
        // Buscar el estado y guardarlo
        user.setIsActive(!user.getIsActive());
        userRepository.save(user);

        String message = user.getIsActive() ? "Candidato activo" : "Candidato pausado";
        return ResponseDTO.ok(message);

    }

    // Eliminar usuario
    public ResponseDTO<String> deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado"));
        userRepository.delete(user);
        // Devolver mensaje como ResponseDTO<String>
        return ResponseDTO.ok("Usuario eliminado con éxito");
    }

    // Buscar usuarios por id
    public ResponseDTO<UserDTO> getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return ResponseDTO.ok(new UserDTO(user));
    }

}
