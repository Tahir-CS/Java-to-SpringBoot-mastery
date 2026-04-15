package com.tahir.milestone5;

import java.util.List;

// DTO = Data Transfer Object used for incoming/outgoing API payloads.
public class StudentDTO {
    private String name;
    private int age;
    private double gpa;
    private List<String> courses;

    // Jackson needs no-args constructor for deserialization.
    public StudentDTO() {
    }

    public StudentDTO(String name, int age, double gpa, List<String> courses) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "StudentDTO{name='" + name + "', age=" + age + ", gpa=" + gpa + ", courses=" + courses + "}";
    }
}
