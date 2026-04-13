import java.util.List;
import java.util.Optional;

public class OptionalDemo {

    public static void main(String[] args) {
        // Creating Optional values.
        Optional<String> hasValue = Optional.of("Ali");
        Optional<String> noValue = Optional.empty();
        Optional<String> maybeNull = Optional.ofNullable(null);

        // Safe presence check.
        System.out.println("hasValue present: " + hasValue.isPresent());
        System.out.println("noValue present: " + noValue.isPresent());
        System.out.println("maybeNull present: " + maybeNull.isPresent());

        if (hasValue.isPresent()) {
            System.out.println("Value: " + hasValue.get());
        }

        // Fallback default.
        String withDefault = noValue.orElse("Default Name");
        System.out.println("orElse result: " + withDefault);

        // Throw custom message if empty.
        String required = hasValue.orElseThrow(() -> new RuntimeException("Value not found"));
        System.out.println("orElseThrow result: " + required);

        // Run logic only when present.
        hasValue.ifPresent(v -> System.out.println("Found: " + v));
        noValue.ifPresent(v -> System.out.println("This line will not run"));

        // Real usage with streams.
        List<StudentOpt> students = List.of(
                new StudentOpt("Ali", 3.9, "CS"),
                new StudentOpt("Sara", 3.5, "Math")
        );

        Optional<StudentOpt> found = students.stream()
                .filter(s -> s.name().equals("Sara"))
                .findFirst();

        found.ifPresent(s -> System.out.println("Found student: " + s));

        StudentOpt ali = students.stream()
                .filter(s -> s.name().equals("Ali"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Student not found"));

        System.out.println("Resolved student: " + ali);
    }

    record StudentOpt(String name, double gpa, String major) {
        @Override
        public String toString() {
            return name + " (" + gpa + ")";
        }
    }
}
