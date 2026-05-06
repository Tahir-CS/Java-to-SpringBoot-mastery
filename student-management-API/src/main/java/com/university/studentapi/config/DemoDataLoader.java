package com.university.studentapi.config;

import com.university.studentapi.entity.Student;
import com.university.studentapi.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * DemoDataLoader seeds a few students at startup.
 *
 * Why this exists:
 * - Postman tests are easier when GET /api/students returns real data.
 * - H2 resets on every app restart, so we load demo rows automatically.
 * - This is only for learning/dev; production apps usually use migrations or fixtures.
 */
@Component
public class DemoDataLoader implements CommandLineRunner {

    private final StudentRepository studentRepository;

    public DemoDataLoader(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) {
        if (studentRepository.count() > 0) {
            return;
        }

        studentRepository.save(new Student(null, "Ali Khan", "ali.khan@example.com", "Computer Science", 3));
        studentRepository.save(new Student(null, "Ayesha Noor", "ayesha.noor@example.com", "Software Engineering", 5));
        studentRepository.save(new Student(null, "Hassan Raza", "hassan.raza@example.com", "Information Systems", 2));
    }
}
