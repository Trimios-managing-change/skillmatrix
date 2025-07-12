package com.example.demo.model;



import jakarta.persistence.*;

@Entity
@Table(name = "skills_gap")
public class SkillsGap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user; // Link to the Users table

    @Column(name = "skill_name", nullable = false)
    private String skillName;

    @Column(name = "no_of_jobs", nullable = false)
    private Integer noOfJobs;

    @Column(name = "your_score", nullable = false)
    private Double yourScore;

    @Column(name = "course_links", length = 1000)
    private String courseLinks;

    @Column(name = "assessment_status", nullable = false)
    private String assessmentStatus;

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

    public Integer getNoOfJobs() {
        return noOfJobs;
    }

    public void setNoOfJobs(Integer noOfJobs) {
        this.noOfJobs = noOfJobs;
    }

    public Double getYourScore() {
        return yourScore;
    }

    public void setYourScore(Double yourScore) {
        this.yourScore = yourScore;
    }

    public String getCourseLinks() {
        return courseLinks;
    }

    public void setCourseLinks(String courseLinks) {
        this.courseLinks = courseLinks;
    }

    public String getAssessmentStatus() {
        return assessmentStatus;
    }

    public void setAssessmentStatus(String assessmentStatus) {
        this.assessmentStatus = assessmentStatus;
    }
}

