package com.paul.subham.graph.operations;

import com.paul.subham.graph.implementation.AdjacencyListWeightedGraph;
import com.paul.subham.graph.implementation.Edge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 1. Topological Sort
 */
public class Sorting {
    public static void main(String[] args) {
        AdjacencyListWeightedGraph graph = new AdjacencyListWeightedGraph(10);
        graph.addEdge(0, 1, 5);
        graph.addEdge(1, 2, 4);
        graph.addEdge(1, 3, 5);
        graph.addEdge(2, 3, 6);
        topologicalSort(graph);
    }

    /**
     * Topological Sort
     * TC: O(V+E)
     * SC: O(V)
     */
    static void topologicalSort(AdjacencyListWeightedGraph graph) {
        int[] indegree = new int[graph.vertex];
        int[] rank = new int[graph.vertex];
        int counter = 0;
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> order = new ArrayList<>();
        for(int i=0; i<graph.vertex; i++) {
            List<Edge> adjList = graph.adjListArray[i];
            for(Edge edge : adjList) {
                indegree[edge.destination]++;
            }
        }
        for(int i=0; i<graph.vertex; i++) {
            if(indegree[i] == 0) {
                queue.add(i);
            }
        }

        while(!queue.isEmpty()) {
            int current = queue.remove();
            rank[current] = ++counter;
            order.add(current);
            List<Edge> adjList = graph.adjListArray[current];
            for(Edge edge : adjList) {
                if(--indegree[edge.destination] == 0) {
                    queue.add(edge.destination);
                }
            }
        }

        if(counter != graph.vertex) {
            System.out.println("Cyclic Graph");
            return;
        }

        for(Integer i : order) {
            System.out.println(i);
        }
    }
}
