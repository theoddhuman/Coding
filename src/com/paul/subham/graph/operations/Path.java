package com.paul.subham.graph.operations;

import com.paul.subham.graph.implementation.AdjacencyListGraph;
import com.paul.subham.graph.implementation.AdjacencyListWeightedGraph;
import com.paul.subham.graph.implementation.AdjacencyMatrixWeightedGraph;
import com.paul.subham.graph.implementation.Edge;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 1. Shortest path in unweighted graph
 * 2. Shortest path in weighted graph - Dijkstra's Algorithm
 * 3. Shortest path in weighted graph - Dijkstra's Algorithm (Using Array)
 * 4. Shortest path in weighted graph - Bellman-ford Algorithm
 * 5. Searching simple path from source to destination
 * 6. Count all simple paths from source to destination
 * 7. Transitive closure of a graph (Using Floyd Warshall Algorithm)
 * 8. Transitive closure of a graph (Using DFS)
 */
public class Path {
    public static void main(String[] args) {
        AdjacencyListGraph graph = new AdjacencyListGraph(10);
        graph.addEdge(1,2);
        graph.addEdge(1,4);
        graph.addEdge(2,3);
        graph.addEdge(2,5);
        graph.addEdge(4,5);
        graph.addEdge(3,5);
        System.out.println(countAllSimplePaths(1,5,graph));
       // AdjacencyListWeightedGraph graph = new AdjacencyListWeightedGraph(5);
//        graph.addEdge(0,1);
//        graph.addEdge(0,2);
//        graph.addEdge(1,2);
//        graph.addEdge(2,0);
//        graph.addEdge(2,3);
//        graph.addEdge(1,2,4);
//        graph.addEdge(1,3, 5);
//        graph.addEdge(3,4, 2);
//        graph.addEdge(4,2, -20);
//        weightedShortestPath(graph, 1);
//        weightedShortestPathImproved(graph, 1);
//        weightedShortestPathUsingArray(graph, 1);
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
     *
     * Case I
     * This implementation will work negative edges too as this is storing only vertex in the priority queue.
     * It is storing distance in a different data structure i.e., array.
     * It won't work for negative cycle.
     *
     * Case II
     * If we store combination of vertex and distance in priority queue or
     * keep track of visited vertex
     * then may or may not work if the graph has negative edges
     * as it relaxes vertex with each iteration.
     */
    public static void weightedShortestPath(AdjacencyListWeightedGraph graph, int s) {
        Integer[] distance = new Integer[graph.vertex];
        Integer[] path = new Integer[graph.vertex];

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(graph.vertex,
                Comparator.comparing(a -> distance[a]));
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

    public static void weightedShortestPathImproved(AdjacencyListWeightedGraph graph, int s) {
        Integer[] distance = new Integer[graph.vertex];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Integer[] path = new Integer[graph.vertex];

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(graph.vertex,
                Comparator.comparing(a -> distance[a]));
        distance[s] = 0;
        priorityQueue.add(s);
        while(!priorityQueue.isEmpty()) {
            int current = priorityQueue.remove();
            LinkedList<Edge> adjList = graph.adjListArray[current];
            for(Edge edge : adjList) {
                int d = distance[current] + edge.weight;
                int next = edge.destination;
                if (distance[next] > d) {
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
        Arrays.fill(distance, Integer.MAX_VALUE);
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
            if(!visited[i] && distance[i] < min) {
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
        Arrays.fill(distance, Integer.MAX_VALUE);
        Integer[] path = new Integer[graph.vertex];
        Queue<Integer> queue = new LinkedList<>();
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

    /**
     * Searching simple path from source to destination
     * TC: O(E)
     * SC: O(V)
     */
    public static boolean hasSimplePath(int source, int destination, AdjacencyListGraph graph) {
        boolean[] visited = new boolean[graph.vertex];
        if(source == destination) {
            return true;
        }
        return hasSimplePathUtil(source, destination, visited, graph);
    }

    private static boolean hasSimplePathUtil(int source, int destination, boolean[] visited, AdjacencyListGraph graph) {
        visited[source] = true;
        if(source == destination) {
            return true;
        }
        List<Integer> adjList = graph.adjListArray[source];
        for(Integer i : adjList) {
            if(!visited[i]) {
                if(hasSimplePathUtil(i, destination, visited, graph)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Count all simple paths from source to destination
     * TC: O(E)
     * SC: O(V)
     */
    public static int countAllSimplePaths(int source, int destination, AdjacencyListGraph graph) {
        boolean[] visited = new  boolean[graph.vertex];
        if(source == destination) {
            return 1;
        }
        countAllSimplePathsUtil(source, destination, visited, graph);
        return count;
    }
    private static int count = 0;
    private static void countAllSimplePathsUtil(int source, int destination, boolean[] visited, AdjacencyListGraph graph) {
        visited[source] = true;
        if(source == destination) {
            count++;
        } else {
            List<Integer> adjList = graph.adjListArray[source];
            for(Integer i : adjList) {
                if(!visited[i]) {
                    countAllSimplePathsUtil(i, destination, visited, graph);
                }
            }
        }
        visited[source] = false;
    }

    /**
     * Transitive closure of a graph (Using Floyd Warshall Algorithm)
     *
     * This is only for adjacency matrix graph.
     *
     * Given a directed graph, find out if a vertex j is reachable from another vertex i for all vertex pairs (i, j) in the given graph.
     * Here reachable mean that there is a path from vertex i to j.
     * The reach-ability matrix is called the transitive closure of a graph.
     *
     *
     * TC: O(V^3)
     * SC: O(V^2)
     */
    public static boolean[][] transitiveClosure(AdjacencyMatrixWeightedGraph graph) {
        int vertex = graph.vertex;
        boolean[][] reach = new boolean[vertex][vertex];
        for(int i=0; i<vertex; i++) {
            for(int j=0; j<vertex; j++) {
                //same vertex
                if(i==j) {
                    reach[i][j] = true;
                } else {
                    //edge
                    if(graph.adjMatrix[i][j] != 0) {
                        reach[i][j] = true;
                    }
                }
            }
        }

        for(int k=0; k<vertex; k++) {
            for(int i=0; i<vertex; i++) {
                for(int j=0; j<vertex; j++) {
                    // if i to j via k
                    reach[i][j] = reach[i][j] || (reach[i][k] && reach[k][j]);
                }
            }
        }
        return reach;
    }

    /**
     * Transitive closure of a graph (Using DFS)
     * TC: O(V(V+E))
     * SC: O(V^2)
     */
    public static boolean[][] transitiveClosureDFS(AdjacencyListGraph graph) {
        int vertex = graph.vertex;
        boolean[][] tc = new boolean[vertex][vertex];
        for(int i=0; i<vertex; i++) {
            transitiveClosureDFSUtil(i, i, tc, graph);
        }
        return tc;
    }

    private static void transitiveClosureDFSUtil(int source, int destination, boolean[][] tc, AdjacencyListGraph graph) {
        tc[source][destination] = true;
        List<Integer> adjList = graph.adjListArray[destination];
        for(Integer i : adjList) {
            if(!tc[source][i]) {
                transitiveClosureDFSUtil(source, i, tc, graph);
            }
        }
    }
}
