package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "otp_token")

public class OtpToken {
    @Id
   private Long id;

    private String email;
    private String otp;
    private LocalDateTime expirationTime;

    // Constructors
    public OtpToken() {
    }

    public OtpToken(Long id,String email, String otp, LocalDateTime expirationTime)
    {
    	this.id=id;
        this.email = email;
        this.otp = otp;
        this.expirationTime = expirationTime;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }
}
