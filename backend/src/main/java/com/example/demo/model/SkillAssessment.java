package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



import jakarta.persistence.*;



import java.time.LocalDateTime;

@Entity
@Table(name = "skill_assessment")
public class SkillAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🧑 User who submitted the skill assessment
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    // 🎯 Skill being assessed (e.g., Linux, AWS)
    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    // 🎓 Role the skill assessment is associated with (e.g., DevOps, Backend)
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    // 📊 Skill level (e.g., Beginner, Intermediate, Expert)
    private String level;

    // ✅ Verification status - to be done by SME
    private boolean isVerified = false;

    // 🧑 Verified by (e.g., admin or SME)
    @Column(name = "verified_by")
    private String verifiedBy;

    // 🕒 Date of verification
    @Column(name = "verification_date")
    private LocalDateTime verificationDate;

    // 💬 Comments or feedback from verifier
    @Column(name = "comments", length = 500)
    private String comments;

    // ---------- Getters and Setters -----------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public LocalDateTime getVerificationDate() {
        return verificationDate;
    }

    public void setVerificationDate(LocalDateTime verificationDate) {
        this.verificationDate = verificationDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
