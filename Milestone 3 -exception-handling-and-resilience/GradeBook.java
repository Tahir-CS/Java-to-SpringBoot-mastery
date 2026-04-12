import java.util.HashMap;
import java.util.Map;

public class GradeBook {
    private final Map<String, Double> grades = new HashMap<>();

    public void addGrade(String studentId, double grade) {
        validateStudentId(studentId);

        // Business rule: grade must be 0-100.
        if (grade < 0 || grade > 100) {
            throw new InvalidGradeException("Grade must be between 0 and 100. Got: " + grade);
        }

        grades.put(studentId, grade);
    }

    public double getGrade(String studentId) {
        validateStudentId(studentId);

        Double grade = grades.get(studentId);
        if (grade == null) {
            throw new StudentNotFoundException("No student found with ID: " + studentId);
        }

        return grade;
    }

    public String getLetterGrade(String studentId) {
        double grade = getGrade(studentId);

        if (grade >= 90) return "A";
        if (grade >= 80) return "B";
        if (grade >= 70) return "C";
        return "F";
    }

    private void validateStudentId(String studentId) {
        if (studentId == null || studentId.isBlank()) {
            throw new IllegalArgumentException("Student ID cannot be empty.");
        }
    }
}
