package com.olivia;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ZeroMatrix {

    static int m = 2, n = 3;
    static int[][] matrix = new int[m][n];

    public static void main(String[] args) {
        try {
            inputZeroMatrix(m,n);
            zeroMatrix(matrix);
            outputZeroMatrix();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void inputZeroMatrix(int m, int n) throws IOException {
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                Scanner in = new Scanner(System.in);
//                matrix[i][j] = in.nextInt();
//            }
//        }
        matrix[0][0] = 6;
        matrix[0][1] = 5;
        matrix[0][2] = 4;

        matrix[1][0] = 0;
        matrix[1][1] = 9;
        matrix[1][2] = 1;
//
//        matrix[2][0] = 1;
//        matrix[2][1] = 2;
//        matrix[2][2] = 0;

    }

    private static void outputZeroMatrix() throws IOException {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = 0;
                }
                System.out.println("row: " + i + " column: " + j + " " + matrix[i][j]);
            }
        }
    }

    private static void zeroMatrix(int[][] matrix) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][j] = -1;
                    for (int k = 0; k < n; k++) {
                        matrix[i][k] = -1;
                    }
                    for (int l = 0; l < m; l++) {
                        matrix[l][j] = -1;
                    }
                }
            }
        }
    }

}
