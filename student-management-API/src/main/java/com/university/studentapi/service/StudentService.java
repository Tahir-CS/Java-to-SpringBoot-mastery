package com.university.studentapi.service;

import com.university.studentapi.dto.StudentRequest;
import com.university.studentapi.dto.StudentResponse;
import com.university.studentapi.entity.Student;
import com.university.studentapi.exception.DuplicateEmailException;
import com.university.studentapi.exception.StudentNotFoundException;
import com.university.studentapi.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * StudentService holds business logic and coordinates repository access.
 *
 * Lesson 2 moves from in-memory data to repository-backed CRUD,
 * adds DTO mapping, and throws custom exceptions.
 */
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Converts a Student entity to an API response DTO.
     */
    private StudentResponse toResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getMajor(),
                student.getSemester()
        );
    }

    /**
     * Read-only transaction is cheaper than a read-write one.
     */
    @Transactional(readOnly = true)
    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public StudentResponse getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return toResponse(student);
    }

    @Transactional
    public StudentResponse createStudent(StudentRequest request) {
        if (studentRepository.findByEmail(request.email()).isPresent()) {
            throw new DuplicateEmailException(request.email());
        }

        Student student = new Student(
                null,
                request.name(),
                request.email(),
                request.major(),
                request.semester()
        );

        Student saved = studentRepository.save(student);
        return toResponse(saved);
    }

    @Transactional
    public StudentResponse updateStudent(Long id, StudentRequest request) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        // Duplicate check is skipped for same owner email and blocked for others.
        studentRepository.findByEmail(request.email())
                .filter(student -> !student.getId().equals(id))
                .ifPresent(student -> {
                    throw new DuplicateEmailException(request.email());
                });

        existing.setName(request.name());
        existing.setEmail(request.email());
        existing.setMajor(request.major());
        existing.setSemester(request.semester());

        Student updated = studentRepository.save(existing);
        return toResponse(updated);
    }

    @Transactional
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        studentRepository.deleteById(id);
    }

    /**
     * Search students by a partial name, case-insensitive.
     */
    @Transactional(readOnly = true)
    public List<StudentResponse> searchByName(String namePart) {
        return studentRepository.findByNameContainingIgnoreCase(namePart)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    /**
     * Returns students whose semester is greater than or equal to the given value.
     */
    @Transactional(readOnly = true)
    public List<StudentResponse> getStudentsFromSemester(int semester) {
        return studentRepository.findFromSemesterOnwards(semester)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    /**
     * Combined filter: major + semester range.
     */
    @Transactional(readOnly = true)
    public List<StudentResponse> getByMajorAndSemesterRange(String major, int from, int to) {
        return studentRepository.findByMajorAndSemesterRange(major, from, to)
                .stream()
                .map(this::toResponse)
                .toList();
    }
}
