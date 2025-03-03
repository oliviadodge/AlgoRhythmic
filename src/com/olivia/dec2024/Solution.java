package com.olivia.dec2024;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public boolean isBipartite(int n, List<List<Integer>> edges) {

        //Make an adjacency list from the input edges.
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (List<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int[] color = new int[n];
        for (int i = 0; i < n; i++) {
            color[i] = -1;
        }

        for (int startNode = 0; startNode < n; startNode++) {
            if (color[startNode] == -1) { //Hasn't been assigned yet
                Queue<Integer> queue = new LinkedList<>();
                queue.add(startNode);
                color[startNode] = 0;

                while (!queue.isEmpty()) {
                    int node = queue.poll();

                    for (int connectedNode : graph.get(node)) {
                        if (color[connectedNode] == -1) {
                            color[connectedNode] = 1 - color[node]; // 1 in the case of the neighbors of startNode
                            queue.add(connectedNode);
                        } else if (color[connectedNode] == color[node]) { // it is the same color as its parent
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}

