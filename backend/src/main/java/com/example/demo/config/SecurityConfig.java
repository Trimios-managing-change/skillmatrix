package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtFilter jwtFilter;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http

				.cors(Customizer.withDefaults())
                 
				.csrf(AbstractHttpConfigurer::disable) // Disable CSRF (for stateless applications)
			    .authorizeHttpRequests(request -> request
			        .anyRequest().permitAll() // Grant access to all requests
			    )

	                    // Public endpoints accessible to everyone
	                   // .requestMatchers("/register", "/login","/auth/forgot-password").permitAll()
	                    //.requestMatchers("/student/**").hasRole("STUDENT")
	                    
	                    
	                    // HR-specific endpoints accessible only to HR role
	                   // .anyRequest().authenticated())
	            .httpBasic(Customizer.withDefaults()) // Enable HTTP Basic authentication
	            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Add JWT filter
	            .build();
	            // Stateless session
	            //.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Add JWT filter
	            
    }
	@Bean
	public AuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	    provider.setPasswordEncoder(new BCryptPasswordEncoder(10)); // Plain-text password
	    provider.setUserDetailsService(userDetailsService);
	    return provider;
	}
	
	@Bean 
	public AuthenticationManager  authenticationManager(AuthenticationConfiguration config) throws Exception
	{
		return config.getAuthenticationManager();
	}


}
