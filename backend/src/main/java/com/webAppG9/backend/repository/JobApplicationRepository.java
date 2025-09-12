package com.webAppG9.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webAppG9.backend.Model.Candidated;
import com.webAppG9.backend.Model.JobApplication;
import com.webAppG9.backend.Model.JobPost;
import com.webAppG9.backend.Model.User;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {

    // Buscar todas las aplicaciones de un usuario por su ID
    List<JobApplication> findByUserId(Integer userId);

    boolean existsByUserAndJobPost(User user, JobPost jobPost);

    // Buscar todas las aplicaciones de una oferta laboral por su ID
    List<JobApplication> findByJobPostId(Integer jobPostId);

    List<JobApplication> findByCandidated(Candidated candidated);

}
