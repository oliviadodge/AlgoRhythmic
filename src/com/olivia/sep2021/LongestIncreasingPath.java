package com.olivia.sep2021;

class LongestIncreasingPath {

    public static void main(String[] args) {
        LongestIncreasingPath thing = new LongestIncreasingPath();

        int[][] grid = new int[][]{
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
        };

        System.out.println("answer: " + thing.longestIncreasingPath(grid));
    }

    public int longestIncreasingPath(int[][] matrix) {

        int longestIncreasingPath = 0;

        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[0].length; column++) {
                longestIncreasingPath = findLongestIncreasingPathForCell(row, column, 0, matrix, longestIncreasingPath);
            }
        }
        return longestIncreasingPath;
    }

    private int findLongestIncreasingPathForCell(int row, int column, int prevCellVal, int[][] matrix, int maxPathSoFar) {

        if (row < 0 || row >= matrix.length || column < 0 || column >= matrix[0].length) {
            return 0;
        } else if (matrix[row][column] <= prevCellVal) {
            return 0;
        } else {
            return Math.max(maxPathSoFar,
                    Math.max(
                            Math.max(
                                    1 + findLongestIncreasingPathForCell(row - 1, column, matrix[row][column], matrix, maxPathSoFar),
                                    1 + findLongestIncreasingPathForCell(row + 1, column, matrix[row][column], matrix, maxPathSoFar)
                            ),
                            Math.max(
                                    1 + findLongestIncreasingPathForCell(row, column - 1, matrix[row][column], matrix, maxPathSoFar),
                                    1 + findLongestIncreasingPathForCell(row, column + 1, matrix[row][column], matrix, maxPathSoFar)
                            )
                    )
            );
        }
    }
}
