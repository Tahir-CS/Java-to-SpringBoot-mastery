package com.university.studentapi.dto;

/**
 * StudentResponse is the output contract for API responses.
 *
 * We return only fields that the client should see.
 */
public record StudentResponse(
        Long id,
        String name,
        String email,
        String major,
        int semester
) {
}
