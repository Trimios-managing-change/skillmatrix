package com.example.demo.service;


import com.example.demo.DTO.UserDetailsDTO;
import com.example.demo.model.UserProfile;
import com.example.demo.model.Users;
import com.example.demo.repo.UserProfileRepository;
import com.example.demo.repo.UserRepo;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
	
	
    @Autowired
    private UserRepo userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public void saveUserProfile(String email, UserDetailsDTO dto) {
        Users user = userRepository.findByEmail(email);

        if (user == null) {
            throw new IllegalArgumentException("User not found with email: " + email);
        }

        UserProfile profile = new UserProfile();
        profile.setName(dto.getName());
        profile.setCourse(dto.getCourse());
        profile.setSscInstitute(dto.getSscInstitute());
        profile.setSscMarks(dto.getSscMarks());
        profile.setInterInstitute(dto.getInterInstitute());
        profile.setInterMarks(dto.getInterMarks());
        profile.setGraduationInstitute(dto.getGraduationInstitute());
        //profile.setGraduationMarks(dto.getGraduationMarks());
        profile.setInternshipExperiences(dto.getInternshipExperiences());
        profile.setDescription(dto.getDescription());
//        profile.setImg(dto.getImg());
//        profile.setResume(dto.getResume());
        profile.setEmail(dto.getEmail());
        profile.setPhone(dto.getPhone());
        profile.setProfileSubmitted(true);  // Set this to true if the profile is completed
        profile.setUser(user);

        userProfileRepository.save(profile);
    }

    public UserDetailsDTO getUserProfile(String email) {
        Users user = userRepository.findByEmail(email);

        if (user == null || user.getUserProfile() == null) {
            throw new IllegalArgumentException("Profile not found for email: " + email);
        }

        UserProfile profile = user.getUserProfile();

        UserDetailsDTO dto = new UserDetailsDTO();
        dto.setName(profile.getName());
        dto.setCourse(profile.getCourse());
        dto.setSscInstitute(profile.getSscInstitute());
        dto.setSscMarks(profile.getSscMarks());
        dto.setInterInstitute(profile.getInterInstitute());
        dto.setInterMarks(profile.getInterMarks());
        dto.setGraduationInstitute(profile.getGraduationInstitute());
       // dto.setGraduationMarks(profile.getGraduationMarks());
        dto.setInternshipExperiences(profile.getInternshipExperiences());
        dto.setDescription(profile.getDescription());
//        dto.setImg(profile.getImg());
//        dto.setResume(profile.getResume());
        dto.setEmail(profile.getEmail());
        dto.setPhone(profile.getPhone());

        return dto;
    }
}

