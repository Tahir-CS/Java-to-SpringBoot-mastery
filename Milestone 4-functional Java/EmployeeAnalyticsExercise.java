import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class EmployeeAnalyticsExercise {

    // Practice record for employee data.
    record Employee(String name, String department, double salary) {
    }

    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("Ali", "Engineering", 90000),
                new Employee("Sara", "Engineering", 76000),
                new Employee("Ahmed", "Marketing", 65000),
                new Employee("Zara", "Marketing", 72000),
                new Employee("Bob", "HR", 58000),
                new Employee("Mina", "Engineering", 68000),
                new Employee("Hassan", "Finance", 81000)
        );

        // 1) Engineering employees with salary above 70000.
        List<Employee> engineeringAbove70k = employees.stream()
                .filter(e -> e.department().equals("Engineering"))
                .filter(e -> e.salary() > 70000)
                .toList();

        // 2) Average salary across all employees.
        double averageSalary = employees.stream()
                .mapToDouble(Employee::salary)
                .average()
                .orElse(0.0);

        // 3) Highest paid employee with Optional.
        Optional<Employee> highestPaid = employees.stream()
                .max(Comparator.comparingDouble(Employee::salary));

        // 4) All unique department names.
        List<String> uniqueDepartments = employees.stream()
                .map(Employee::department)
                .distinct()
                .toList();

        // 5) Total salary bill for Marketing only.
        double marketingSalaryBill = employees.stream()
                .filter(e -> e.department().equals("Marketing"))
                .mapToDouble(Employee::salary)
                .sum();

        System.out.println("Engineering > 70000: " + engineeringAbove70k);
        System.out.println("Average salary: " + averageSalary);
        highestPaid.ifPresent(e -> System.out.println("Highest paid: " + e));
        System.out.println("Unique departments: " + uniqueDepartments);
        System.out.println("Marketing salary bill: " + marketingSalaryBill);
    }
}
