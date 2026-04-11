import java.util.HashSet;

public class HashSetDemo {
    
    public static void main(String[] args) {
        
        // HashSet: Collection that NEVER has duplicates
        // No guaranteed order, extremely fast contains() checks
        HashSet<String> enrolledStudents = new HashSet<>();
        
        // Add elements (duplicates are silently ignored)
        enrolledStudents.add("Ali");
        enrolledStudents.add("Sara");
        enrolledStudents.add("Ahmed");
        enrolledStudents.add("Ali");    // Duplicate - ignored
        enrolledStudents.add("Sara");   // Duplicate - ignored
        
        // Size is 3, not 5
        int size = enrolledStudents.size();
        
        // Check existence (O(1) - super fast)
        boolean hasAli = enrolledStudents.contains("Ali");      // true
        boolean hasZara = enrolledStudents.contains("Zara");    // false
        
        // Remove
        enrolledStudents.remove("Ahmed");
        
        // Loop (order is NOT guaranteed)
        for (String student : enrolledStudents) {
            System.out.println(student);
        }
    }
}

// Real Use Case: Finding Duplicates in Array
class FindDuplicates {
    public static void main(String[] args) {
        
        int[] numbers = {1, 2, 3, 2, 4, 3, 5};
        
        // Use HashSet to track seen numbers and find duplicates
        HashSet<Integer> seen = new HashSet<>();
        HashSet<Integer> duplicates = new HashSet<>();
        
        for (int num : numbers) {
            // add() returns false if element already exists
            if (!seen.add(num)) {
                duplicates.add(num);
            }
        }
        
        System.out.println("Duplicates: " + duplicates);  // [2, 3]
    }
}

// Real World Use: Unique User Roles
class UserRoles {
    public static void main(String[] args) {
        
        // A user can have multiple roles, but each role is unique
        HashSet<String> userRoles = new HashSet<>();
        
        userRoles.add("USER");
        userRoles.add("ADMIN");
        userRoles.add("MODERATOR");
        userRoles.add("USER");      // Duplicate - ignored
        
        // Check if user has admin privilege (fast O(1) check)
        if (userRoles.contains("ADMIN")) {
            System.out.println("User has admin access");
        }
    }
}
