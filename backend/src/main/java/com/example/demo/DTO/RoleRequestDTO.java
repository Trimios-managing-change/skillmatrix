package com.example.demo.DTO;



import java.util.List;

public class RoleRequestDTO {

    private String roleName;
    private List<String> skills; // skill names like "Linux", "AWS", etc.

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}

