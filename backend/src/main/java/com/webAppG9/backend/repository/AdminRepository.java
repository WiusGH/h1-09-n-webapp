package com.webAppG9.backend.repository;

import com.webAppG9.backend.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    // Buscar admin por userId
    Optional<Admin> findByUserId(Integer userId);
}
