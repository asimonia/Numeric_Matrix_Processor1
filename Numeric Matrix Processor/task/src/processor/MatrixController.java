package processor;

import java.util.Scanner;

public class MatrixController {

    private static final Scanner scanner = new Scanner(System.in);

    public void execute() {
        while (true) {
            System.out.println("1. Add matrices");
            System.out.println("2. Multiply matrix to a constant");
            System.out.println("3. Multiply matrices");
            System.out.println("4. Transpose matrix");
            System.out.println("5. Calculate a determinant");
            System.out.println("6. Inverse matrix");
            System.out.println("0. Exit");

            System.out.print("Your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter size of first matrix: ");
                    double[][] a = createMatrix();
                    System.out.print("Enter size of second matrix: ");
                    double[][] b = createMatrix();
                    double[][] result1 = Matrix.add(a, b);
                    System.out.println("The additive result is:");
                    displayResult(result1);
                    break;
                case "2":
                    System.out.print("Enter size of first matrix: ");
                    double[][] c = createMatrix();
                    System.out.print("Enter a constant: ");
                    double d = Double.parseDouble(scanner.nextLine());
                    double[][] result2 = Matrix.multiplyByScalar(c, d);
                    System.out.println("The multiplication result is:");
                    displayResult(result2);
                    break;
                case "3":
                    System.out.print("Enter size of first matrix: ");
                    double[][] matrix1 = createMatrix();
                    System.out.print("Enter size of second matrix: ");
                    double[][] matrix2 = createMatrix();
                    double[][] result3 = Matrix.multiply(matrix1, matrix2);
                    System.out.println("The multiplication result is:");
                    displayResult(result3);
                    break;
                case "4":
                    System.out.println("1. Main diagonal");
                    System.out.println("2. Side diagonal");
                    System.out.println("3. Vertical line");
                    System.out.println("4. Horizontal line");
                    String transpose = scanner.nextLine();
                    System.out.print("Enter size of matrix: ");
                    double[][] e = createMatrix();
                    double[][] result4 = Matrix.transpose(e, transpose);
                    displayResult(result4);
                    break;
                case "5":
                    System.out.print("Enter size of the matrix: ");
                    double[][] matrix5 = createSquareMatrix();
                    double result5 = Matrix.getDeterminant(matrix5);
                    System.out.println("The result is:");
                    System.out.println(result5);
                    break;
                case "6":
                    System.out.print("Enter size of the matrix: ");
                    double[][] matrix6 = createMatrix();
                    double[][] result6 = Matrix.findInverse(matrix6);
                    System.out.println("The result is:");
                    displayResult(result6);
                    break;
                case "0":
                    System.exit(0);
            }
        }
    }

    public static double[][] createMatrix() {
        String[] size1 = scanner.nextLine().split("\\s");
        int r1 = Integer.parseInt(size1[0]);
        int c1 = Integer.parseInt(size1[1]);
        return Matrix.createMatrix(r1, c1);
    }

    public static double[][] createSquareMatrix() {
        String[] size1 = scanner.nextLine().split("\\s");
        int size = Integer.parseInt(size1[0]);
        return Matrix.createMatrix(size, size);
    }

    public static void displayResult(double[][] result) {
        for (double[] row : result) {
            for (double column : row) {
                System.out.print(column + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
