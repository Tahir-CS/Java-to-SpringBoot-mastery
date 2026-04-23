package com.university.studentapi.exception;

import java.time.LocalDateTime;

/**
 * Standard error payload sent to API clients.
 *
 * Using a record keeps this immutable and concise.
 */
public record ErrorResponse(
        int status,
        String message,
        LocalDateTime timestamp
) {
}
