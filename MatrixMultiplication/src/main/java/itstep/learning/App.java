package itstep.learning;

public  class App {
    public static void main( String[] args ) {
        int[][] matrixA = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };
        int[][] matrixB = {
                { 9, 8, 7 },
                { 6, 5, 4 },
                { 3, 2, 1 }
        };

        int[][] result = multiplyMatrices(matrixA, matrixB);
        if (result != null) printMatrices(matrixA, matrixB, result);
        else System.out.println("Матрицы нельзя перемножить!");
    }
    public static int[][] multiplyMatrices(int[][] matrixA, int[][] matrixB) {
        int rowsA = matrixA.length, colsA = matrixA[0].length, rowsB = matrixB.length, colsB = matrixB[0].length;
        if (colsA != rowsB) return null;
        int[][] result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++)  result[i][j] += matrixA[i][k] * matrixB[k][j];
            }
        }
        return result;
    }
    public static void printMatrices(int[][] matrixA, int[][] matrixB, int[][] result) {
        int rowsA = matrixA.length, colsA = matrixA[0].length, rowsB = matrixB.length, colsB = matrixB[0].length;

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsA; j++) System.out.print(matrixA[i][j] + " ");
            if (i == rowsA / 2)  System.out.print(" X  ");
            else System.out.print("    ");

            for (int j = 0; j < colsB; j++) System.out.print(matrixB[i][j] + " ");
            if (i == rowsA / 2) System.out.print(" =  ");
            else System.out.print("    ");

            for (int j = 0; j < result[0].length; j++) System.out.print(result[i][j] + " ");
            System.out.println();
        }
    }
}