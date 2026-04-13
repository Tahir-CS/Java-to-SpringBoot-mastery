import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class LambdaDemo {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>(List.of("Ali", "Sara", "Ahmed", "Zara", "Bob"));

        // Old style loop.
        for (String name : names) {
            System.out.println(name);
        }

        // Lambda with forEach.
        names.forEach(name -> System.out.println(name));

        // Method reference version.
        names.forEach(System.out::println);

        // Lambda comparators for sorting.
        names.sort((a, b) -> a.compareTo(b));
        names.sort((a, b) -> b.compareTo(a));
        names.sort((a, b) -> a.length() - b.length());

        System.out.println(names);

        // Lambda syntax forms.
        Runnable noParams = () -> System.out.println("Hello from lambda");
        Function<String, String> oneParam = value -> "Hello " + value;
        BiFunction<Integer, Integer, Integer> multipleParams = (a, b) -> a + b;
        BiFunction<Integer, Integer, Integer> multiLineBody = (a, b) -> {
            int sum = a + b;
            return sum;
        };

        noParams.run();
        System.out.println(oneParam.apply("Tahir"));
        System.out.println(multipleParams.apply(5, 7));
        System.out.println(multiLineBody.apply(10, 20));
    }
}
