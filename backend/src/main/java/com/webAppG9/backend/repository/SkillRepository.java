package com.webAppG9.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.webAppG9.backend.Model.Skill;
import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Integer> {

    // Buscar skill por nombre
    Optional<Skill> findByName(String name);

}
