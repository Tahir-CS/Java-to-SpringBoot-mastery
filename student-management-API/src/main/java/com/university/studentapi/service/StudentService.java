package com.university.studentapi.service;

import com.university.studentapi.entity.Student;
import com.university.studentapi.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * StudentService holds the business logic.
 *
 * For Lesson 1, we keep a simple in-memory store so you can understand the flow
 * before moving to real database CRUD later.
 */
@Service
public class StudentService {

    /**
     * In-memory storage.
     * LinkedHashMap keeps insertion order, which makes the output predictable.
     */
    private final Map<Long, Student> studentStore = new LinkedHashMap<>();

    /**
     * Simple ID generator for the in-memory version.
     */
    private final AtomicLong idCounter = new AtomicLong(0);

    public StudentService(StudentRepository studentRepository) {
        // The repository is injected so the project already has the JPA layer ready.
        // We are not using it yet in this lesson, but the wiring is already in place.

        Student first = new Student(idCounter.incrementAndGet(), "Ali", "ali@example.com", "Computer Science", 2);
        Student second = new Student(idCounter.incrementAndGet(), "Sara", "sara@example.com", "Software Engineering", 3);

        studentStore.put(first.getId(), first);
        studentStore.put(second.getId(), second);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(studentStore.values());
    }

    public Student getStudentById(Long id) {
        Student student = studentStore.get(id);
        if (student == null) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        return student;
    }

    public Student createStudent(Student student) {
        Long newId = idCounter.incrementAndGet();
        Student newStudent = new Student(
                newId,
                student.getName(),
                student.getEmail(),
                student.getMajor(),
                student.getSemester()
        );
        studentStore.put(newId, newStudent);
        return newStudent;
    }

    public Student updateStudent(Long id, Student student) {
        Student existing = getStudentById(id);
        existing.setName(student.getName());
        existing.setEmail(student.getEmail());
        existing.setMajor(student.getMajor());
        existing.setSemester(student.getSemester());
        return existing;
    }

    public void deleteStudent(Long id) {
        if (!studentStore.containsKey(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        studentStore.remove(id);
    }
}
