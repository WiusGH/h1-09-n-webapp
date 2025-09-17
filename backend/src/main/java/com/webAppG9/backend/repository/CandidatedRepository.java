package com.webAppG9.backend.repository;

import com.webAppG9.backend.Model.Candidated;
import com.webAppG9.backend.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidatedRepository extends JpaRepository<Candidated, Integer> {

    // Listar todos los candidatos activos
    List<Candidated> findAllByActiveTrue();

    Optional<Candidated> findByUserEmail(String email);

    Optional<Candidated> findByUser(User user);

}