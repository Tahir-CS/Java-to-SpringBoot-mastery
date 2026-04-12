public class SpringExceptionHandlerPreview {

    public static void main(String[] args) {
        // This file is just a learning preview.
        // In real Spring Boot, you use @RestControllerAdvice and @ExceptionHandler.

        System.out.println("In Spring Boot, global exception handling is centralized.");
        System.out.println("You throw custom exceptions in service layer,");
        System.out.println("and a global handler maps them to clean HTTP responses.");
    }

    // Example shape in Spring Boot:
    //
    // @RestControllerAdvice
    // public class GlobalExceptionHandler {
    //
    //     @ExceptionHandler(UserNotFoundException.class)
    //     public ResponseEntity<String> handleUserNotFound(UserNotFoundException e) {
    //         return ResponseEntity.status(404).body(e.getMessage());
    //     }
    //
    //     @ExceptionHandler(InvalidGradeException.class)
    //     public ResponseEntity<String> handleInvalidGrade(InvalidGradeException e) {
    //         return ResponseEntity.status(400).body(e.getMessage());
    //     }
    // }
}
