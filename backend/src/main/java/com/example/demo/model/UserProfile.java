package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.util.List;

import jakarta.persistence.*;





@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private String name;
    private String course;

    private String sscInstitute;
    private Double sscMarks;
    private String interInstitute;
    private Double interMarks;
    private String graduationInstitute;
   // private Double graduationMarks;

    private List<String> internshipExperiences; // Concatenated internships as a single string

    @Lob
    private String description;

//    @Lob
//    private byte[] img;
//
//    @Lob
//    private byte[] resume;

    private String email;
    private String phone;

    private boolean profileSubmitted; // Indicates if the profile is completed

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id") // FK to Users table
    private Users user;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSscInstitute() {
        return sscInstitute;
    }

    public void setSscInstitute(String sscInstitute) {
        this.sscInstitute = sscInstitute;
    }

    public Double getSscMarks() {
        return sscMarks;
    }

    public void setSscMarks(Double sscMarks) {
        this.sscMarks = sscMarks;
    }

    public String getInterInstitute() {
        return interInstitute;
    }

    public void setInterInstitute(String interInstitute) {
        this.interInstitute = interInstitute;
    }

    public Double getInterMarks() {
        return interMarks;
    }

    public void setInterMarks(Double interMarks) {
        this.interMarks = interMarks;
    }

    public String getGraduationInstitute() {
        return graduationInstitute;
    }

    public void setGraduationInstitute(String graduationInstitute) {
        this.graduationInstitute = graduationInstitute;
    }

//    public Double getGraduationMarks() {
//        return graduationMarks;
//    }
//
//    public void setGraduationMarks(Double graduationMarks) {
//        this.graduationMarks = graduationMarks;
//    }

    public List<String> getInternshipExperiences() {
        return internshipExperiences;
    }

    public void setInternshipExperiences(List<String> list) {
        this.internshipExperiences = list;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public byte[] getImg() {
//        return img;
//    }
//
//    public void setImg(byte[] img) {
//        this.img = img;
//    }
//
//    public byte[] getResume() {
//        return resume;
//    }
//
//    public void setResume(byte[] resume) {
//        this.resume = resume;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isProfileSubmitted() {
        return profileSubmitted;
    }

    public void setProfileSubmitted(boolean profileSubmitted) {
        this.profileSubmitted = profileSubmitted;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}


