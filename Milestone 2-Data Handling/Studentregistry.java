import java.util.*;

class Student {
    String name;
    int age;
    double gpa;
ArrayList<String> courses;
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

public class Studentregistry{
   private HashMap<String,Student> registry=new HashMap<>();
//add students
public void addStudent(String id,Student s){
    registry.put(id,s);

}
// enrollCourse — finds the student by ID, then adds course to THEIR list
    public void enrollCourse(String id, String course) {
         
    Student s= registry.getid();
    if (s != null) {
            s.courses.add(course);
        } else {
            System.out.println("Error: Student ID " + id + " not found.");
        }
    
    
    }

    public HashSet<String,Student> findStudentsInCourse(String course){
        HashSet<String,Student>student = new HashSet<>();
        
        for(HashMap.Entry<String,Student>entry:)

            
    }
}
