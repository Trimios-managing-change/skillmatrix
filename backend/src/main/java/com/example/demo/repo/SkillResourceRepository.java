package com.example.demo.repo;



import com.example.demo.model.SkillResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillResourceRepository extends JpaRepository<SkillResource, Long> {
    SkillResource findBySkillName(String skillName);
    
 
    
    // Add this method to find by skill ID
    
}