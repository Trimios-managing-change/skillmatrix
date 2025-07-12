package com.example.demo.model;

import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrinciples implements UserDetails {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Users user;

	public UserPrinciples(Users user) {
		
		this.user = user;
	}
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Map the Role enum to a Spring Security authority with "ROLE_" prefix
        String role = "ROLE_" + user.getUserType().name();
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }
	

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}
	
	@Override
	public boolean isAccountNonExpired() {
	    return true; // Account is not expired
	}
	@Override
	public boolean isAccountNonLocked() {
	    return true; // Account is not locked
	}
	@Override
	public boolean isCredentialsNonExpired() {
	    return true; // Credentials are valid
	}
	@Override
	public boolean isEnabled() {
	    return true; // User is enabled
	}



}
