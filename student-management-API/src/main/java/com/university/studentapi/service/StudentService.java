package com.university.studentapi.service;

import com.university.studentapi.dto.StudentRequest;
import com.university.studentapi.dto.StudentResponse;
import com.university.studentapi.entity.Student;
import com.university.studentapi.exception.DuplicateEmailException;
import com.university.studentapi.exception.StudentNotFoundException;
import com.university.studentapi.repository.StudentRepository;
import org.springframework.stereotype.Service;

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

    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public StudentResponse getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return toResponse(student);
    }

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

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        studentRepository.deleteById(id);
    }
}
