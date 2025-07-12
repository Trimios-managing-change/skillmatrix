package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
	UserProfile  findByEmail(String email);
}