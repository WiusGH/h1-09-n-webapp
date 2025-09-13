package com.webAppG9.backend.repository;

import java.util.List;

// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.webAppG9.backend.Model.JobPost;

public interface JobPostRepository extends JpaRepository<JobPost, Integer> {

    @Query("SELECT j FROM JobPost j JOIN j.skills s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<JobPost> findBySkillKeyword(@Param("keyword") String keyword);
}
