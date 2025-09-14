package com.webAppG9.backend.service;

import com.webAppG9.backend.Model.User;
import com.webAppG9.backend.exception.candidate.CandidateNotFoundException;
import com.webAppG9.backend.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Buscar usuario por email
        User user = userRepository.findByEmail(email)
                .orElseThrow(CandidateNotFoundException::new);

        // Convertir el rol del enum a String para Spring Security
        String roleName = "ROLE_" + user.getRole().name(); // ROLE_USER, ROLE_ADMIN, etc.

        // Crear UserDetails con email, contraseña y roles
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(roleName)));
    }
}
