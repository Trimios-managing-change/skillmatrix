package com.example.demo.service;

import com.example.demo.DTO.SkillAssessmentDTO;
import com.example.demo.DTO.SkillAssessmentSMEDTO;
import com.example.demo.model.RoleEntity;
import com.example.demo.model.Skill;
import com.example.demo.model.SkillAssessment;
import com.example.demo.model.SkillResource;
import com.example.demo.model.SkillsAssessmentSME;
import com.example.demo.model.Users;
import com.example.demo.repo.RoleRepository;
import com.example.demo.repo.SkillAssessmentRepository;
import com.example.demo.repo.SkillResourceRepository;
import com.example.demo.repo.SmeRepo;
import com.example.demo.repo.UserRepo;

import jakarta.transaction.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillAssessmentService {
	
	@Autowired
	private SmeRepo sr;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private SkillAssessmentRepository assessmentRepo;
    
   

    public Map<String, Object> getRoleSkillsWithStatus(String email, String roleName) {
        Map<String, Object> response = new HashMap<>();
        Users user = userRepo.findByEmail(email);
        RoleEntity role = roleRepo.findByRoleName(roleName);

        if (user == null || role == null) {
            response.put("status", false);
            response.put("message", "Invalid user or role.");
            return response;
        }

        List<Skill> roleSkills = role.getSkills();
        List<SkillAssessment> assessments = assessmentRepo.findByUserAndRole(user, role);

        List<Map<String, Object>> skillDataList = new ArrayList<>();

        for (Skill skill : roleSkills) {
            Optional<SkillAssessment> assessment = assessments.stream()
                    .filter(a -> a.getSkill().equals(skill))
                    .findFirst();

            Map<String, Object> skillData = new HashMap<>();
            skillData.put("skillName", skill.getSkillName());

            if (assessment.isPresent()) {
                SkillAssessment skillAssessment = assessment.get();
                skillData.put("status", skillAssessment.isVerified() ? "Verified" : "Verifying");
                skillData.put("level", skillAssessment.getLevel());
            } else {
                skillData.put("status", "Unverified");
                skillData.put("level", null);
            }

            skillDataList.add(skillData);
        }

//        response.put("status", true);
//        response.put("message", "Role and skills fetched successfully.");
        response.put("data", Map.of(
            "roleName", role.getRoleName(),
            "skills", skillDataList
        ));

        return response;
    }

    public Map<String, Object> saveAssessmentAndFetchStatus(String email, SkillAssessmentDTO dto) {
        Users user = userRepo.findByEmail(email);
        RoleEntity role = roleRepo.findByRoleName(dto.getRoleName());

        if (user == null || role == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", false);
            response.put("message", "Invalid user or role.");
            return response;
        }

        List<Skill> roleSkills = role.getSkills();
        Map<String, String> incomingSkills = dto.getSkillLevels();

        List<SkillAssessment> submittedAssessments = assessmentRepo.findByUserAndRole(user, role);
        Map<Skill, SkillAssessment> skillAssessmentMap = submittedAssessments.stream()
                .collect(Collectors.toMap(SkillAssessment::getSkill, assessment -> assessment));

        List<Map<String, Object>> skillStatuses = new ArrayList<>();
        boolean hasAlreadyAddedSkills = false;

        for (Map.Entry<String, String> entry : incomingSkills.entrySet()) {
            String skillName = entry.getKey();
            String level = entry.getValue();

            Skill skill = roleSkills.stream()
                    .filter(s -> s.getSkillName().equalsIgnoreCase(skillName))
                    .findFirst()
                    .orElse(null);

            if (skill != null) {
                SkillAssessment existingAssessment = skillAssessmentMap.get(skill);

                Map<String, Object> skillStatus = new HashMap<>();
                skillStatus.put("skillName", skillName);

                if (existingAssessment != null) {
                    if (existingAssessment.getLevel().equals(level)) {
                        skillStatus.put("status", "Already Submitted");
                        skillStatus.put("level", level);
                        hasAlreadyAddedSkills = true; // Track if already added skills exist
                    } else {
                        existingAssessment.setLevel(level);
                        assessmentRepo.save(existingAssessment);
                        skillStatus.put("status", "Verifying");
                        skillStatus.put("level", level);
                    }
                } else {
                    SkillAssessment newAssessment = new SkillAssessment();
                    newAssessment.setUser(user);
                    newAssessment.setRole(role);
                    newAssessment.setSkill(skill);
                    newAssessment.setLevel(level);
                    newAssessment.setVerified(false);
                    assessmentRepo.save(newAssessment);
                    skillStatus.put("status", "Verifying");
                    skillStatus.put("level", level);
                }

                skillStatuses.add(skillStatus);
            }
        }

        for (Skill skill : roleSkills) {
            if (!incomingSkills.containsKey(skill.getSkillName())) {
                SkillAssessment existingAssessment = skillAssessmentMap.get(skill);

                Map<String, Object> skillStatus = new HashMap<>();
                skillStatus.put("skillName", skill.getSkillName());

                if (existingAssessment != null) {
                    skillStatus.put("status", existingAssessment.isVerified() ? "Verified" : "Verifying");
                    skillStatus.put("level", existingAssessment.getLevel());
                } else {
                    skillStatus.put("status", "Unverified");
                    skillStatus.put("level", null);
                }

                skillStatuses.add(skillStatus);
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        if (hasAlreadyAddedSkills) {
            response.put("message", "Some skills were already added.");
        } else {
            response.put("message", "Skill assessment processed successfully.");
        }
        response.put("data", Map.of(
                "roleName", dto.getRoleName(),
                "skills", skillStatuses
        ));

        return response;
    }
    

    public Map<String, Object> submitToSME(String email, SkillAssessmentSMEDTO dto) {
        Map<String, Object> response = new HashMap<>();

        // Check if the user already submitted SME details
        if (sr.existsByUserEmailAndSubmittedToSMETrue(email)) {
            response.put("status", false);
            response.put("message", "You have already submitted your details to the SME and cannot modify them.");
            return response;
        }

        // Convert skills Map to a concatenated String
        String skillsAsString = dto.getSkills().entrySet().stream()
                .map(entry -> entry.getKey() + ":" + entry.getValue()) // Format: "Skill:Level"
                .collect(Collectors.joining(", ")); // Combine with a comma and space

        // Save the details to the database
        SkillsAssessmentSME assessment = new SkillsAssessmentSME();
        assessment.setUserEmail(email);
        assessment.setRole(dto.getRole());
        assessment.setSubmittedToSME(true);
        assessment.setStatus("Pending");
        assessment.setSkills(skillsAsString); // Set the concatenated string

        sr.save(assessment); // Save to the database

        response.put("status", true);
        response.put("message", "Details submitted successfully.");
        return response;
    }

    // Utility to parse skills back into a Map<String, String>
    public Map<String, String> parseSkills(String skills) {
        if (skills == null || skills.isEmpty()) {
            return Collections.emptyMap();
        }

        return Arrays.stream(skills.split(", ")) // Split by comma and space
                .map(skill -> skill.split(":")) // Split each "Skill:Level"
                .collect(Collectors.toMap(
                        parts -> parts[0], // Skill name
                        parts -> parts[1]  // Skill level
                ));
    }
    
    
   @Autowired
 private SkillResourceRepository skillResourceRepo;

    /**
     * Retrieves unverified skills with their associated course links for a user in a specific role.
     * 
     * @param email The email of the user
     * @param roleName The name of the role to check skills for
     * @return A map containing response status, message, and data with unverified skills
     */
   
   public Map<String, Object> getAllSkillsWithVerificationStatus(String email, String roleName) {
	    Map<String, Object> response = new HashMap<>();

	    // Find user and role
	    Users user = userRepo.findByEmail(email);
	    RoleEntity role = roleRepo.findByRoleName(roleName);

	    // Validate user and role exist
	    if (user == null || role == null) {
	        response.put("status", false);
	        response.put("message", "Invalid user or role.");
	        return response;
	    }

	    // Get all skills for the role
	    List<Skill> roleSkills = role.getSkills();
	    System.out.println("Total role skills: " + roleSkills.size());

	    // Fetch all assessments for the user and role
	    List<SkillAssessment> skillAssessments = assessmentRepo.findByUserAndRole(user, role);

	    // Map assessments by skill ID for quick lookup
	    Map<Long, SkillAssessment> assessmentMap = skillAssessments.stream()
	            .collect(Collectors.toMap(sa -> sa.getSkill().getId(), sa -> sa));

	    List<Map<String, Object>> allSkills = new ArrayList<>();

	    // Iterate over all skills and fetch details
	    for (Skill skill : roleSkills) {
	        SkillResource skillResource = skillResourceRepo.findBySkillName(skill.getSkillName());

	        String verificationStatus = "unverified"; // Default status

	        // Check if there's an assessment for this skill
	        if (assessmentMap.containsKey(skill.getId())) {
	            SkillAssessment assessment = assessmentMap.get(skill.getId());
	            if (assessment.isVerified()) {
	                verificationStatus = "verified";
	            } else if (assessment.getLevel() != null && !assessment.getLevel().isEmpty()) {
	                verificationStatus = "verifying";
	            }
	        }

	        Map<String, Object> skillData = new HashMap<>();
	        skillData.put("skillName", skill.getSkillName());
	        skillData.put("courseLink", skillResource != null ?
	                skillResource.getCourseLink() : "No course available");
	        skillData.put("verificationStatus", verificationStatus);

	        allSkills.add(skillData);
	    }

	    System.out.println("Total skills: " + allSkills.size());

	    // Build response
	    Map<String, Object> data = new HashMap<>();
	    data.put("roleName", role.getRoleName());
	    data.put("allSkills", allSkills);

	    response.put("status", true);
	    response.put("message", "Skills with verification status fetched successfully.");
	    response.put("data", data);

	    return response;
	}

    
}

    // Add a new SkillResource
  


