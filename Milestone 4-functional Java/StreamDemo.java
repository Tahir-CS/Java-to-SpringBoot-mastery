import java.util.List;

public class StreamDemo {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // FILTER: keep only matching elements.
        List<Integer> evens = numbers.stream()
                .filter(n -> n % 2 == 0)
                .toList();

        // MAP: transform each element.
        List<Integer> doubled = numbers.stream()
                .map(n -> n * 2)
                .toList();

        // Chain filter + map.
        List<Integer> evenThenTripled = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * 3)
                .toList();

        // REDUCE: collapse stream to one value.
        int sum = numbers.stream()
                .reduce(0, (acc, n) -> acc + n);

        // COUNT elements matching condition.
        long countEvens = numbers.stream()
                .filter(n -> n % 2 == 0)
                .count();

        // Match operations.
        boolean hasNegative = numbers.stream().anyMatch(n -> n < 0);
        boolean allPositive = numbers.stream().allMatch(n -> n > 0);
        boolean noneGreaterThan20 = numbers.stream().noneMatch(n -> n > 20);

        System.out.println("Evens: " + evens);
        System.out.println("Doubled: " + doubled);
        System.out.println("Even then tripled: " + evenThenTripled);
        System.out.println("Sum: " + sum);
        System.out.println("Count of evens: " + countEvens);
        System.out.println("Has negative: " + hasNegative);
        System.out.println("All positive: " + allPositive);
        System.out.println("None greater than 20: " + noneGreaterThan20);
    }
}
