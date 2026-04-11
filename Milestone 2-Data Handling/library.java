import java.util.*;

class books {
    String title;
    String author;
    ArrayList<String> borrowers = new ArrayList<>(); // name of borrowers

    books(String t, String a) {
        this.title = t;
        this.author = a;
    }

    @Override
    public String toString() {
        return title + " by " + author + " | Borrowers: " + borrowers;
    }
}

// non public class
class Librarymanager {
    // here we are gonna store the books in library manager class
    // for storage we are gonna use hashmap 
    HashMap<String, books> map = new HashMap<>();

    void addBOOKs(String id, books b) {
        map.put(id, b);
    }

    // Fix: Added "String name" so the method knows who is borrowing
    void borrowbook(String id, String name) {
        if (map.containsKey(id)) {
            map.get(id).borrowers.add(name);
        }
    }
    

    // now we need to find books borrowed by members name we stored in borrower array list
    // as we stored all library info in hashmap named map
    // we are running a loop through it 
    HashSet<String> findbookborrowedByName(String name) {
        HashSet<String> result = new HashSet<>();
        // we are using hashset to return set of all borrowed book.

        for (HashMap.Entry<String, books> entry : map.entrySet()) {
            books b = entry.getValue();
            
            // FIXED: This IF statement Imust be INSIDE the for-loop braces 
            // Check if our list of borrowers contains the member
            if (b.borrowers.contains(name)) {
                result.add(b.title); // Add the title to our HashSet
            }
        } // Loop ends here
        return result;
    }

    // Added a display method to see the results
    void displayAll() {
        for (books b : map.values()) {
            System.out.println(b);
        }
    }
}

public class library {
    public static void main(String args[]) {
        Librarymanager lib = new Librarymanager();

        // 1. Add 3 books
        lib.addBOOKs("B001", new books("Clean Code", "Robert Martin"));
        lib.addBOOKs("B002", new books("Design Patterns", "GoF"));
        lib.addBOOKs("B003", new books("Java Concurrency", "Brian Goetz"));

        // 2. Borrow books
        lib.borrowbook("B001", "Ali");
        lib.borrowbook("B001", "Sara");
        lib.borrowbook("B002", "Ali");
        lib.borrowbook("B003", "Ahmed");

        // 3. Results
        System.out.println("--- Library Status ---");
        lib.displayAll();

        System.out.println("\nBooks borrowed by Ali: " + lib.findbookborrowedByName("Ali"));
    }
}