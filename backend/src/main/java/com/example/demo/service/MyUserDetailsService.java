package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Users;
import com.example.demo.model.UserPrinciples;
import com.example.demo.repo.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepo ur;
	
	@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Attempting to load user: " + email);
        Users user = ur.findByEmail(email);
        if (user == null) {
            System.out.println("User not found in database: " + email);
            throw new UsernameNotFoundException("User not found: " + email);
        }
        System.out.println("User found: " + user.getEmail() + " with role: " + user.getUserType());
        return new UserPrinciples(user);
    }


}
