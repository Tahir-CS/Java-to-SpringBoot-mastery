import java.util.Comparator;
import java.util.List;

public class StreamObjectDemo {

    public static void main(String[] args) {
        List<StudentInfo> students = List.of(
                new StudentInfo("Ali", 3.9, "CS"),
                new StudentInfo("Sara", 3.5, "Math"),
                new StudentInfo("Ahmed", 2.8, "CS"),
                new StudentInfo("Zara", 3.7, "CS"),
                new StudentInfo("Bob", 3.1, "Math")
        );

        // Get all CS students with GPA above 3.5.
        List<StudentInfo> topCS = students.stream()
                .filter(s -> s.getMajor().equals("CS"))
                .filter(s -> s.getGpa() > 3.5)
                .toList();

        // Map objects to uppercased names.
        List<String> upperNames = students.stream()
                .map(s -> s.getName().toUpperCase())
                .toList();

        // Find highest GPA student.
        StudentInfo topStudent = students.stream()
                .max(Comparator.comparingDouble(StudentInfo::getGpa))
                .orElseThrow();

        // Average GPA.
        double averageGpa = students.stream()
                .mapToDouble(StudentInfo::getGpa)
                .average()
                .orElse(0.0);

        System.out.println("Top CS students: " + topCS);
        System.out.println("Upper names: " + upperNames);
        System.out.println("Top student: " + topStudent);
        System.out.println("Average GPA: " + averageGpa);
    }

    static class StudentInfo {
        private final String name;
        private final double gpa;
        private final String major;

        StudentInfo(String name, double gpa, String major) {
            this.name = name;
            this.gpa = gpa;
            this.major = major;
        }

        String getName() {
            return name;
        }

        double getGpa() {
            return gpa;
        }

        String getMajor() {
            return major;
        }

        @Override
        public String toString() {
            return name + " (" + gpa + ")";
        }
    }
}
