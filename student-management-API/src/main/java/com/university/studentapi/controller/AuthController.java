package com.university.studentapi.controller;

import com.university.studentapi.dto.LoginRequest;
import com.university.studentapi.dto.RegisterRequest;
import com.university.studentapi.entity.AppUser;
import com.university.studentapi.repository.AppUserRepository;
import com.university.studentapi.security.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Lesson 3 Iteration 6: AuthController handles register/login/me.
 *
 * Endpoints:
 * - POST /api/auth/register
 * - POST /api/auth/login
 * - GET /api/auth/me
 *
 * Flow:
 * 1. Register stores a BCrypt password hash in DB.
 * 2. Login verifies the hash and generates JWT with user role.
 * 3. JWT filter reads role and builds Spring authorities.
 * 4. /me reads authenticated user info from DB.
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
         * POST /api/auth/register
         * Creates a new user account with USER role by default.
         */
        @PostMapping("/register")
        public ResponseEntity<Map<String, String>> register(@Valid @RequestBody RegisterRequest registerRequest) {
        AppUser existing = appUserRepository.findByEmail(registerRequest.email()).orElse(null);
        if (existing != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                "error", "Email is already registered"
            ));
        }

        AppUser saved = appUserRepository.save(new AppUser(
            null,
            registerRequest.email(),
            passwordEncoder.encode(registerRequest.password()),
            "USER"
        ));

        String token = jwtTokenProvider.generateToken(saved.getId(), saved.getEmail(), saved.getRole());
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
            "token", token,
            "email", saved.getEmail(),
            "role", saved.getRole()
        ));
        }

    /**
    * POST /api/auth/login
    * Returns JWT token for authenticated user.
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
        String token = jwtTokenProvider.generateToken(user.getId(), user.getEmail(), user.getRole());

        return ResponseEntity.ok(Map.of(
                "token", token,
            "email", user.getEmail(),
            "role", user.getRole()
        ));
    }

        /**
         * GET /api/auth/me
         * Returns current authenticated user details from database.
         */
        @GetMapping("/me")
        public ResponseEntity<Map<String, String>> me(Authentication authentication) {
        if (authentication == null || authentication.getName() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                "error", "Not authenticated"
            ));
        }

        AppUser user = appUserRepository.findByEmail(authentication.getName()).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "error", "Authenticated user not found"
            ));
        }

        return ResponseEntity.ok(Map.of(
            "id", String.valueOf(user.getId()),
            "email", user.getEmail(),
            "role", user.getRole()
        ));
        }
}
