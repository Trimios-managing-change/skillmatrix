package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.JobRole;

public interface JobRoleRepository extends JpaRepository<JobRole, Long> {
    Optional<JobRole> findByJobRoleName(String name);
}