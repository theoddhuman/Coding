package com.paul.subham.graph.operations;

import com.paul.subham.graph.implementation.AdjacencyListGraph;
import com.paul.subham.graph.implementation.AdjacencyListWeightedGraph;
import com.paul.subham.graph.implementation.AdjacencyMatrixWeightedGraph;
import com.paul.subham.graph.implementation.Edge;

import java.util.*;

/**
 * 1. Shortest path in unweighted graph
 * 2. Shortest path in a directed acyclic weighted graph
 * 3. Shortest path in a directed acyclic weighted graph (Topological Sorting)
 * 4. Shortest path in weighted graph - Dijkstra's Algorithm
 * 5. Shortest path in weighted graph - Dijkstra's Algorithm (Using Array)
 * 6. Shortest path in weighted graph - Bellman-ford Algorithm (BFS)
 * 7. Shortest path in weighted graph - detect negative cycle - Bellman-ford Algorithm (Iteration approach)
 * 8. Searching simple path from source to destination
 * 9. Count all simple paths from source to destination
 * 10. Transitive closure of a graph (Using Floyd Warshall Algorithm)
 * 11. Transitive closure of a graph (Using DFS)
 * 12. The shortest path of all pair (Using Floyd Warshall Algorithm)
 * 13. Shortest Path in binary matrix (0 pass through) (BFS)
 * 14. Path With Minimum Effort (Dijkstra)
 * 15. Cheapest Flights Within K Stops (BFS)
 * 16. Network Delay Time (BFS)
 * 17. No of ways to arrive at a destination in minimum time (BFS)
 * 18. Minimum Multiplications to reach End (BFS)
 * 19. Find the City With the Smallest Number of Neighbors at a Threshold Distance (Floyd Warshall Algorithm)
 * 20. Existence of arbitrage in a list of conversion rates (Using Bellman Ford Algorithm)
 */
public class Path {
    public static void main(String[] args) {
//        AdjacencyListGraph graph = new AdjacencyListGraph(10);
//        graph.addEdge(1,2);
//        graph.addEdge(1,4);
//        graph.addEdge(2,3);
//        graph.addEdge(2,5);
//        graph.addEdge(4,5);
//        graph.addEdge(3,5);
//        System.out.println(countAllSimplePaths(1,5,graph));
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
////        weightedShortestPathUsingArray(graph, 1);
//        AdjacencyMatrixWeightedGraph graph = new AdjacencyMatrixWeightedGraph(10);
//        graph.addEdge(1,2,1);
//        graph.addEdge(2,3,2);
//        graph.addEdge(3,5,3);
//        graph.addEdge(5,6,4);
//        graph.addEdge(5,4,2);
//        graph.addEdge(3,4,7);
//        allPairShortestPath(graph);
        List<Rate> rates = new ArrayList<>();
        rates.add(new Rate("USD", "EUR", 0.9));
        rates.add(new Rate("EUR", "GBP", 0.8));
        rates.add(new Rate("GBP", "USD", 1.5));
        System.out.println(canArbitrageExist(rates, "USD"));
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
     * Shortest path in a directed acyclic weighted graph
     *
     * TC: O(V+E), more than E as some edges can be repeated
     * SC: O(V)
     */
    public void shortestPath(int V, int E, List<List<Edge>> adj, int s) {
        int[] distance = new int[V];
        int[] path = new int[V];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        distance[s] = 0;
        path[s] = -1;
        while(!queue.isEmpty()){
            int current = queue.remove();
            List<Edge> list = adj.get(current);
            for(Edge edge : list) {
                int dest = edge.destination;
                int d = distance[current] + edge.weight;
                if (distance[dest] > d) {
                    distance[dest] = d;
                    queue.add(dest);
                }
            }
        }
        for(int i=0; i<V; i++) {
            System.out.println(path[i] + " " + distance[i]);
        }
    }

    /**
     * Shortest path in a directed acyclic weighted graph (Topological Sorting)
     *
     * TC: O(V+E), more than E as some edges can be repeated
     * SC: O(V)
     */
    public static void shortestPath(int V, AdjacencyListWeightedGraph graph, int s) {
        boolean[] visited = new boolean[V];
        int[] distance = new int[V];
        int[] path = new int[V];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[s] = 0;
        Stack<Integer> stack = new Stack<>();
        Sorting.dfsTopo(graph, s, visited, stack);
        while(!stack.isEmpty()) {
            int current = stack.pop();
            for(Edge edge : graph.adjListArray[current]) {
                int des = edge.destination;
                if(distance[des] > distance[current]+edge.weight) {
                    distance[des] = distance[current]+edge.weight;
                    path[des] = current;
                }
            }
        }
        for(int i=0; i<V; i++) {
            System.out.println(path[i] + " " + distance[i]);
        }
    }

    /**
     * Shortest path in weighted graph - Dijkstra's Algorithm
     *
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
        int[] distance = new int[graph.vertex];
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
     * Shortest path in weighted graph - Bellman-ford Algorithm (BFS)
     * TC: Check E edges V-1 times, O((V-1)E) = O(VE)
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
     * Shortest path in weighted graph - detect negative cycle - Bellman-ford Algorithm (Iteration approach)
     *
     * TC: Check E edges V-1 times, O((V-1)E) = O(VE)
     * If V = n, total possible edge = n*(n-1)/2, O(n * n*(n-1)/2) = O(n^3)
     * SC: O(V)
     *
     * It doesn't work if total weight of the circle is negative, it goes into infinite loop.
     */
    public static int[] bellmanFord(int V, ArrayList<ArrayList<Integer>> edges, int src) {
        int[] distance = new int[V];
        Arrays.fill(distance, 100000000);
        distance[src]=0;
        for(int i=0; i<V;i++) {
            for(ArrayList<Integer> list : edges) {
                int u = list.get(0);
                int v = list.get(1);
                int w = list.get(2);
                if(distance[u] != 100000000 && distance[u] + w < distance[v]) {
                    distance[v] = distance[u]+w;
                }
            }
        }
        for(ArrayList<Integer> list : edges) {
            int u = list.get(0);
            int v = list.get(1);
            int w = list.get(2);
            if(distance[u] != 100000000 && distance[u] + w < distance[v]) {
                return new int[]{-1};
            }
        }
        return distance;

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

    /**
     * The shortest path of all pair (Using Floyd Warshall Algorithm)
     *
     * This is only for adjacency matrix graph.
     *
     *
     * TC: O(V^3)
     * SC: O(V^2)
     */
    public static void allPairShortestPath(AdjacencyMatrixWeightedGraph graph) {
        int v = graph.vertex;
        int[][] distance = new int[v][v];
        for(int i=0; i< v; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }
        for(int i=0; i<v; i++) {
            for(int j=0; j<v; j++) {
                if(graph.adjMatrix[i][j] != 0) {
                    distance[i][j] = graph.adjMatrix[i][j];
                }
            }
        }
        for(int k=0; k<v; k++) {
            for(int i=0; i<v; i++) {
                for(int j=0; j<v; j++) {
                    if(distance[i][k] < Integer.MAX_VALUE && distance[k][j] < Integer.MAX_VALUE) {
                        int d = distance[i][k] + distance[k][j];
                        if (d < distance[i][j]) {
                            distance[i][j] = d;
                        }
                    }
                }
            }
        }
        for(int i=0; i<v; i++) {
            for(int j=0; j<v; j++) {
                if(distance[i][j] < Integer.MAX_VALUE) {
                    System.out.println(i + " " + j + " " + distance[i][j]);
                }
            }
        }
    }

    /**
     * Shortest Path in binary matrix (0 pass through) (BFS)
     *
     * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.
     *
     * A clear path in a binary matrix is a path from the source to destination such that:
     * a. All the visited cells of the path are 0.
     * b. All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
     * c. The length of a clear path is the number of visited cells of this path.
     *
     * Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
     * Output: 4
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public static int shortestPathBinaryMatrix(int[][] grid, int[] source, int[] destination) {
        if(grid[source[0]][source[1]]==1) {
            return -1;
        }
        if(source[0] == destination[0] && source[1] == destination[1]) {
            return 1;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] distance = new int[m][n];
        for(int i=0; i<m; i++) {
            Arrays.fill(distance[i],Integer.MAX_VALUE);
        }
        Queue<IndexPair> queue = new LinkedList<>();
        queue.add(new IndexPair(source[0],source[1]));
        distance[source[0]][source[1]]=1;
        int[] delRow = {-1,-1,-1,0,1,1,1,0};
        int[] delCol = {-1,0,1,1,1,0,-1,-1};
        while(!queue.isEmpty()) {
            IndexPair current = queue.remove();
            int i = current.r;
            int j = current.c;
            for(int k=0; k<8; k++) {
                int ni = i + delRow[k];
                int nj = j + delCol[k];
                if(ni>=0 && nj>=0 && ni<m && nj<n && grid[ni][nj] == 0 && distance[i][j] + 1 < distance[ni][nj]) {
                    distance[ni][nj] = distance[i][j]+1;
                    if(ni==destination[0] && nj==destination[1]) {
                        return distance[ni][nj];
                    }
                    queue.add(new IndexPair(ni,nj));
                }
            }
        }
        return -1;
    }


    /**
     * Path With Minimum Effort (Dijkstra)
     *
     * You are a hiker preparing for an upcoming hike.
     * You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of the cell (row, col).
     * You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e.,0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
     *
     * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
     *
     * TC: O(mn*log(mn))
     * SC: O(mn)
     */
    public static int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int[][] diff = new int[m][n];
        for(int i=0; i<m; i++){
            Arrays.fill(diff[i], Integer.MAX_VALUE);
        }
        PriorityQueue<IndexPair> pq = new PriorityQueue<>(Comparator.comparing(idxPair -> diff[idxPair.r][idxPair.c]));
        pq.add(new IndexPair(0,0));
        diff[0][0]=0;
        int[] delRow = {-1,0,1,0};
        int[] delCol = {0,1,0,-1};
        while(!pq.isEmpty()) {
            IndexPair current = pq.remove();
            int i = current.r;
            int j = current.c;
            for(int k=0; k<4; k++) {
                int ni = i + delRow[k];
                int nj = j + delCol[k];
                if(ni>=0 && nj>=0 && ni<m && nj<n) {
                    int newEffort = Math.max(Math.abs(heights[i][j]-heights[ni][nj]), diff[i][j]);
                    if(newEffort < diff[ni][nj]) {
                        diff[ni][nj]=newEffort;
                        pq.add(new IndexPair(ni,nj));
                    }
                }
            }
        }
        return diff[m-1][n-1];
    }

    /**
     * Cheapest Flights Within K Stops (BFS)
     *
     * There are n cities connected by some number of flights.
     * You are given an array flights where flights[i] = from[i], to[i], price[i] indicates that there is a flight from city from[i] to city to[i] with cost price[i].
     * You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.
     *
     * TC: O(V+ElogV)
     * SC: O(V+E)
     */
    public static int findCheapestPrice(int v, int[][] flights, int src, int dst, int k) {
        List<List<Edge>> adj = new ArrayList<>();
        for(int i=0; i<v; i++) {
            adj.add(new ArrayList<>());
        }
        for(int i=0; i<flights.length; i++) {
            adj.get(flights[i][0]).add(new Edge(flights[i][0], flights[i][1], flights[i][2]));
        }
        int[] minCost = new int[v];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        Queue<Tuple> pq = new LinkedList<>();
        minCost[src] = 0;
        pq.add(new Tuple(0, src, 0));
        while(!pq.isEmpty()){
            Tuple current = pq.remove();
            int cost = current.cost;
            int stopCount = current.stopCount;
            int node = current.node;
            if(stopCount > k) {
                continue;
            }
            List<Edge> list = adj.get(node);
            for(Edge edge : list) {
                int d = edge.weight + cost;
                int next = edge.destination;
                if(minCost[next] > d ) {
                    minCost[next] = d;
                    pq.add(new Tuple(stopCount+1, next, minCost[next]));
                }
            }
        }
        return minCost[dst] == Integer.MAX_VALUE?-1 : minCost[dst];
    }

    /**
     * Network Delay Time (BFS)
     *
     * You are given a network of n nodes, labeled from 1 to n.
     * You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi),
     * where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.
     *
     * We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal.
     * If it is impossible for all the n nodes to receive the signal, return -1.
     *
     * TC: O(V+E)
     * SC: O(V+E)
     */
    public static int networkDelayTime(int[][] times, int n, int k) {
        List<List<Edge>> adj = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            adj.add(new ArrayList<>());
        }
        for(int i=0; i<times.length; i++) {
            adj.get(times[i][0]).add(new Edge(times[i][0],times[i][1],times[i][2]));
        }
        int[] distance = new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparing(a->distance[a]));
        distance[k] = 0;
        pq.add(k);
        while(!pq.isEmpty()){
            int current = pq.remove();
            for(Edge edge: adj.get(current)) {
                int d = distance[current]+edge.weight;
                int next = edge.destination;
                if(distance[next] > d) {
                    pq.remove(next);
                    distance[next]=d;
                    pq.add(next);
                }
            }
        }
        int max = 0;
        for(int i=1;i<=n;i++) {
            //System.out.print(distance[i]+" ");
            if(distance[i] == Integer.MAX_VALUE) {
                return -1;
            }
            max = Math.max(max, distance[i]);
        }
        return max;
    }

    /**
     * No of ways to arrive at a destination in minimum time (BFS)
     *
     * ou are in a city that consists of n intersections numbered from 0 to n - 1 with bidirectional roads between some intersections.
     * The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between any two intersections.
     * You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that there is a road between intersections ui and vi that takes timei minutes to travel.
     * You want to know in how many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.
     *
     * Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer may be large, return it modulo 109 + 7.
     *
     * TC: O(V+E)
     * SC: O(E+V)
     */
    public int countPaths(int n, int[][] roads) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < roads.length; i++) {
            adj.get(roads[i][0]).add(new Pair(roads[i][1], roads[i][2]));
            adj.get(roads[i][1]).add(new Pair(roads[i][0], roads[i][2]));
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingLong(p->p.distance));
        long dist[] = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        long ways[] = new long[n];
        long mod = (int)(1e9 + 7);

        dist[0] = 0;
        ways[0] = 1;
        pq.add(new Pair(0, 0));
        while (!pq.isEmpty()) {
            Pair current = pq.remove();
            int node = current.node;
            for (Pair edge : adj.get(node)) {
                int adjNode = edge.node;
                long d = edge.distance + current.distance;
                if (d < dist[adjNode]) {
                    dist[adjNode] = d;
                    pq.add(new Pair(adjNode,d));
                    ways[adjNode] = ways[node];
                } else if (d == dist[adjNode]) {
                    ways[adjNode] = (ways[adjNode] + ways[node]) % mod;
                }
            }
        }
        return (int)(ways[n - 1] % mod);
    }

    /**
     * Minimum Multiplications to reach End (BFS)
     *
     * Given start, end and an array arr of n numbers.
     * At each step, start is multiplied with any number in the array and then mod operation with 100000 is done to get the new start.
     * Your task is to find the minimum steps in which end can be achieved starting from start.
     * If it is not possible to reach end, then return -1.
     * arr[] = {2, 5, 7}
     * start = 3, end = 30
     * Output: 2
     *
     * TC:O(100000)
     * SC:O(100000)
     */
    public static int minimumMultiplications(int[] arr, int start, int end) {
        if(start==end) {
            return 0;
        }
        Queue<Integer> queue = new LinkedList<>();
        int[] dist = new int[100000];
        Arrays.fill(dist, Integer.MAX_VALUE);
        queue.add(start);
        dist[start]=0;
        while(!queue.isEmpty()) {
            int current = queue.remove();
            for(int i=0;i<arr.length;i++) {
                int nextData = (current*arr[i])%100000;
                if(dist[nextData] > dist[current] + 1) {
                    dist[nextData] = dist[current]+1;
                    if(nextData == end) {
                        return dist[nextData];
                    }
                    queue.add(nextData);
                }
            }
        }
        return -1;
    }

    /**
     * Find the City With the Smallest Number of Neighbors at a Threshold Distance (Floyd Warshall Algorithm)
     *
     * There are n cities numbered from 0 to n-1.
     * Given the array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and
     * weighted edge between cities fromi and toi, and given the integer distanceThreshold.
     *
     * Return the city with the smallest number of cities that are reachable through some path
     * and whose distance is at most distanceThreshold, If there are multiple such cities, return the city with the greatest number.
     *
     * TC: O(n^3)
     * SC: O(n^2)
     */
    public static int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] a = new int[n][n];
        for(int i=0; i<edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];
            a[u][v] = w;
            a[v][u] = w;
        }
        int[][] distance = new int[n][n];
        for(int i=0; i<n; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(i==j){
                    distance[i][j] = 0;
                } else if(a[i][j] != 0) {
                    distance[i][j] = a[i][j];
                }
            }
        }
        for(int k=0;k<n;k++) {
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(distance[i][k]<Integer.MAX_VALUE && distance[k][j] <Integer.MAX_VALUE){
                        int d=distance[i][k] + distance[k][j];
                        if(distance[i][j] > d) {
                            distance[i][j] = d;
                        }
                    }
                }
            }
        }
        int min = Integer.MAX_VALUE;
        int index = -1;
        for(int i=0;i<n;i++) {
            int count = 0;
            for(int j=0;j<n;j++) {
                if(j!=i && distanceThreshold >= distance[i][j]) {
                    count++;
                }
            }
            if(count < min) {
                min = count;
                index = i;
            } else if (count==min) {
                index = Math.max(index, i);
            }
        }
        return index;
    }

    /**
     * Existence of arbitrage in a list of conversion rates (Using Bellman Ford Algorithm)
     *
     * TC: O(n^3)
     * SC: O(n)
     */
    public static boolean canArbitrageExist(List<Rate> rates, String start) {
        Map<String, List<Rate>> adjMap = new HashMap<>();
        for(Rate rate : rates) {
            rate.rate = -Math.log(rate.rate);
            adjMap.putIfAbsent(rate.from, new ArrayList<>());
            adjMap.get(rate.from).add(rate);
        }
        Map<String, Double> distances = new HashMap<>();
        for(String currency : adjMap.keySet()) {
            distances.put(currency, Double.MAX_VALUE);
        }
        distances.put(start, 0.0);
        for(int i=0; i<adjMap.size()-1; i++) {
            for(String currency : adjMap.keySet()) {
                for(Rate rate : adjMap.get(currency)) {
                    if(distances.get(rate.to) > distances.get(currency) + rate.rate) {
                        distances.put(rate.to, distances.get(currency) + rate.rate);
                    }
                }
            }
        }
        //finding negative cycle
        for(String currency : adjMap.keySet()) {
            for(Rate rate : adjMap.get(currency)) {
                if(distances.get(rate.to) > distances.get(rate.from) + rate.rate) {
                    return true;
                }
            }
        }
        return false;
    }
}

class Pair {
    int node;
    long distance;
    Pair(int node, long distance) {
        this.node = node;
        this.distance = distance;
    }
}

class Tuple {
    int stopCount;
    int node;
    int cost;
    Tuple(int stopCount, int node, int cost) {
        this.stopCount = stopCount;
        this.node = node;
        this.cost = cost;
    }
}
