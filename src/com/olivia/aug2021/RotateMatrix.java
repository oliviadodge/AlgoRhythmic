package com.olivia.aug2021;

import java.util.Arrays;

public class RotateMatrix {

    public static void main(String[] args) {
        RotateMatrix thing = new RotateMatrix();
        int[][] arr = {{1,2,3},{4,5,6},{7,8,9}};
        thing.rotate(arr);

        System.out.println("output: " + Arrays.deepToString(arr));
    }
    public void rotate(int[][] matrix) {

        int size = matrix.length;

        int shell = 0;
        int maxShell = (matrix.length / 2) + (matrix.length % 2);

        while (shell <= maxShell) {
            int row = shell;
            int column = shell;

            while (column < size - shell - 1) {

                int toMove = matrix[row][column];

                for (int i = 0; i < 4; i++) {

                    int newRow = column;
                    int newColumn = matrix.length - row - 1;

                    int newToMove = matrix[newRow][newColumn];

                    matrix[newRow][newColumn] = toMove;

                    row = newRow;
                    column = newColumn;
                    toMove = newToMove;
                }
                column++;
            }
            shell++;
        }
    }

    public void rotateClean(int[][] matrix) {
        int length = matrix.length;
        int numberOfShells = (length + 1) / 2;
        for (int shell = 0; shell < numberOfShells; shell++) {
            for (int column = 0; column < length / 2; column++) {
                int temp = matrix[length - 1 - column][shell];
                matrix[length - 1 - column][shell] = matrix[length - 1 - shell][length - column - 1];
                matrix[length - 1 - shell][length - column - 1] = matrix[column][length - 1 -shell];
                matrix[column][length - 1 - shell] = matrix[shell][column];
                matrix[shell][column] = temp;
            }
        }
    }
}
