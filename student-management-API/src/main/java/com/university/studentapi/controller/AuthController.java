package com.university.studentapi.controller;

import com.university.studentapi.dto.LoginRequest;
import com.university.studentapi.security.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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

    public AuthController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
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
        // Generate token (valid for 24 hours)
        String token = jwtTokenProvider.generateToken(loginRequest.email());

        return ResponseEntity.ok(Map.of(
                "token", token,
                "email", loginRequest.email()
        ));
    }
}
