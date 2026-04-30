package com.university.studentapi.dto;

import com.university.studentapi.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * LESSON 3: Authentication DTOs
 *
 * Used for login and registration endpoints
 */

/**
 * Login Request DTO
 * Sent by client during login
 */
public record LoginRequest(
        @NotBlank(message = "Username is required")
        String username,

        @NotBlank(message = "Password is required")
        String password
) {
}

// File continues below...
