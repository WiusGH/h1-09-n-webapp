package com.webAppG9.backend.service;

import com.webAppG9.backend.Model.User;
import com.webAppG9.backend.dto.user.UserRequestDTO;
import com.webAppG9.backend.dto.user.UserResponseDTO;
import com.webAppG9.backend.exception.UserNotFoundException;
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
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                // Mapear cada user y convertir a DTO
                .map(UserResponseDTO::new)
                // Devolver una lista con todos los users
                .toList();
    }

    // Buscar usuarios por id
    public UserResponseDTO getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return new UserResponseDTO(user);
    }

    // actualizar usurio
    public UserResponseDTO updateUser(Integer id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        // Metodo para mapear usuario en UserDTO
        dto.applyToEntity(user);
        // Fuardar el usuario y enviarlo al controlle
        userRepository.save(user);
        return new UserResponseDTO(user);

    }

    // Cambiar estado de usuario
    public String toggleUserStatus(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        // Buscar el estado y guardarlo
        user.setIsActive(!user.getIsActive());
        userRepository.save(user);

        String message = user.getIsActive() ? "Candidato activo" : "Candidato pausado";
        return message;

    }

    // Eliminar usuario
    public String deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
        // Devolver mensaje como ResponseDTO<String>
        return "Usuario eliminado con éxito";
    }

}
