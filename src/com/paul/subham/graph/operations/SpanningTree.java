package com.paul.subham.graph.operations;

import com.paul.subham.graph.implementation.AdjacencyListWeightedGraph;
import com.paul.subham.graph.implementation.Edge;
import com.paul.subham.set.implementation.QuickFindSet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * 1. Minimum Spanning Tree - Prim's Algorithm (Using Priority Queue)
 * 2. Minimum Spanning Tree - Prim's Algorithm (Using Array)
 * 3. Min Spanning Tree - Kruskal Algorithm
 * 4. Spanning Tree of an undirected graph
 * 5. Spanning Tree of an undirected graph (Using DFS)
 * 6. Spanning Tree of an undirected graph (Using BFS)
 */
public class SpanningTree {
    public static void main(String[] args) {
        AdjacencyListWeightedGraph graph = new AdjacencyListWeightedGraph(10);
        graph.addEdgeUndirected(1,2,2);
        graph.addEdgeUndirected(1,3,4);
        graph.addEdgeUndirected(2,4,6);
        graph.addEdgeUndirected(3,4,2);
        spanningTreeBFS(graph);
    }

    /**
     * Minimum Spanning Tree - Prim's Algorithm (Using Priority Queue)
     * TC: Using PQ, V delete min + E updates, O(VlogV + ElogV) = O(ElogV),
     * If V = n, total possible edge = n*(n-1)/2, O(n^2logn)
     * TC: for adjacency matrix, O(V^2logV)
     * SC: O(V)
     */
    public static void minSpanningTree(AdjacencyListWeightedGraph graph, int s) {
        Integer[] distance = new Integer[graph.vertex];
        Integer[] path = new Integer[graph.vertex];
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(graph.vertex,
                Comparator.comparing(o -> distance[o]));
        distance[s] = 0;
        priorityQueue.add(s);
        while(!priorityQueue.isEmpty()) {
            int current = priorityQueue.remove();
            LinkedList<Edge> adjList = graph.adjListArray[current];
            for(Edge edge : adjList) {
                int next = edge.destination;
                int d = edge.weight;
                if(Objects.isNull(distance[next])) {
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
            System.out.println(i+" "+distance[i]+" "+path[i]);
        }
    }

    /**
     * Minimum Spanning Tree - Prim's Algorithm (Using Array)
     * TC: Using array, V min check + E updates, O(V^2 + E)
     * If V = n, total possible edge = n*(n-1)/2, O(n^2 + n*(n-1)/2) = O(n^2)
     * SC: O(V)
     */
    public static void minSpanningTreeUsingArray(AdjacencyListWeightedGraph graph, int s) {
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
                int d = edge.weight;
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
     * Min Spanning Tree - Kruskal Algorithm
     * TC: E insertions in priority queue + 2*(V-1) find operations + V-1 union operations
     * TC: ElogE + 2V + VlogV ~ ElogE + VlogV, E can be at most V^2
     * TC: O(ElogE), When V=n, TC: O(n^2logn)
     * SC: O(V+E) = O(E) = O(n^2)
     */
    public static void minSpanningTreeKruskal(AdjacencyListWeightedGraph graph) {
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(graph.edgeCount(), (o1, o2) -> {
            Integer x = o1.weight;
            Integer y = o2.weight;
            return x.compareTo(y);
        });
        for(int i=0; i<graph.vertex; i++) {
            LinkedList<Edge> adjList = graph.adjListArray[i];
            for(Edge edge : adjList) {
                if(!priorityQueue.contains(edge)) {
                    priorityQueue.add(edge);
                }
            }
        }

        List<Edge> result = new ArrayList<>();
        QuickFindSet set = new QuickFindSet();
        set.makeSet(graph.vertex);
        while(!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.remove();
            int source = edge.source;
            int destination = edge.destination;
            if(set.find(source) != set.find(destination)) {
                result.add(edge);
                set.union(source, destination);
            }
        }
        result.forEach(edge -> System.out.println(edge.source +" "+ edge.destination+" "+edge.weight));
    }

    /**
     * Spanning Tree of an undirected graph
     *
     * TC: O(E)
     * SC: O(V)
     */
    public static void spanningTree(AdjacencyListWeightedGraph graph) {
        Set<Edge> spanningTree = new LinkedHashSet<>();
        for(int i=0; i<graph.vertex; i++) {
            LinkedList<Edge> list = graph.adjListArray[i];
            for(Edge edge : list) {
                if(!(graph.visited[edge.source] && graph.visited[edge.destination])) {
                    spanningTree.add(edge);
                    graph.visited[edge.source] = true;
                    graph.visited[edge.destination] = true;
                }
            }
        }
        for(Edge edge : spanningTree) {
            System.out.println(edge.source +" "+edge.destination);
        }
    }

    /**
     * Spanning Tree of an undirected graph (Using DFS)
     *
     * TC: O(E)
     * SC: O(V)
     */
    public static void spanningTreeDFS(AdjacencyListWeightedGraph graph) {
        Set<Edge> spanningTree = new LinkedHashSet<>();
        for(int i=0; i<graph.vertex; i++) {
            if(!graph.visited[i]) {
                graph.visited[i] = true;
                spanningTreeDFSUtil(i, graph, spanningTree);
            }
        }

        for(Edge edge : spanningTree) {
            System.out.println(edge.source +" "+edge.destination);
        }
    }

    private static void spanningTreeDFSUtil(int s, AdjacencyListWeightedGraph graph, Set<Edge> set) {
        List<Edge> list = graph.adjListArray[s];
        for(Edge e: list) {
            if(!graph.visited[e.destination]) {
                graph.visited[e.destination] = true;
                set.add(e);
                spanningTreeDFSUtil(e.destination, graph, set);
            }
        }
    }

    /**
     * Spanning Tree of an undirected graph (Using BFS)
     *
     * TC: O(E)
     * SC: O(V)
     */
    public static void spanningTreeBFS(AdjacencyListWeightedGraph graph) {
        Set<Edge> spanningTree = new LinkedHashSet<>();
        for(int i=0; i<graph.vertex; i++) {
            if(!graph.visited[i]) {
                graph.visited[i] = true;
                spanningTreeBFSUtil(i, graph, spanningTree);
            }
        }

        for(Edge edge : spanningTree) {
            System.out.println(edge.source +" "+edge.destination);
        }
    }

    private static void spanningTreeBFSUtil(int s, AdjacencyListWeightedGraph graph, Set<Edge> spanningTree) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        while(!queue.isEmpty()) {
            Integer current = queue.remove();
            graph.visited[current] = true;
            List<Edge> list = graph.adjListArray[current];
            for(Edge edge : list) {
                if(!graph.visited[edge.destination]) {
                    spanningTree.add(edge);
                    graph.visited[edge.destination] = true;
                    queue.add(edge.destination);
                }
            }
        }
    }
}
