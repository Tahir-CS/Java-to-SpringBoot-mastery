import java.util.HashMap;

class Student {
    String name;
    int age;
    double gpa;

    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return name + " | Age: " + age + " | GPA: " + gpa;
    }
}

public class Main {
    public static void main(String[] args) {

        // Key = student ID (String), Value = full Student object
        HashMap<String, Student> database = new HashMap<>();

        database.put("S001", new Student("Ali",   20, 3.8));
        database.put("S002", new Student("Sara",  21, 3.9));
        database.put("S003", new Student("Ahmed", 19, 3.5));

        // Look up a student by their ID instantly
        Student found = database.get("S002");
        System.out.println(found);  // Sara | Age: 21 | GPA: 3.9

        // Loop all students
        for (HashMap.Entry<String, Student> entry : database.entrySet()) {
            System.out.println("ID: " + entry.getKey() + " → " + entry.getValue());
        }
    }
}