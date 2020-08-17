package processor;


import java.util.Scanner;

public class Matrix {

    private static final Scanner scanner = new Scanner(System.in);

    public static double[][] createMatrix(int row, int col) {
        double[][] matrix = new double[row][col];
        System.out.println("Enter matrix:");
        for (int r = 0; r < row; r++) {
            String[] columns = scanner.nextLine().split("\\s");
            for (int c = 0; c < col; c++) {
                matrix[r][c] = Double.parseDouble(columns[c]);
            }
        }
        return matrix;
    }

    public static double[][] multiplyByScalar(double[][] matrix1, double d) {
        int r1 = matrix1.length;
        int c1 = matrix1[0].length;
        double[][] product = new double[r1][c1];
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c1; j++) {
                product[i][j] += matrix1[i][j] * d;
            }
        }

        return product;
    }

    public static double[][] multiply(double[][] matrix1, double[][] matrix2) {
        int r1 = matrix1.length;
        int c1 = matrix1[0].length, c2 = matrix2[0].length;
        double[][] product = new double[r1][c2];
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                for (int k = 0; k < c1; k++) {
                    product[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        return product;
    }

    public static double[][] add(double[][] matrix1, double[][] matrix2) {
        double[][] sum = new double[matrix1.length][matrix1.length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2.length; j++) {
                sum[i][j] += matrix1[i][j] + matrix2[i][j];
            }
        }

        return sum;
    }

    public static double[][] transpose(double[][] matrix, String type) {
        int r = matrix.length;
        int c = matrix[0].length;
        double[][] transposed = new double[r][c];
        switch (type) {
            case "1":
                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        transposed[i][j] = matrix[j][i];
                    }
                }
                break;
            case "2":
                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        transposed[i][j] = matrix[r - 1 - j][c - 1 - i];
                    }
                }
                break;
            case "3":
                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        transposed[i][j] = matrix[i][c - 1 - j];
                    }
                }
                break;
            case "4":
                for (int i = 0; i < r; i++) {
                    System.arraycopy(matrix[r - 1 - i], 0, transposed[i], 0, c);
                }
                break;
        }
        return transposed;
    }

    public static double getDeterminant(double[][] matrix) {
        if (matrix.length == 1)
            return matrix[0][0];
        else {
            double det = 0;
            double sign = 1;
            int k, l;
            double[][] minor = new double[matrix.length - 1][matrix.length - 1];
            for (int cofactor = 0; cofactor < matrix.length; cofactor++) {
                k = 0;
                for (int i = 1; i < matrix.length; i++) {
                    l = 0;
                    for (int j = 0; j < matrix.length; j++) {
                        if (j == cofactor) continue;
                        minor[k][l] = matrix[i][j];
                        l++;
                    }
                    k++;
                }
                det += sign * matrix[0][cofactor] * getDeterminant(minor);
                sign *= -1;
            }
            return det;
        }
    }

    private static double getCofactor(double[][] matrix, int i, int j) {
        double sign = Math.pow(-1, i + j + 2);
        int m, n;
        double[][] minor = new double[matrix.length - 1][matrix.length - 1];
        m = 0;
        for (int k = 0; k < matrix.length; k++) {
            if (k == i) continue;
            n = 0;
            for (int l = 0; l < matrix.length; l++) {
                if (l == j) continue;
                minor[m][n] = matrix[k][l];
                n++;
            }
            m++;
        }
        return sign * getDeterminant(minor);
    }

    public static double[][] findInverse(double[][] matrix) throws IllegalArgumentException {
        if (matrix.length != matrix[0].length) throw new IllegalArgumentException("Operation supported only for square matrices");
        double[][] cofactors = new double[matrix.length][matrix.length];
        double det = getDeterminant(matrix);
        if (det == 0) throw new IllegalArgumentException("Inverse matrix cannot be found");
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix.length; j++)
                cofactors[i][j] = getCofactor(matrix,i, j);
        return multiplyByScalar(transpose(cofactors, "1"), 1d / det);
    }

}
