package com.example.demo.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
    Users findByEmail(String email);

	Users findById(Long userId);
    
    
}

