import java.util.HashMap;

public class HashMapDemo {
    
    public static void main(String[] args) {
        
        // HashMap: Key-Value pairs like a dictionary
        // Keys are UNIQUE, super fast O(1) lookups
        // If key already exists, new value OVERWRITES old value
        HashMap<String, Integer> studentGrades = new HashMap<>();
        
        // PUT: Add or update key-value pairs
        studentGrades.put("Ali", 95);
        studentGrades.put("Sara", 88);
        studentGrades.put("Ahmed", 76);
        studentGrades.put("Sara", 92);  // Overwrites previous Sara entry
        
        // GET: Retrieve value by key (O(1) - instant!)
        int aliGrade = studentGrades.get("Ali");           // 95
        Integer saraGrade = studentGrades.get("Sara");     // 92
        Integer unknownGrade = studentGrades.get("Nobody"); // null
        
        // Check existence
        boolean hasAhmed = studentGrades.containsKey("Ahmed");    // true
        boolean has95 = studentGrades.containsValue(95);           // true
        
        // Get size
        int totalEntries = studentGrades.size();  // 3, not 4
        
        // Remove entry
        studentGrades.remove("Ahmed");
        
        // Loop through all key-value pairs (PREFERRED method)
        for (HashMap.Entry<String, Integer> entry : studentGrades.entrySet()) {
            String name = entry.getKey();
            Integer grade = entry.getValue();
            System.out.println(name + " → " + grade);
        }
        
        // Alternative: Loop through keys only
        for (String name : studentGrades.keySet()) {
            int grade = studentGrades.get(name);
            System.out.println(name + ": " + grade);
        }
    }
}

// Example: HashMap with Custom Objects (Real World Usage)
class HashMapWithObjects {
    public static void main(String[] args) {
        
        // Key = Student ID (String), Value = Student object
        HashMap<String, StudentObj> database = new HashMap<>();
        
        // Add student objects
        database.put("S001", new StudentObj("Ali", 20, 3.8));
        database.put("S002", new StudentObj("Sara", 21, 3.9));
        database.put("S003", new StudentObj("Ahmed", 19, 3.5));
        
        // Fast lookup by ID
        StudentObj student = database.get("S002");
        System.out.println(student);
        
        // Loop all students
        for (HashMap.Entry<String, StudentObj> entry : database.entrySet()) {
            System.out.println("ID: " + entry.getKey() + " → " + entry.getValue());
        }
    }
}

class StudentObj {
    String name;
    int age;
    double gpa;
    
    public StudentObj(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }
    
    @Override
    public String toString() {
        return name + " | Age: " + age + " | GPA: " + gpa;
    }
}
