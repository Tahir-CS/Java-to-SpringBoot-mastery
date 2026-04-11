import java.util.ArrayList;
import java.util.List;

public class GenericsDemo {
    
    public static void main(String[] args) {
        
        // Example 1: Generic Class - Box can hold ANY type
        Box<String> stringBox = new Box<>("Hello World");
        String value = stringBox.getContent();  // No casting needed!
        
        Box<Integer> intBox = new Box<>(42);
        int number = intBox.getContent();
        
        Box<Dog> dogBox = new Box<>(new Dog("Buddy"));
        Dog dog = dogBox.getContent();
        
        // Example 2: Generic Methods
        String[] names = {"Ali", "Sara", "Ahmed"};
        Integer[] numbers = {10, 20, 30};
        
        Utils.printArray(names);
        Utils.printArray(numbers);
        
        // Example 3: Generic method with return type
        ArrayList<String> wordList = new ArrayList<>(List.of("First", "Second", "Third"));
        String firstWord = Utils.getFirst(wordList);
        
        ArrayList<Integer> numList = new ArrayList<>(List.of(100, 200, 300));
        Integer firstNum = Utils.getFirst(numList);
    }
}

// Generic Class: T is a placeholder for any type
public class Box<T> {
    private T content;
    
    public Box(T content) {
        this.content = content;
    }
    
    public T getContent() {
        return content;
    }
    
    public void setContent(T content) {
        this.content = content;
    }
}

class Dog {
    String name;
    
    public Dog(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Dog(" + name + ")";
    }
}

// Utility class with Generic Methods
class Utils {
    
    // Generic method: works with ANY array type
    public static <T> void printArray(T[] array) {
        for (T item : array) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
    
    // Generic method with return type
    public static <T> T getFirst(ArrayList<T> list) {
        if (list.isEmpty()) return null;
        return list.get(0);
    }
    
    // Generic method to get max value from array (with comparable constraint)
    public static <T extends Comparable<T>> T getMax(T[] array) {
        T max = array[0];
        for (T item : array) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }
}

// Example: Generic Pair class
class Pair<K, V> {
    private K key;
    private V value;
    
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    public K getKey() {
        return key;
    }
    
    public V getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }
}
