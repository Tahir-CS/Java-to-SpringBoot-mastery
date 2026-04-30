package com.university.studentapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Lesson 3: Spring Security Configuration (Iteration 1).
 *
 * Right now this is basic setup. In next iteration we'll:
 * - Add JWT filter to validate tokens on every request
 * - Create login endpoint that returns a token
 * - Protect endpoints with @PreAuthorize
 *
 * What this does:
 * - Enables Spring Security (adds security filters to all requests)
 * - Provides BCryptPasswordEncoder (for hashing passwords securely)
 */
@Configuration
public class SecurityConfig {

    /**
     * BCryptPasswordEncoder: Hashes passwords with salt.
     * Even if two users have password "123", their hashes are different.
     * This makes rainbow table attacks impossible.
     *
     * Example:
     * "password" → $2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcg7b3XeKeUxWdeS86E36ajwFUm
     * "password" → $2a$10$abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUV (different!)
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
