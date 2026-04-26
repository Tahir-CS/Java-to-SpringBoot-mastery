package com.university.studentapi.repository;

import com.university.studentapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    /**
     * Case-insensitive partial search by name.
     */
    List<Student> findByNameContainingIgnoreCase(String namePart);

    /**
     * JPQL query: returns students from a minimum semester onward.
     */
    @Query("SELECT s FROM Student s WHERE s.semester >= :semester ORDER BY s.name ASC")
    List<Student> findFromSemesterOnwards(@Param("semester") int semester);

    /**
     * JPQL query: filter by major within a semester range.
     */
    @Query("SELECT s FROM Student s WHERE s.major = :major AND s.semester BETWEEN :from AND :to ORDER BY s.semester ASC")
    List<Student> findByMajorAndSemesterRange(
            @Param("major") String major,
            @Param("from") int from,
            @Param("to") int to
    );
}
