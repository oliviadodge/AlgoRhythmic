package com.olivia.sep2021;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/*
 Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
*/

class NumIslandsBreadthFirstSearch {

    HashMap<String, Integer> numTimesEachNodeVisited = new HashMap<>();

    public static void main(String[] args) {
        NumIslandsBreadthFirstSearch thing = new NumIslandsBreadthFirstSearch();

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

        int numRows = grid.length;
        int numColumns = grid[0].length;
        int numIslands = 0;

        for (int row = 0; row < numRows; row++) {
            for (int column = 0; column < numColumns; column++) {
                String node = row + " " + column;
                int numTimesVisited = numTimesEachNodeVisited.getOrDefault(node, 0) + 1;
                numTimesEachNodeVisited.put(node, numTimesVisited);

                if (grid[row][column] == '1') {
                    numIslands++;
                    grid[row][column] = '0'; // mark as visited
                    Queue<Integer> neighbors = new LinkedList<>();
                    neighbors.add(row * numColumns + column);
                    while (!neighbors.isEmpty()) {
                        int id = neighbors.remove();
                        int nodeRow = id / numColumns;
                        int nodeColumn = id % numColumns;

                        String node2 = nodeRow + " " + nodeColumn;
                        int numTimesVisited2 = numTimesEachNodeVisited.getOrDefault(node2, 0) + 1;
                        numTimesEachNodeVisited.put(node2, numTimesVisited2);

                        if (nodeRow - 1 >= 0 && grid[nodeRow-1][nodeColumn] == '1') {
                            neighbors.add((nodeRow-1) * numColumns + nodeColumn);
                            grid[nodeRow-1][nodeColumn] = '0';
                        }
                        if (nodeRow + 1 < numRows && grid[nodeRow + 1][nodeColumn] == '1') {
                            neighbors.add((nodeRow + 1) * numColumns + nodeColumn);
                            grid[nodeRow + 1][nodeColumn] = '0';
                        }
                        if (nodeColumn - 1 >= 0 && grid[nodeRow][nodeColumn - 1] == '1') {
                            neighbors.add(nodeRow * numColumns + nodeColumn - 1);
                            grid[nodeRow][nodeColumn - 1] = '0';
                        }
                        if (nodeColumn + 1 < numColumns && grid[nodeRow][nodeColumn + 1] == '1') {
                            neighbors.add(nodeRow * numColumns + nodeColumn + 1);
                            grid[nodeRow][nodeColumn + 1] = '0';
                        }
                    }
                }
            }
        }
        return numIslands;
    }
}
