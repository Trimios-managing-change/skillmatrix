package com.example.demo.DTO;



import java.util.List;

public class UserDetailsDTO {
    private String name;
    private String course;

    private String sscInstitute;
    private Double sscMarks;
    private String interInstitute;
    private Double interMarks;
    private String graduationInstitute;
  //  private Double graduationMarks;

    private List<String> internshipExperiences;
    private String description;

//    private byte[] img;
//    private byte[] resume;

    private String email;
    private String phone;

    // Getters and Setters

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

    public void setInternshipExperiences(List<String> internshipExperiences) {
        this.internshipExperiences = internshipExperiences;
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
}
