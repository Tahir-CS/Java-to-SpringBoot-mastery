package com.tahir.springbootfirstusage.service;

import com.tahir.springbootfirstusage.exception.StudentNotFoundException;
import com.tahir.springbootfirstusage.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StudentService {

    // In-memory store for beginner demo (later you can replace with database).
    private final Map<Long, Student> studentStore = new LinkedHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(0);

    public StudentService() {
        // Preload small demo data so GET endpoint has output from start.
        Student s1 = new Student(idCounter.incrementAndGet(), "Ali", "ali@example.com");
        Student s2 = new Student(idCounter.incrementAndGet(), "Sara", "sara@example.com");
        studentStore.put(s1.getId(), s1);
        studentStore.put(s2.getId(), s2);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(studentStore.values());
    }

    public Student getStudentById(Long id) {
        Student student = studentStore.get(id);
        if (student == null) {
            throw new StudentNotFoundException("Student not found with id: " + id);
        }
        return student;
    }

    public Student createStudent(Student studentRequest) {
        // Simple beginner validation.
        if (studentRequest.getName() == null || studentRequest.getName().isBlank()) {
            throw new IllegalArgumentException("Student name is required.");
        }
        if (studentRequest.getEmail() == null || studentRequest.getEmail().isBlank()) {
            throw new IllegalArgumentException("Student email is required.");
        }

        Long newId = idCounter.incrementAndGet();
        Student newStudent = new Student(newId, studentRequest.getName(), studentRequest.getEmail());
        studentStore.put(newId, newStudent);
        return newStudent;
    }

    public void deleteStudent(Long id) {
        if (!studentStore.containsKey(id)) {
            throw new StudentNotFoundException("Student not found with id: " + id);
        }
        studentStore.remove(id);
    }
}
