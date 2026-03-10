public class InstanceObjectconcept {
    
//here we will learn upcasting and downcasting 
public class InstanceObjectconcept {
    
    public static void main(String[] args) {
        
        System.out.println("--- Upcasting and Downcasting Example ---");
        
        // 1. Upcasting: Dog object is referred to as an Animal
        // The 'Animal' type determines what methods are VISIBLE.
        // The 'new Dog()' type determines which version of those methods RUNS.
        Animal dog = new Dog(); 
        dog.sound(); // Prints: "Dog barks: Woof!" (Because the actual object is a Dog)
        
        System.out.println("\n--- Upcasting (Implicit) ---");
        Animal animal = new Cat(); 
        animal.sound(); // Prints: "Cat meows: Meow!" (Polymorphism in action)
        
        // Note: animal.meow(); would cause a COMPILE ERROR here because 
        // the "Animal" reference doesn't know about the "meow" method yet.

        System.out.println("\n--- Downcasting (Explicit) ---");
        // We check if 'animal' is actually a Cat before casting to avoid a crash.
        if (animal instanceof Cat) {
            // Downcasting: Converting the Animal reference back to a Cat reference.
            Cat cat = (Cat) animal; 
            cat.sound(); // Prints: "Cat meows: Meow!"
            cat.meow();  // Prints: "Cat is meowing softly!" (Now we can access Cat-specific methods)
        }
        
        System.out.println("\n--- Another Downcasting Example ---");
        Animal animal2 = new Dog();
        if (animal2 instanceof Dog) {
            Dog dog2 = (Dog) animal2;
            dog2.sound(); // Prints: "Dog barks: Woof!"
            dog2.bark();  // Prints: "Dog is barking loudly!"
        }
        
        System.out.println("\n--- ClassCastException Example ---");
        Animal animal3 = new Cat(); // The actual object is a Cat
        try {
            // This will FAIL. You cannot turn a Cat object into a Dog.
            // It's like trying to force a square peg into a round hole.
            Dog wrongDog = (Dog) animal3; 
        } catch (ClassCastException e) {
            // Prints: "Error: Cannot cast Cat to Dog!"
            System.out.println("Error: Cannot cast Cat to Dog!");
        }
    }
}

// Parent Class
class Animal {
    public void sound() {
        System.out.println("Animal makes a sound");
    }
}

// Child Class 1
class Dog extends Animal {
    @Override
    public void sound() {
        System.out.println("Dog barks: Woof!");
    }
    
    public void bark() {
        System.out.println("Dog is barking loudly!");
    }
}

// Child Class 2
class Cat extends Animal {
    @Override
    public void sound() {
        System.out.println("Cat meows: Meow!");
    }
    
    public void meow() {
        System.out.println("Cat is meowing softly!");
    }
}}
