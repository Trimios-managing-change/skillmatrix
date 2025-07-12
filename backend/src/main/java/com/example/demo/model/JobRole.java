package com.example.demo.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;


@Entity
@Table(name = "job_roles")
public class JobRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_role_name", nullable = false, unique = true)
    private String jobRoleName;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
        name = "job_role_skills",
        joinColumns = @JoinColumn(name = "job_role_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_course_id")
    )
    private List<SkillCourse> skillCourses;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobRoleName() {
        return jobRoleName;
    }

    public void setJobRoleName(String jobRoleName) {
        this.jobRoleName = jobRoleName;
    }

    public List<SkillCourse> getSkillCourses() {
        return skillCourses;
    }

    public void setSkillCourses(List<SkillCourse> skillCourses) {
        this.skillCourses = skillCourses;
    }

    public void addSkillCourse(SkillCourse skill) {
        if (!skillCourses.contains(skill)) {
            skillCourses.add(skill);
        }
    }

    public void removeSkillCourse(SkillCourse skill) {
        skillCourses.remove(skill);
    }
}
