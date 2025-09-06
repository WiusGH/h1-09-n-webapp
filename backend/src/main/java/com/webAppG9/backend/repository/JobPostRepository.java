package com.webAppG9.backend.repository;

// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.webAppG9.backend.Model.JobPost;

public interface JobPostRepository extends JpaRepository<JobPost, Integer> {

    // Optional<JobPost> findByEmail(String id);

}
