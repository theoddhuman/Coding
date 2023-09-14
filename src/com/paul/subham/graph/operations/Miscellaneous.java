package com.paul.subham.graph.operations;

import com.paul.subham.graph.implementation.AdjacencyListGraph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1. Get level of each node from source in a graph (using BFS)
 * 2. Count of nodes at a level from source (using BFS)
 * 3. Counting no of tress in a forest (Using DFS)
 * 4. Counting no of tress in a forest (Using BFS)
 * 5. k core of an undirected graph
 */
public class Miscellaneous {
    public static void main(String[] args) {
        AdjacencyListGraph g1 = new AdjacencyListGraph(10);
        g1.addEdgeUndirected(0, 1);
        g1.addEdgeUndirected(0, 2);
        g1.addEdgeUndirected(1, 2);
        g1.addEdgeUndirected(1, 5);
        g1.addEdgeUndirected(2, 3);
        g1.addEdgeUndirected(2, 4);
        g1.addEdgeUndirected(2, 5);
        g1.addEdgeUndirected(2, 6);
        g1.addEdgeUndirected(3, 4);
        g1.addEdgeUndirected(3, 6);
        g1.addEdgeUndirected(3, 7);
        g1.addEdgeUndirected(4, 6);
        g1.addEdgeUndirected(4, 7);
        g1.addEdgeUndirected(5, 6);
        g1.addEdgeUndirected(5, 8);
        g1.addEdgeUndirected(6, 7);
        g1.addEdgeUndirected(6, 8);
        printKCore(g1, 3);
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

    /**
     * Counting no of tress in a forest (Using DFS)
     * TC: O(V+E)
     * SC: O(V)
     */
    public static int countTreesInForestDFS(AdjacencyListGraph graph) {
        int trees = 0;
        for(int i=0; i<graph.vertex; i++) {
            if(!graph.visited[i]) {
                graph.DFS(i);
                trees++;
            }
        }
        return trees;
    }

    /**
     * Counting no of tress in a forest (Using BFS)
     * TC: O(V+E)
     * SC: O(V)
     */
    public static int countTreesInForestBFS(AdjacencyListGraph graph) {
        int trees = 0;
        for(int i=0; i<graph.vertex; i++) {
            if(!graph.visited[i]) {
                graph.BFS(i);
                trees++;
            }
        }
        return trees;
    }

    /**
     * k core of an undirected graph
     *
     * k core of the graph are connected components that are left after all vertices of degree less than k have been removed.
     *
     * TC: O(V+E)
     * SC: O(V)
     */
    public static void printKCore(AdjacencyListGraph graph, int k) {
        int[] degree = new int[graph.vertex];
        int minDegree = Integer.MAX_VALUE;
        int startVertex = 0;
        for(int i=0; i<graph.vertex; i++) {
            degree[i] = graph.adjListArray[i].size();
            if(degree[i] < minDegree) {
                minDegree = degree[i];
                startVertex = i;
            }
        }
        dfsKCoreUtil(startVertex, degree, k, graph);
        for(int i=0; i<graph.vertex; i++) {
            if(!graph.visited[i]) {
                dfsKCoreUtil(i, degree, k, graph);
            }
        }
        for(int i=0; i<graph.vertex; i++) {
            if(degree[i] >= k) {
                System.out.print(i);
                for(Integer j : graph.adjListArray[i]) {
                    if(degree[j] >= k) {
                        System.out.print("->" + j);
                    }
                }
                System.out.println();
            }
        }
    }

    private static void dfsKCoreUtil(int s, int[] degree, int k, AdjacencyListGraph graph) {
        graph.visited[s] = true;
        LinkedList<Integer> adjList = graph.adjListArray[s];
        for(Integer i : adjList) {
            if(degree[s] < k) {
                degree[i]--;
            }
            if(!graph.visited[i]) {
                dfsKCoreUtil(i, degree, k, graph);
            }
        }
    }
}
