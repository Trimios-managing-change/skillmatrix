package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.OtpToken;

public interface OtpTokenRepository extends JpaRepository<OtpToken, Long> {
    OtpToken findByOtp(String email);
}
