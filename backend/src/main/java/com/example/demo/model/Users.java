
package com.example.demo.model;

// JPA and Hibernate imports

import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;

import com.fasterxml.jackson.annotation.JsonIgnore;



	@Entity
	@Table(name = "users")
	public class Users {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Column(name = "fullName")
	    private String fullName;

	    @Column(name = "EMAIL", nullable = false, unique = true)
	    private String email;

	    @Column(name = "PASSWORD", nullable = false)
	    private String password;

	    @Column(name = "USER_TYPE", nullable = false)	
	    @Enumerated(EnumType.STRING)
	    private Role role;

	    // ✅ Add this field to link UserProfile
	    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    @JsonIgnore // prevent infinite recursion if you're returning this in APIs
	    private UserProfile userProfile;

	    // Getters and setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getFullName() {
	        return fullName;
	    }

	    public void setFullName(String fullName) {
	        this.fullName = fullName;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public Role getUserType() {
	        return role;
	    }

	    public void setUserType(Role role) {
	        this.role = role;
	    }

	    public UserProfile getUserProfile() {
	        return userProfile;
	    }

	    public void setUserProfile(UserProfile userProfile) {
	        this.userProfile = userProfile;
	    }
	}




