package com.webAppG9.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webAppG9.backend.Model.JobApplication;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {

}
