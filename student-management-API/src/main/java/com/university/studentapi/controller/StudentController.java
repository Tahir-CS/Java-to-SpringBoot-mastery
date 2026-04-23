package com.university.studentapi.controller;

import com.university.studentapi.dto.StudentRequest;
import com.university.studentapi.dto.StudentResponse;
import com.university.studentapi.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * StudentController handles HTTP requests.
 *
 * This is the Spring Boot version of Express routes.
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * GET /api/students
     * Returns all students.
     */
    @GetMapping
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllStudents();
    }

    /**
     * GET /api/students/{id}
     * Returns one student by ID.
     */
    @GetMapping("/{id}")
    public StudentResponse getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    /**
     * POST /api/students
     * Creates a new student.
     */
    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentRequest studentRequest) {
        StudentResponse createdStudent = studentService.createStudent(studentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    /**
     * PUT /api/students/{id}
     * Updates an existing student.
     */
    @PutMapping("/{id}")
    public StudentResponse updateStudent(@PathVariable Long id, @Valid @RequestBody StudentRequest studentRequest) {
        return studentService.updateStudent(id, studentRequest);
    }

    /**
     * DELETE /api/students/{id}
     * Deletes a student.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok(Map.of("message", "Student deleted successfully."));
    }
}
