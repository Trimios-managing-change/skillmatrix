package com.example.demo.DTO;

import java.util.Map;


public class SkillAssessmentSMEDTO {

    private String role;
    private Map<String, String> skills; // Key: Skill Name, Value: Skill Level

    // Getters and Setters
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Map<String, String> getSkills() {
        return skills;
    }

    public void setSkills(Map<String, String> skills) {
        this.skills = skills;
    }
}




