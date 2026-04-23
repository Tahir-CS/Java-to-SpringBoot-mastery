package com.university.studentapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * StudentRequest is the input contract for POST/PUT APIs.
 *
 * Why DTO?
 * - We do not expose our entity directly at the API boundary.
 * - We validate incoming data at the edge (controller level).
 * - We keep API shape independent from DB shape.
 */
public record StudentRequest(
        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Email must be valid")
        String email,

        @NotBlank(message = "Major cannot be blank")
        String major,

        @Min(value = 1, message = "Semester must be at least 1")
        @Max(value = 8, message = "Semester cannot exceed 8")
        int semester
) {
}
