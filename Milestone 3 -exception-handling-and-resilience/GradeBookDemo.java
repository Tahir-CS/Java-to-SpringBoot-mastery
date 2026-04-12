public class GradeBookDemo {

    public static void main(String[] args) {
        GradeBook gradeBook = new GradeBook();

        // Happy path: valid students and valid grades.
        try {
            gradeBook.addGrade("S001", 95.0);
            gradeBook.addGrade("S002", 84.5);
            gradeBook.addGrade("S003", 71.0);

            System.out.println("S001 score: " + gradeBook.getGrade("S001"));
            System.out.println("S001 letter: " + gradeBook.getLetterGrade("S001"));

            System.out.println("S002 score: " + gradeBook.getGrade("S002"));
            System.out.println("S002 letter: " + gradeBook.getLetterGrade("S002"));

        } catch (InvalidGradeException | StudentNotFoundException | IllegalArgumentException e) {
            System.out.println("Unexpected error during happy path: " + e.getMessage());
        }

        System.out.println("---");

        // Exception path 1: invalid grade.
        try {
            gradeBook.addGrade("S004", 150.0);
        } catch (InvalidGradeException e) {
            System.out.println("InvalidGradeException caught: " + e.getMessage());
        }

        // Exception path 2: student ID not found.
        try {
            System.out.println("S999 score: " + gradeBook.getGrade("S999"));
        } catch (StudentNotFoundException e) {
            System.out.println("StudentNotFoundException caught: " + e.getMessage());
        }

        // Exception path 3: blank student ID.
        try {
            gradeBook.addGrade(" ", 88.0);
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException caught: " + e.getMessage());
        }
    }
}
