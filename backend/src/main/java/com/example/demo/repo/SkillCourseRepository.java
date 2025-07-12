package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.SkillCourse;

public interface SkillCourseRepository extends JpaRepository<SkillCourse, Long> {
    Optional<SkillCourse> findBySkillName(String name);
}