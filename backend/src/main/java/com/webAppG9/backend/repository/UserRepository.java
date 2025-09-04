package com.webAppG9.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webAppG9.backend.Model.User;

//dmetodo interfaz qeu  hereda todos los metodos de JraRepository
//trabaja con la entidad User y el tipo de ID (Long)
public interface UserRepository extends JpaRepository<User, Long> {

    // Optional para que en caso de no halklar nada no de null,
    Optional<User> findByEmail(String email);

    // buscar pasanndo el tipo de dato y el valor de la entidad
    // User findByUsername(String username);
}
