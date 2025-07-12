package com.example.demo.model;




import jakarta.persistence.*;

@Entity
@Table(name = "skill_verification")
public class SkillVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user; // Link to the Users table

    @Column(name = "skill_name", nullable = false)
    private String skillName;

    @Column(name = "assigned_score", nullable = false)
    private Double assignedScore;

    @Column(name = "course_link", length = 1000)
    private String courseLink;

    @Column(name = "assessment_status", nullable = false)
    private String assessmentStatus;

    @Column(name = "feedback", length = 1000)
    private String feedback;

    @Column(name = "verified_score")
    private Double verifiedScore;

    // Getters and Setters
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

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Double getAssignedScore() {
        return assignedScore;
    }

    public void setAssignedScore(Double assignedScore) {
        this.assignedScore = assignedScore;
    }

    public String getCourseLink() {
        return courseLink;
    }

    public void setCourseLink(String courseLink) {
        this.courseLink = courseLink;
    }

    public String getAssessmentStatus() {
        return assessmentStatus;
    }

    public void setAssessmentStatus(String assessmentStatus) {
        this.assessmentStatus = assessmentStatus;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Double getVerifiedScore() {
        return verifiedScore;
    }

    public void setVerifiedScore(Double verifiedScore) {
        this.verifiedScore = verifiedScore;
    }
}
