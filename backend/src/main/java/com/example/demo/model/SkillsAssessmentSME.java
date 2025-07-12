package com.example.demo.model;



import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;






import jakarta.persistence.*;

@Entity
@Table(name = "skills_assessment_sme")
public class SkillsAssessmentSME {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;

    private String role;

    private boolean submittedToSME = false;

    private String status; // e.g., "Pending", "Reviewed", "Approved", "Rejected"

    @Lob
    private String skills; // Store skills as a single concatenated string (e.g., "Java:Advanced,Python:Intermediate")

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isSubmittedToSME() {
        return submittedToSME;
    }

    public void setSubmittedToSME(boolean submittedToSME) {
        this.submittedToSME = submittedToSME;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}






