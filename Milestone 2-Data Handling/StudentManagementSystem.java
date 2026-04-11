import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class StudentManagementSystem {
    
    // Database components
    private ArrayList<Student> allStudents;
    private HashMap<String, Double> studentGrades;
    private HashSet<String> courseCodes;
    
    public StudentManagementSystem() {
        allStudents = new ArrayList<>();
        studentGrades = new HashMap<>();
        courseCodes = new HashSet<>();
    }
    
    // Add a student to the system
    public void addStudent(Student student) {
        allStudents.add(student);
    }
    
    // Record a grade for a student
    public void recordGrade(String studentId, double grade) {
        studentGrades.put(studentId, grade);
    }
    
    // Register a course code (no duplicates)
    public void registerCourse(String courseCode) {
        courseCodes.add(courseCode);
    }
    
    // Get student grade by ID
    public Double getGrade(String studentId) {
        return studentGrades.get(studentId);
    }
    
    // Check if course exists
    public boolean isCourseRegistered(String courseCode) {
        return courseCodes.contains(courseCode);
    }
    
    // Find student by name (loop through ArrayList)
    public Student findStudentByName(String name) {
        for (Student student : allStudents) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }
    
    // Get all students
    public ArrayList<Student> getAllStudents() {
        return allStudents;
    }
    
    // Get all registered courses
    public HashSet<String> getAllCourses() {
        return courseCodes;
    }
    
    // Find student with highest grade
    public Student getTopPerformer() {
        if (allStudents.isEmpty()) return null;
        
        Student topStudent = allStudents.get(0);
        double topGrade = studentGrades.get(topStudent.getId());
        
        for (Student student : allStudents) {
            double grade = studentGrades.getOrDefault(student.getId(), 0.0);
            if (grade > topGrade) {
                topGrade = grade;
                topStudent = student;
            }
        }
        return topStudent;
    }
    
    public static void main(String[] args) {
        
        StudentManagementSystem system = new StudentManagementSystem();
        
        // Add students
        system.addStudent(new Student("S001", "Ali", 20));
        system.addStudent(new Student("S002", "Sara", 21));
        system.addStudent(new Student("S003", "Ahmed", 19));
        
        // Record grades
        system.recordGrade("S001", 95.5);
        system.recordGrade("S002", 88.0);
        system.recordGrade("S003", 92.5);
        
        // Register courses
        system.registerCourse("CS101");
        system.registerCourse("CS102");
        system.registerCourse("MATH201");
        system.registerCourse("CS101");  // Duplicate - ignored
        
        // Display all students with grades
        System.out.println("=== All Students ===");
        for (Student student : system.getAllStudents()) {
            Double grade = system.getGrade(student.getId());
            System.out.println(student + " → Grade: " + grade);
        }
        
        // Search student
        System.out.println("\n=== Search Student ===");
        Student found = system.findStudentByName("Sara");
        if (found != null) {
            System.out.println("Found: " + found);
        }
        
        // Top performer
        System.out.println("\n=== Top Performer ===");
        Student top = system.getTopPerformer();
        System.out.println(top + " → Grade: " + system.getGrade(top.getId()));
        
        // Display unique courses
        System.out.println("\n=== Registered Courses ===");
        System.out.println("Total unique courses: " + system.getAllCourses().size());
        for (String course : system.getAllCourses()) {
            System.out.println("  " + course);
        }
        
        // Check if course exists
        System.out.println("\n=== Course Lookup ===");
        System.out.println("CS101 registered? " + system.isCourseRegistered("CS101"));
        System.out.println("PHYSICS registered? " + system.isCourseRegistered("PHYSICS"));
    }
}

class Student {
    private String id;
    private String name;
    private int age;
    
    public Student(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return "Student{" + id + ", " + name + ", age=" + age + "}";
    }
}
