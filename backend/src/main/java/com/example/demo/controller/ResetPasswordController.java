package com.example.demo.controller;




import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.ForgotPasswordRequest;
import com.example.demo.model.OtpToken;
import com.example.demo.model.OtpVerificationRequest;
import com.example.demo.model.ResetPasswordRequest;
import com.example.demo.model.Users;
import com.example.demo.repo.OtpTokenRepository;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.EmailService;
import com.example.demo.service.JWTService;

@RestController
@RequestMapping("/auth")
public class ResetPasswordController {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private JWTService jwtUtil;
    
    @Autowired
    private EmailService emailService;
    
    
    @Autowired
    private OtpTokenRepository otpTokenRepository;

    @Autowired
    private JWTService jwtService;
    
    @Autowired
    private UserRepo userRepo;
   

    private BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();

    @PutMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request, 
                                                @RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove "Bearer "
        }

        String email = jwtUtil.extractEmail(token);

        if (email == null) {
            return ResponseEntity.badRequest().body("Invalid token.");
        }

        Users user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found.");
        }

        // Hash the new password before saving
        user.setPassword(encoder.encode(request.getNewPassword())); // Fix: Use request's new password
        userRepository.save(user);

        return ResponseEntity.ok("Password reset successful.");
    }

    @PostMapping("/verify-otp")
    public Map<String,String> verifyOtp(@RequestBody OtpVerificationRequest request) {
 	   
	      Map<String, String> response = new HashMap<>();
        OtpToken otpToken = otpTokenRepository.findByOtp(request.getOtp());     
        //Users us= userRepo.findByUsername(request.getEmail());

//////        if (otpToken == null || !otpToken.getOtp().equals(request.getOtp())) {
//////            return ResponseEntity.badRequest().body("Invalid OTP.");
//////        }
//////       
//////        if (otpToken.getExpirationTime().isBefore(LocalDateTime.now())) 
//////            return ResponseEntity.badRequest().body("OTP expired.");
//////        }
//
//        // Generate short-span JWT token (valid for 10 minutes)
//        
        String token = jwtService.generateShortLivedToken(otpToken.getEmail(),10);
	      
	      response.put("token", token);
	      return response;

      
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Map<String, String>> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        Users user = userRepo.findByEmail(request.getEmail());
        if (user == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email not found!"));
        }

        // Generate OTP (6-digit code)
        String otp = String.format("%06d", new Random().nextInt(999999));

        // Store OTP with expiration time (5 minutes)
        OtpToken otpToken = new OtpToken(user.getId(), user.getEmail(), otp, LocalDateTime.now().plusMinutes(5));
        otpTokenRepository.save(otpToken);

        // Send OTP email
        emailService.sendOtpEmail(user.getEmail(), otp);

        // Generate short-lived token
        //String token = jwt.generateShortLivedToken(otpToken.getEmail(), 10);

        // Create a response map
        Map<String, String> response = new HashMap<>();
       
        //response.put("token", token);

        return ResponseEntity.ok(response);
    }


    }

