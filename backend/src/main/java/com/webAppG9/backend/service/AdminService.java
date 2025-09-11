package com.webAppG9.backend.service;

import org.springframework.stereotype.Service;

import com.webAppG9.backend.Model.Admin;
import com.webAppG9.backend.Model.User;
import com.webAppG9.backend.dto.admin.AdminDTO;
import com.webAppG9.backend.repository.AdminRepository;
import com.webAppG9.backend.repository.UserRepository;

// AdminService.java
@Service
public class AdminService {
    // Atruibutos d la clase
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    // Constructor
    public AdminService(AdminRepository adminRepository, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    // metodo para crear usuario admin con sus atributos
    public Admin createAdmin(AdminDTO adminDTO) {
        // Verificar que el usuario exista
        User user = userRepository.findById(adminDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Verificar que no sea ya admin
        if (adminRepository.existsById(adminDTO.getUserId())) {
            throw new RuntimeException("Este usuario ya es admin");
        }

        // Crear Admin
        Admin admin = new Admin();
        admin.setUser(user); // asocia la entidad User

        // actualizar rol en User
        user.setRole(User.Role.ADMIN);
        userRepository.save(user);

        // Guardar el usuario admon
        return adminRepository.save(admin);
    }
}
