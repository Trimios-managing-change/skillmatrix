package com.example.demo.model;

public enum Role {
    
    STUDENT("STUDENT");
	
	
	 
    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
