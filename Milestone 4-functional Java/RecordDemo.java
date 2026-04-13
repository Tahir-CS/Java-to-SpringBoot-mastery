import java.util.List;

public class RecordDemo {

    // Record: compact immutable data class.
    record StudentRecord(String name, int age, double gpa) {
    }

    public static void main(String[] args) {
        StudentRecord s1 = new StudentRecord("Ali", 20, 3.9);
        StudentRecord s2 = new StudentRecord("Sara", 21, 3.5);

        // Accessors are auto-generated with field names.
        System.out.println(s1.name());
        System.out.println(s1.age());
        System.out.println(s1.gpa());

        // toString/equals/hashCode are auto-generated too.
        System.out.println(s1);
        System.out.println("s1 equals s2: " + s1.equals(s2));

        List<StudentRecord> students = List.of(
                new StudentRecord("Ali", 20, 3.9),
                new StudentRecord("Sara", 21, 3.5),
                new StudentRecord("Ahmed", 19, 2.8)
        );

        // Records work naturally with streams.
        List<String> goodNames = students.stream()
                .filter(s -> s.gpa() > 3.0)
                .map(StudentRecord::name)
                .toList();

        System.out.println(goodNames);
    }
}
