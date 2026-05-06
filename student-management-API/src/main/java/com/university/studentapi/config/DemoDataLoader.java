package com.university.studentapi.config;

import com.university.studentapi.entity.AppUser;
import com.university.studentapi.entity.Student;
import com.university.studentapi.repository.AppUserRepository;
import com.university.studentapi.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * DemoDataLoader seeds a few students at startup.
 *
 * Why this exists:
 * - Postman tests are easier when GET /api/students returns real data.
 * - H2 resets on every app restart, so we load demo rows automatically.
 * - We also seed one demo user so login is backed by the database.
 * - This is only for learning/dev; production apps usually use migrations or fixtures.
 */
@Component
public class DemoDataLoader implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public DemoDataLoader(StudentRepository studentRepository,
                          AppUserRepository appUserRepository,
                          PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (studentRepository.count() > 0) {
            // Keep running so we can still seed auth users even if students already exist.
        }

        if (studentRepository.count() == 0) {
            studentRepository.save(new Student(null, "Ali Khan", "ali.khan@example.com", "Computer Science", 3));
            studentRepository.save(new Student(null, "Ayesha Noor", "ayesha.noor@example.com", "Software Engineering", 5));
            studentRepository.save(new Student(null, "Hassan Raza", "hassan.raza@example.com", "Information Systems", 2));
        }

        if (appUserRepository.count() == 0) {
            // USER account for normal access.
            appUserRepository.save(new AppUser(
                    null,
                    "ali@example.com",
                    passwordEncoder.encode("password123"),
                    "USER"
            ));

            // ADMIN account for protected write operations.
            appUserRepository.save(new AppUser(
                    null,
                    "admin@example.com",
                    passwordEncoder.encode("admin12345"),
                    "ADMIN"
            ));
        }
    }
}
