package com.paul.subham.graph.operations;

import com.paul.subham.graph.implementation.AdjacencyListGraph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1. Get level of each node from source in a graph (using BFS)
 * 2. Count of nodes at a level from source (using BFS)
 */
public class Level {
    public static void main(String[] args) {
        AdjacencyListGraph graph = new AdjacencyListGraph(10);
        graph.addEdge(1,2);
        graph.addEdge(1,4);
        graph.addEdge(2,3);
        graph.addEdge(2,5);
        graph.addEdge(4,5);
        graph.addEdge(5,6);
        System.out.println(countNodesAtLevelFromSource(graph, 1, 3));
    }

    /**
     * Get level of each node from source in a graph (using BFS)
     * TC: O(V), If V=n, O(n)
     * SC: O(n)
     */
    public static int[] getLevelsOfNodesFromSource(AdjacencyListGraph graph, Integer source) {
        boolean[] visited = new boolean[graph.vertex];
        Queue<Integer> queue = new LinkedList<>();
        int[] level = new int[graph.vertex];
        Arrays.fill(level, -1);
        visited[source] = true;
        queue.add(source);
        level[source] = 0;
        while(!queue.isEmpty()) {
            int current = queue.remove();
            LinkedList<Integer> adjList = graph.adjListArray[current];
            for(Integer i : adjList) {
                if(!visited[i]) {
                    level[i] = level[current] + 1;
                    queue.add(i);
                }
            }
        }
        return level;
    }

    /**
     * Count of nodes at a level from source (using BFS)
     * TC: O(V), If V=n, O(n)
     * SC: O(n)
     */
    public static int countNodesAtLevelFromSource(AdjacencyListGraph graph, int source, int level) {
        int[] levelArray = getLevelsOfNodesFromSource(graph, source);
        int count = 0;
        for (int j : levelArray) {
            if (j == level) {
                count++;
            }
        }
        return count;
    }
}
