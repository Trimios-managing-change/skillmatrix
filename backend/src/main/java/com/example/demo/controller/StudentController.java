package com.example.demo.controller;

import com.example.demo.DTO.RoleRequestDTO;
import com.example.demo.DTO.SkillAssessmentDTO;
import com.example.demo.DTO.SkillAssessmentSMEDTO;
import com.example.demo.DTO.UserDetailsDTO;
import com.example.demo.DTO.UserInfoResponse;
import com.example.demo.model.RoleEntity;
import com.example.demo.model.SkillResource;
import com.example.demo.model.UserProfile;
import com.example.demo.model.Users;
import com.example.demo.repo.SkillResourceRepository;
import com.example.demo.repo.UserProfileRepository;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.RoleService;
import com.example.demo.service.SkillAssessmentService;
import com.example.demo.service.UserProfileService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private UserProfileService profileService;
    
    
    
    @Autowired 
    private UserRepo us;
    
    @Autowired
    private RoleService roleService;

    @Autowired
    private SkillAssessmentService assessmentService; // Injecting the missing service
    
    @Autowired
    private UserProfileRepository upr;
    

    // Submit user profile details
    
    
    

    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> saveUserProfile(@RequestBody UserDetailsDTO dto) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName();

        profileService.saveUserProfile(email, dto);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Profile saved successfully.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get")
    public ResponseEntity<UserDetailsDTO> getUserProfile() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName();

        UserDetailsDTO userProfile = profileService.getUserProfile(email);
        return ResponseEntity.ok(userProfile);
    }
    
    
    @GetMapping("/home")
    public ResponseEntity<?> getUserDetails() {
        // Email is the username in your system
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName();

        Users user = us.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        UserInfoResponse response = new UserInfoResponse(user.getFullName());

        return ResponseEntity.ok(response);
    }
    
   

    // Endpoint to create a new role with associated skills
    @PostMapping
    public ResponseEntity<Map<String, Object>> createRole(@RequestBody RoleRequestDTO dto) {
        Map<String, Object> response = new HashMap<>();
        try {
            RoleEntity createdRole = roleService.createRoleWithSkills(dto);
            response.put("status", true);
            response.put("message", "Role created successfully!");
            response.put("role", createdRole);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", false);
            response.put("message", "Failed to create role: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Endpoint to fetch role skills with status based on user's email
    @GetMapping("/{roleName}")
    public ResponseEntity<Map<String, Object>> getRoleWithSkillsAndStatus(@PathVariable String roleName) {
        Map<String, Object> response = new HashMap<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || auth.getName() == null) {
            response.put("status", false);
            response.put("message", "User authentication failed.");
            return ResponseEntity.status(401).body(response);
        }

        String email = auth.getName();
        Map<String, Object> roleSkills = assessmentService.getRoleSkillsWithStatus(email, roleName);

        if (roleSkills.isEmpty()) {
            response.put("status", false);
            response.put("message", "Role or skills not found for the given role name.");
            return ResponseEntity.notFound().build();
        }

        response.put("status", true);
        response.put("message", "Role and skills fetched successfully.");
        response.put("data", roleSkills);
        return ResponseEntity.ok(response);
    }

    // Endpoint to submit a skill assessment for a specific role
    @PostMapping("/submit-assessment")
    public ResponseEntity<Map<String, Object>> submitAssessment(@RequestBody SkillAssessmentDTO dto) {
        // Get the authenticated user's email
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        // Check if the user's profile is submitted
         UserProfile is = upr.findByEmail(email);

        if (is==null) {
            // Return an error response if the profile is not submitted
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", false);
            errorResponse.put("message", "User profile must be submitted before submitting the assessment.");
            return ResponseEntity.badRequest().body(errorResponse);
        }

        // Save the assessment and fetch status
        Map<String, Object> response = assessmentService.saveAssessmentAndFetchStatus(email, dto);

        if ((boolean) response.get("status")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
    @PostMapping("/submit-to-sme")
    public ResponseEntity<Map<String, Object>> submitToSME(@RequestBody SkillAssessmentSMEDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        // Call service to save the submission
        Map<String, Object> response = assessmentService.submitToSME(email, dto);

        if ((boolean) response.get("status")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    
    @Autowired
    private SkillAssessmentService skillAssessmentService;

    @GetMapping("/unverified")
    public ResponseEntity<Map<String, Object>> getUnverifiedSkills(@RequestParam String roleName) {
    	 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         String email = auth.getName();
    	
        Map<String, Object> response = skillAssessmentService.getAllSkillsWithVerificationStatus(email, roleName);
        return ResponseEntity.ok(response);
    }


    @Autowired
    private SkillResourceRepository skillResourceRepository;

    @PostMapping("/add")
    public ResponseEntity<List<SkillResource>> addSkills(@RequestBody List<SkillResource> skillResources) {
        List<SkillResource> savedSkills = skillResourceRepository.saveAll(skillResources);
        return ResponseEntity.ok(savedSkills);

   
  
    
   

   

    
}
}
