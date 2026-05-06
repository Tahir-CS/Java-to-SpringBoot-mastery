package com.university.studentapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Lesson 3 Iteration 2: Login Request DTO.
 *
 * Client sends email + password to login and get a JWT token.
 * The AuthController uses this to validate input and generate tokens.
 */
public record LoginRequest(
        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Email must be valid")
        String email,

        @NotBlank(message = "Password cannot be blank")
        String password
) {
}
