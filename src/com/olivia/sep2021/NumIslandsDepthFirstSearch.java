package com.olivia.sep2021;

import java.util.HashMap;

/*
 Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
*/
class NumIslandsDepthFirstSearch {
    HashMap<String, Integer> numTimesEachNodeVisited = new HashMap<>();

    public static void main(String[] args) {
        NumIslandsDepthFirstSearch thing = new NumIslandsDepthFirstSearch();

        char[][] grid = new char[][]{
                {'1', '0', '1', '0', '1'},
                {'0', '0', '1', '1', '1'},
                {'0', '1', '0', '1', '0'},
                {'1', '0', '1', '1', '0'}
        };

        System.out.println("answer: " + thing.numIslands(grid));
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int numberOfRows = grid.length;
        int numberOfColumns = grid[0].length;
        int numberOfIslands = 0;

        for (int row = 0; row < numberOfRows; row++) {
            for (int column = 0; column < numberOfColumns; column++) {

                String node = row + " " + column;
                int numTimesVisited = numTimesEachNodeVisited.getOrDefault(node, 0) + 1;
                numTimesEachNodeVisited.put(node, numTimesVisited);

                if (grid[row][column] == '1') {
                    numberOfIslands++;
                    depthFirstSearch(grid, row, column);
                }
            }
        }
        System.out.println(numTimesEachNodeVisited);
        return numberOfIslands;
    }

    void depthFirstSearch(char[][] grid, int row, int column) {
        int numRows = grid.length;
        int numColumns = grid[0].length;

        if (row < 0 || column < 0 || row >= numRows || column >= numColumns || grid[row][column] == '0') {
            return;
        }

        grid[row][column] = '0';

        String node = row + " " + column;
        int numTimesVisited = numTimesEachNodeVisited.getOrDefault(node, 0) + 1;
        numTimesEachNodeVisited.put(node, numTimesVisited);

        depthFirstSearch(grid, row - 1, column);
        depthFirstSearch(grid, row + 1, column);
        depthFirstSearch(grid, row, column - 1);
        depthFirstSearch(grid, row, column + 1);
    }
}