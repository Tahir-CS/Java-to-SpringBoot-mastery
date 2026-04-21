package com.university.studentapi.repository;

import com.university.studentapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * StudentRepository is the database access layer.
 *
 * Spring Data JPA creates the implementation automatically.
 * We only define the interface.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Finds one student by email.
     * Spring turns this method name into a database query.
     */
    Optional<Student> findByEmail(String email);

    /**
     * Finds all students with the same major.
     */
    List<Student> findByMajor(String major);

    /**
     * Finds students by semester.
     */
    List<Student> findBySemester(int semester);
}
