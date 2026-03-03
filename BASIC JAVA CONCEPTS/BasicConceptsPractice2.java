public class BasicConceptsPractice2 {
    public static void main(String[] args) {
        int number = 7;
        
        if (number % 2 == 0) {
            System.out.println(number + " is even");
        } else {
            System.out.println(number + " is odd");
        }

        
        char grade = 'B';
        switch (grade) {
            case 'A':
                System.out.println("Excellent");
                break;
            case 'B':
                System.out.println("Good");
                break;
            case 'C':
                System.out.println("Average");
                break;
            default:
                System.out.println("Needs Improvement");
        }

        
        for (int i = 1; i <= 5; i++) {
            System.out.println("Count: " + i);
        }

        
        int count = 5;
        while (count > 0) {
            System.out.println("Countdown: " + count);
            count--;
        }
    }
} 
public class MatrixManipulator {

    public static void main(String[] executionArgs) {
        System.out.println("--- Starting Matrix Operations ---\n");

        int[][] originalMatrix = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };

        System.out.println("Original 3x4 Matrix:");
        printMatrix(originalMatrix);

        int[][] transposedMatrix = transpose(originalMatrix);

        System.out.println("\nTransposed 4x3 Matrix:");
        printMatrix(transposedMatrix);
    }

    public static void printMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            System.out.print("[ ");
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] < 10) {
                    System.out.print(" " + matrix[row][col] + " ");
                } else {
                    System.out.print(matrix[row][col] + " ");
                }
            }
            System.out.println("]");
        }
    }

    public static int[][] transpose(int[][] original) {
        int originalRows = original.length;
        int originalCols = original[0].length;

        int[][] newMatrix = new int[originalCols][originalRows];

        for (int row = 0; row < originalRows; row++) {
            for (int col = 0; col < originalCols; col++) {
                newMatrix[col][row] = original[row][col];
            }
        }
        return newMatrix;
    }
}