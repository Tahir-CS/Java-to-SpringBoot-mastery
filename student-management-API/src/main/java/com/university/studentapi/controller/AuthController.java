package com.university.studentapi.controller;

import com.university.studentapi.dto.LoginRequest;
import com.university.studentapi.entity.AppUser;
import com.university.studentapi.repository.AppUserRepository;
import com.university.studentapi.security.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Lesson 3 Iteration 2: AuthController handles login.
 *
 * This is a NEW endpoint: POST /api/auth/login
 *
 * Flow:
 * 1. Client sends email + password
 * 2. We validate the request (@Valid)
 * 3. We generate JWT token for this email
 * 4. Return token to client
 *
 * Client stores token in localStorage and sends in Authorization header
 * on future requests.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(JwtTokenProvider jwtTokenProvider,
                          AppUserRepository appUserRepository,
                          PasswordEncoder passwordEncoder) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * POST /api/auth/login
     * Returns JWT token for authenticated user.
     *
     * Note: Right now we don't verify password against database.
     * Next iteration will add proper authentication.
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginRequest loginRequest) {
        // Database-backed auth check: look up the user by email and verify the password hash.
        AppUser user = appUserRepository.findByEmail(loginRequest.email())
            .orElse(null);

        if (user == null || !passwordEncoder.matches(loginRequest.password(), user.getPasswordHash())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "error", "Invalid email or password"
            ));
        }

        // Generate token (valid for 24 hours).
        String token = jwtTokenProvider.generateToken(user.getEmail());

        return ResponseEntity.ok(Map.of(
                "token", token,
                "email", loginRequest.email()
        ));
    }
}
