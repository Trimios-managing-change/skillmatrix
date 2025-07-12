package com.example.demo.DTO;




import java.util.Map;

public class SkillAssessmentDTO {
    private String roleName;
    private Map<String, String> skillLevels; // e.g., {"Linux": "Beginner", "AWS": "Intermediate"}

    // Getters & Setters
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Map<String, String> getSkillLevels() {
        return skillLevels;
    }

    public void setSkillLevels(Map<String, String> skillLevels) {
        this.skillLevels = skillLevels;
    }
}
