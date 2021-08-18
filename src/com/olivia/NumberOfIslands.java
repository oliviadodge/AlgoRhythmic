package com.olivia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberOfIslands {

    public static void main(String[] args) {
        NumberOfIslands stringDecoder = new NumberOfIslands();
    }

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> islandRegistry = new ArrayList<>();
        islandRegistry.add(1);
        List<Integer> numIslands = new ArrayList<>();

        int[][] ocean = new int[m][n];

        for (int i = 0; i < positions.length; i++) {
            int indexOfLastIsland = islandRegistry.size() - 1;
            int newIslandId;
            if (indexOfLastIsland != -1) {
                newIslandId = islandRegistry.get(indexOfLastIsland) + 1;
            } else {
                newIslandId = 1;
            }
            //check if this is connecting with any other island

            int[] position = positions[i];

            int islandsConnected = islandsConnected(ocean, position, newIslandId, islandRegistry);

            int prevNum = numIslands.get(i - 1);

            if (islandsConnected == 0) {
                numIslands.add(prevNum + 1);
            } else {
                numIslands.add(prevNum - (islandsConnected - 1));
            }
        }
        return numIslands;
    }

    private int islandsConnected(int[][] ocean, int[] position, int newIslandId, List<Integer> islandRegistry) {
        int row = position[0];
        int column = position[1];

        int north = 0, south = 0, east = 0, west = 0;

        if ((row - 1 >= 0) && (ocean[row - 1][column] > 0)) {
            north = ocean[row - 1][column];
        } else if ((row + 1 < ocean.length) && ocean[row + 1][column] > 0) {
            south = ocean[row + 1][column];
        } else if ((column - 1 >= 0) && ocean[row][column - 1] > 0) {
            west = ocean[row][column - 1];
        } else if ((column + 1 < ocean[row].length) && ocean[row][column + 1] > 0) {
            east = ocean[row][column + 1];
        } else {
            return 0;
        }

        ocean[position[0]][position[1]] = newIslandId;

        return east + west + north + south;
    }
}

