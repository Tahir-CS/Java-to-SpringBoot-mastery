package com.university.studentapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * Lesson 3: Spring Security Configuration (Iteration 2).
 *
 * Now we allow /api/auth/login without authentication.
 * All other endpoints will require JWT token in next iteration.
 *
 * Current flow:
 * - /api/auth/login → PUBLIC (no auth needed, returns token)
 * - /api/students/* → PUBLIC (for now, will be protected in iteration 3)
 */
@Configuration
public class SecurityConfig {

    /**
     * BCryptPasswordEncoder: Hashes passwords with salt.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Disable Spring Security's default form login.
     * We're using JWT tokens instead.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF (we use tokens, not cookies)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // Login is public
                        .anyRequest().permitAll() // Allow all other endpoints for now
                )
                .httpBasic().disable() // No basic auth
                .formLogin().disable(); // No form login

        return http.build();
    }
}
