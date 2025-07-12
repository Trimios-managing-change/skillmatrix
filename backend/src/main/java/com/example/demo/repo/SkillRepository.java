package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {
	  Skill findBySkillNameIgnoreCase(String skillName);
}