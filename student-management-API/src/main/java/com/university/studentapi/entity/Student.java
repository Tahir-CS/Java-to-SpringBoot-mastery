package com.university.studentapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Student is our database model.
 *
 * Spring Boot + JPA reads this class and maps it to a table.
 * Think of this as the Java version of a schema definition.
 */
@Entity
@Table(name = "students")
public class Student {

    /**
     * Primary key.
     *
     * Long is used instead of int because the ID is created by the database.
     * Before saving, it is null.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String major;
    private int semester;

    /**
     * JPA needs a no-args constructor so it can create objects by reflection.
     */
    public Student() {
    }

    /**
     * Convenience constructor for creating Student objects in code.
     */
    public Student(Long id, String name, String email, String major, int semester) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.major = major;
        this.semester = semester;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
