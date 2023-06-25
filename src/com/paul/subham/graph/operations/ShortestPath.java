package com.paul.subham.graph.operations;

import com.paul.subham.graph.implementation.AdjacencyListGraph;
import com.paul.subham.graph.implementation.AdjacencyListWeightedGraph;
import com.paul.subham.graph.implementation.Edge;

import java.util.LinkedList;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 1. Shortest path in unweighted graph
 * 2. Shortest path in weighted graph - Dijkstra's Algorithm
 * 3. Shortest path in weighted graph - Dijkstra's Algorithm (Using Array)
 * 4. Shortest path in weighted graph - Bellman-ford Algorithm
 */
public class ShortestPath {
    public static void main(String[] args) {
        AdjacencyListWeightedGraph graph = new AdjacencyListWeightedGraph(10);
//        graph.addEdge(0, 1, 4);
//        graph.addEdge(0, 2, 3);
//        graph.addEdge(1, 3, 2);
//        graph.addEdge(2, 3, 2);
//        graph.addEdge(3, 4, 1);
//        graph.addEdge(3, 5, 3);
        //test for bellman ford
        graph.addEdge(1,2,3);
        graph.addEdge(1,3,2);
        graph.addEdge(3,2,-4);
        weightedShortestPathBF(graph, 1);
    }

    /**
     * Shortest path in unweighted graph
     * TC: O(V+E)
     * SC: O(V)
     */
    public static void unweightedShortestPath(AdjacencyListGraph graph, int s) {
        Integer[] distance = new Integer[graph.vertex];
        Integer[] path = new Integer[graph.vertex];
        Queue<Integer> queue = new LinkedList<>();
        distance[s] = 0;
        queue.add(s);
        while(!queue.isEmpty()) {
            int current = queue.remove();
            LinkedList<Integer> adjList = graph.adjListArray[current];
            for(Integer i : adjList) {
                if(distance[i] == null) {
                    path[i] = current;
                    distance[i] = distance[current] + 1;
                    queue.add(i);
                }
            }
        }

        for(int i=0; i<graph.vertex; i++) {
            System.out.println(i + " " + path[i] + " " + distance[i]);
        }
    }

    /**
     * Shortest path in weighted graph - Dijkstra's Algorithm
     * TC: Using PQ, V delete min + E updates, O(VlogV + ElogV) = O(ElogV),
     * If V = n, total possible edge = n*(n-1)/2, O(n^2logn)
     * TC: for adjacency matrix, O(V^2logV)
     * SC: O(V)
     * It may or may not work if the graph has negative edges as it relaxes vertex with each iteration.
     */
    public static void weightedShortestPath(AdjacencyListWeightedGraph graph, int s) {
        Integer[] distance = new Integer[graph.vertex];
        Integer[] path = new Integer[graph.vertex];

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(graph.vertex,
                (a, b) -> distance[a].compareTo(distance[b]));
        distance[s] = 0;
        priorityQueue.add(s);
        while(!priorityQueue.isEmpty()) {
            int current = priorityQueue.remove();
            LinkedList<Edge> adjList = graph.adjListArray[current];
            for(Edge edge : adjList) {
                int d = distance[current] + edge.weight;
                int next = edge.destination;
                if(distance[next] == null) {
                    distance[next] = d;
                    path[next] = current;
                    priorityQueue.add(next);
                } else if (distance[next] > d) {
                    priorityQueue.remove(next);
                    distance[next] = d;
                    path[next] = current;
                    priorityQueue.add(next);
                }
            }
        }

        for(int i=0; i<graph.vertex; i++) {
            System.out.println(i + " " + distance[i] + " " + path[i]);
        }
    }

    /**
     * Shortest path in weighted graph - Dijkstra's Algorithm (Using Array)
     * TC: Using array, V min check + E updates, O(V^2 + E)
     * If V = n, total possible edge = n*(n-1)/2, O(n^2 + n*(n-1)/2) = O(n^2)
     * SC: O(V)
     */
    public static void weightedShortestPathUsingArray(AdjacencyListWeightedGraph graph, int s) {
        Integer[] distance = new Integer[graph.vertex];
        Integer[] path = new Integer[graph.vertex];
        distance[s] = 0;
        for(int i=0; i<graph.vertex-1; i++) {
            int current = minDistance(distance, graph.visited);
            if(current == -1) {
                break;
            }
            graph.visited[i] = true;
            LinkedList<Edge> adjList = graph.adjListArray[current];
            for(Edge edge : adjList) {
                int next = edge.destination;
                int d = distance[current] + edge.weight;
                if(!graph.visited[next] || distance[next] > d) {
                    distance[next] = d;
                    path[next] = current;
                }
            }
        }
        for(int i=0; i<graph.vertex; i++) {
            System.out.println(i + " " + distance[i] + " " + path[i]);
        }
    }

    private static int minDistance(Integer[] distance, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for(int i=0; i<distance.length; i++) {
            if(Objects.nonNull(distance[i]) && !visited[i] && distance[i] < min) {
                min = distance[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    /**
     * Shortest path in weighted graph - Bellman-ford Algorithm
     * TC: Check E edges V-1 times, O(|V|(|E|-1)) = O(|V||E|)
     * If V = n, total possible edge = n*(n-1)/2, O(n * n*(n-1)/2) = O(n^3)
     * SC: O(V)
     * It doesn't work if total weight of the circle is negative, it goes into infinite loop.
     */
    public static void weightedShortestPathBF(AdjacencyListWeightedGraph graph, int s) {
        Integer[] distance = new Integer[graph.vertex];
        Integer[] path = new Integer[graph.vertex];
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<graph.vertex; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[s] = 0;
        queue.add(s);
        while(!queue.isEmpty()) {
            int current = queue.remove();
            LinkedList<Edge> adjList = graph.adjListArray[current];
            for(Edge edge : adjList) {
                int next = edge.destination;
                int d = distance[current] + edge.weight;
                if(distance[next] > d) {
                    distance[next] = d;
                    path[next] = current;
                    if(!queue.contains(next)) {
                        queue.add(next);
                    }
                }
            }
        }

        for(int i=0; i< graph.vertex; i++) {
            System.out.println(i+" "+distance[i]+" "+path[i]);
        }
    }
}
