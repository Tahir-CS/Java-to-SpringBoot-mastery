import java.util.ArrayList;

public class ArrayListDemo {
    
    public static void main(String[] args) {
        
        // ArrayList: Dynamic array that grows/shrinks automatically
        // Maintains insertion order, allows duplicates, index-based access
        ArrayList<String> students = new ArrayList<>();
        
        // Adding elements
        students.add("Ali");
        students.add("Sara");
        students.add("Ahmed");
        students.add("Sara");  // Duplicates are allowed
        
        // Accessing elements by index
        String firstStudent = students.get(0);  // Ali
        String lastStudent = students.get(students.size() - 1);  // Sara
        
        // Check existence
        boolean hasAhmed = students.contains("Ahmed");  // true
        boolean hasZara = students.contains("Zara");    // false
        
        // Remove by value or by index
        students.remove("Ahmed");  // Remove by value
        students.remove(0);         // Remove by index
        
        // Update element at index
        students.set(0, "Zara");
        
        // Loop through ArrayList
        for (String student : students) {
            System.out.println(student);
        }
        
        // Loop with index
        for (int i = 0; i < students.size(); i++) {
            System.out.println(i + ": " + students.get(i));
        }
    }
}
