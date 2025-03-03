package com.olivia.sep2021;

import java.util.*;

class CourseSchedule {

    public static void main(String[] args) {
        CourseSchedule thing = new CourseSchedule();
        int[][] arr = {{1,0}};
        int[][] arr2 = {{1,0},{2,0},{3,1},{3,2}};
        int[] order = thing.findOrder(2, arr);
        int[] order2 = thing.findOrder(4, arr2);

        System.out.println("output: " + Arrays.toString(order));
        System.out.println("output: " + Arrays.toString(order2));
    }

    static int WHITE = 1;
    static int GRAY = 2;
    static int BLACK = 3;

    boolean isPossible;
    Map<Integer, Integer> colors;
    Map<Integer, List<Integer>> adjList;
    List<Integer> topologicalOrder;

    private void init(int numCourses) {
        this.isPossible = true;
        this.colors = new HashMap<>();
        this.adjList = new HashMap<>();
        this.topologicalOrder = new ArrayList<>();

        // By default all vertces are WHITE
        for (int i = 0; i < numCourses; i++) {
            this.colors.put(i, WHITE);
        }
    }

    private void dfs(int node) {

        // Don't recurse further if we found a cycle already
        if (!this.isPossible) {
            return;
        }

        // Start the recursion
        this.colors.put(node, GRAY);

        // Traverse on neighboring vertices
        for (Integer neighbor : this.adjList.getOrDefault(node, new ArrayList<>())) {
            if (this.colors.get(neighbor) == WHITE) {
                this.dfs(neighbor);
            } else if (this.colors.get(neighbor) == GRAY) {
                // An edge to a GRAY vertex represents a cycle
                this.isPossible = false;
            }
        }

        // Recursion ends. We mark it as black
        this.colors.put(node, BLACK);
        this.topologicalOrder.add(node);
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        this.init(numCourses);

        // Create the adjacency list representation of the graph
        for (int[] prerequisite : prerequisites) {
            int dest = prerequisite[0];
            int src = prerequisite[1];
            List<Integer> lst = adjList.getOrDefault(src, new ArrayList<>());
            lst.add(dest);
            adjList.put(src, lst);
        }

        // If the node is unprocessed, then call dfs on it.
        for (int i = 0; i < numCourses; i++) {
            if (this.colors.get(i) == WHITE) {
                this.dfs(i);
            }
        }

        int[] order;
        if (this.isPossible) {
            order = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                order[i] = this.topologicalOrder.get(numCourses - i - 1);
            }
        } else {
            order = new int[0];
        }

        return order;
    }
}
