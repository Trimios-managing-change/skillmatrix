package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.SkillsAssessmentSME;

public interface SmeRepo extends JpaRepository<SkillsAssessmentSME, Long> {
	boolean existsByUserEmailAndSubmittedToSMETrue(String email);


}
