package com.olivia.sep2021;

class LongestIncreasingPath {

    public static void main(String[] args) {
        LongestIncreasingPath thing = new LongestIncreasingPath();

        int[][] grid = new int[][]{
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
        };

        int[][] grid2 = new int[][]{
                {7, 7, 5},
                {2, 4, 6},
                {8, 2, 0}
        };

        int[][] grid3 = new int[][]{
                {7, 8, 9},
                {9, 7, 6},
                {7, 2, 3}
        };

        System.out.println("answer: " + thing.longestIncreasingPath(grid3));
    }

    private static final int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int m, n;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) return 0;
        m = matrix.length;
        n = matrix[0].length;
        int[][] cache = new int[m][n];
        int maxPath = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                maxPath = Math.max(maxPath, depthFirstSearch(matrix, cache, i, j));
        return maxPath;
    }

    private int depthFirstSearch(int[][] matrix, int[][] cache, int i, int j) {
        if (cache[i][j] != 0) return cache[i][j];
        for (int[] direction : directions) {
            int x = i + direction[0], y = j + direction[1];
            if (0 <= x && x < m && 0 <= y && y < n && matrix[x][y] > matrix[i][j])
                cache[i][j] = Math.max(cache[i][j], depthFirstSearch(matrix, cache, x, y));
        }
        return ++cache[i][j];
    }
}
