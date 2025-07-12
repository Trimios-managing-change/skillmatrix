package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "header")
public class Header {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "updates", length = 500)
    private String updates;

    @Column(name = "news", length = 500)
    private String news;

    // Relationships
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_verification_id", referencedColumnName = "id")
    private SkillVerification skillVerification; // Fetch feedback from here

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id", referencedColumnName = "id")
    private UserProfile userProfile; // Fetch userImg and username from here

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUpdates() {
        return updates;
    }

    public void setUpdates(String updates) {
        this.updates = updates;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public SkillVerification getSkillVerification() {
        return skillVerification;
    }

    public void setSkillVerification(SkillVerification skillVerification) {
        this.skillVerification = skillVerification;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
