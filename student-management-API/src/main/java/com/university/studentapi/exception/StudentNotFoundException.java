package com.university.studentapi.exception;

/**
 * Thrown when a student lookup by id fails.
 *
 * Extends RuntimeException (unchecked) so service methods stay clean.
 */
public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(Long id) {
        super("Student not found with id: " + id);
    }
}
