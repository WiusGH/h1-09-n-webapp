package com.webAppG9.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.webAppG9.backend.Model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    // Buscar un usuario por email (opcional)
    Optional<User> findByEmail(String email);

}
