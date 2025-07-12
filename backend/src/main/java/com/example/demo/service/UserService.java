package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.demo.model.Login;
import com.example.demo.model.Users;
import com.example.demo.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private JWTService jwtService;
	
	
	
	@Autowired
	AuthenticationManager authManger;
	public Users register(Users user)
	{
		return userRepo.save(user);
	}
	
	public ResponseEntity<Map<String, String>> verify(Login user) {
	    Authentication auth = authManger.authenticate(
	        new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
	    );

	    if (auth.isAuthenticated()) {
	        Users us = userRepo.findByEmail(user.getEmail());

	        Map<String, String> response = new HashMap<>();
	       
	        response.put("token", jwtService.generateToken(user.getEmail(), us.getUserType()));
	        response.put("role", us.getUserType().getRoleName());

	        return ResponseEntity.ok(response);
	    }

	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	        .body(Map.of("error", "Invalid email or password"));
	}

}
