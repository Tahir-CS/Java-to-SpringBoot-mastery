package com.university.studentapi.exception;

/**
 * Thrown when a create/update operation tries to use an email that already exists.
 */
public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String email) {
        super("A student with email '" + email + "' already exists.");
    }
}
