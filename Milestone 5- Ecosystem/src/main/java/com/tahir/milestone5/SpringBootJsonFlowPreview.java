package com.tahir.milestone5;

public class SpringBootJsonFlowPreview {

    public static void main(String[] args) {
        // In real Spring Boot code, Jackson conversion is automatic.
        // You return StudentDTO from controller -> Spring sends JSON response.
        // Client sends JSON request body -> Spring gives you StudentDTO object.
        // This is the bridge between your Java code and frontend/mobile apps.

        System.out.println("Spring Boot JSON flow preview is documented in comments.");
    }

    // Real Spring style (preview only, kept as comments to avoid extra dependencies here):
    //
    // @RestController
    // @RequestMapping("/students")
    // public class StudentController {
    //
    //     @GetMapping("/{id}")
    //     public StudentDTO getStudent(@PathVariable Long id) {
    //         return new StudentDTO("Ali", 20, 3.9, List.of("Math"));
    //     }
    //
    //     @PostMapping
    //     public String createStudent(@RequestBody StudentDTO student) {
    //         return "Student created: " + student.getName();
    //     }
    // }
}
