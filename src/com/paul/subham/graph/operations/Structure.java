package com.paul.subham.graph.operations;

import com.paul.subham.graph.implementation.AdjacencyListGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 1. Get level of each node from source in a graph (using BFS)
 * 2. Count of nodes at a level from source (using BFS)
 * 3. Counting no of tress in a forest (Using DFS)
 * 4. Counting no of tress in a forest (Using BFS)
 * 5. k core of an undirected graph
 * 6. Cut vertex or articulation points of a graph
 * 7. Cut vertex or articulation points of a graph (Tarjan's algorithm)
 * 8. Cut bridge or cut edge of a graph (Tarjan's algorithm)
 * 9. Is connected an undirected graph
 * 10. Is undirected graph an Eulerian Path
 * 11. Is undirected graph an Eulerian Path
 * 12. Strongly connected components of a directed graph
 * 13. Strongly connected components of a directed graph (Kosaraju's algorithm)
 * 14. Is the directed graph strongly connected
 * 15. Is the directed graph Eulerian Cycle
 * 16. Detecting cycle in undirected graph (Using DFS)
 * 17. Detecting cycle in undirected graph (Using BFS)
 * 18. Detecting cycle in directed graph (Using DFS)
 * 19. Detecting cycle in directed graph (Using BFS - Topological Sorting)
 * 20. Depth of undirected acyclic graph from a source (Using BFS)
 * 21. Depth of directed acyclic graph (Using BFS)
 */
public class Structure {
    public static void main(String[] args) {
        AdjacencyListGraph g1 = new AdjacencyListGraph(10);
        g1.addEdge(1, 2);
        g1.addEdge(2, 4);
        g1.addEdge(5, 3);
        g1.addEdge(4, 5);
        g1.addEdge(5, 6);
        System.out.println(depthDirected(g1));
//        g1.addEdge(2,3);
//        g1.addEdge(3,4);
//        g1.addEdge(3,6);
//        g1.addEdge(4,1);
//        g1.addEdge(4,5);
//        g1.addEdge(5,6);
//        g1.addEdge(6,7);
//        g1.addEdge(7,5);
//        g1.addEdgeUndirected(1,2);
//        g1.addEdgeUndirected(1,3);
//        g1.addEdgeUndirected(3,4);
//        g1.addEdgeUndirected(4,5);
//        g1.addEdgeUndirected(4,6);
//        g1.addEdgeUndirected(0, 1);
//        g1.addEdgeUndirected(0, 2);
//        g1.addEdgeUndirected(1, 2);
//        g1.addEdgeUndirected(1, 5);
//        g1.addEdgeUndirected(2, 3);
//        g1.addEdgeUndirected(2, 4);
//        g1.addEdgeUndirected(2, 5);
//        g1.addEdgeUndirected(2, 6);
//        g1.addEdgeUndirected(3, 4);
//        g1.addEdgeUndirected(3, 6);
//        g1.addEdgeUndirected(3, 7);
//        g1.addEdgeUndirected(4, 6);
//        g1.addEdgeUndirected(4, 7);
//        g1.addEdgeUndirected(5, 6);
//        g1.addEdgeUndirected(5, 8);
//        g1.addEdgeUndirected(6, 7);
//        g1.addEdgeUndirected(6, 8);
//        printKCore(g1, 3);
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
        while (!queue.isEmpty()) {
            int current = queue.remove();
            LinkedList<Integer> adjList = graph.adjListArray[current];
            for (Integer i : adjList) {
                if (!visited[i]) {
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
        for (int i = 0; i < graph.vertex; i++) {
            if (!graph.visited[i]) {
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
        for (int i = 0; i < graph.vertex; i++) {
            if (!graph.visited[i]) {
                graph.BFS(i);
                trees++;
            }
        }
        return trees;
    }

    /**
     * k core of an undirected graph
     * <p>
     * k core of the graph are connected components that are left after all vertices of degree less than k have been removed.
     * <p>
     * TC: O(V+E)
     * SC: O(V)
     */
    public static void printKCore(AdjacencyListGraph graph, int k) {
        int[] degree = new int[graph.vertex];
        int minDegree = Integer.MAX_VALUE;
        int startVertex = 0;
        for (int i = 0; i < graph.vertex; i++) {
            degree[i] = graph.adjListArray[i].size();
            if (degree[i] < minDegree) {
                minDegree = degree[i];
                startVertex = i;
            }
        }
        dfsKCoreUtil(startVertex, degree, k, graph);
        for (int i = 0; i < graph.vertex; i++) {
            if (!graph.visited[i]) {
                dfsKCoreUtil(i, degree, k, graph);
            }
        }
        for (int i = 0; i < graph.vertex; i++) {
            if (degree[i] >= k) {
                System.out.print(i);
                for (Integer j : graph.adjListArray[i]) {
                    if (degree[j] >= k) {
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
        for (Integer i : adjList) {
            if (degree[s] < k) {
                degree[i]--;
            }
            if (!graph.visited[i]) {
                dfsKCoreUtil(i, degree, k, graph);
            }
        }
    }

    /**
     * Cut vertex or articulation points of a graph
     * <p>
     * A vertex in an undirected connected graph is a articulation point if removing it disconnects the graph.
     * <p>
     * TC: O(V(V+E)) = O(n^3)
     * SC: O(V+E)
     */
    public static void articulationPoint(AdjacencyListGraph graph) {
        for (int i = 0; i < graph.vertex; i++) {
            Arrays.fill(graph.visited, false);
            int component = 0;
            graph.visited[i] = true;
            for (int j = 0; j < graph.vertex; j++) {
                if (i != j) {
                    // checking size to ignore vertices which are not connected to any other vertices
                    if (!graph.visited[j] && graph.adjListArray[j].size() > 0) {
                        dfs(graph, j);
                        component++;
                    }
                }
            }
            if (component > 1) {
                System.out.print(i + " ");
            }
        }
    }

    private static void dfs(AdjacencyListGraph graph, int s) {
        graph.visited[s] = true;
        List<Integer> list = graph.adjListArray[s];
        for (Integer i : list) {
            if (!graph.visited[i]) {
                dfs(graph, i);
            }
        }
    }

    /**
     * Cut vertex or articulation points of a graph (Tarjan's algorithm)
     * <p>
     * In DFS tree, a vertex u is an articulation point if one of the following two conditions is true.
     * a. u is the root of the DFS tree and has at least two children.
     * b. u is not the root of the DFS tree and has a child v such that no vertex in the subtree rooted with v has
     * a back edge to one of the ancestors in DFS tree of u.
     * <p>
     * TC: O(V+E) = O(n^2)
     * SC: O(V)
     */
    public static void articulationPointTarjan(AdjacencyListGraph graph) {
        int v = graph.vertex;
        int[] discovery = new int[v];
        int[] low = new int[v];
        boolean[] ap = new boolean[v];
        for (int i = 0; i < v; i++) {
            if (!graph.visited[i]) {
                APUtil(i, discovery, low, -1, ap, graph);
            }
        }
        for (int i = 0; i < v; i++) {
            if (ap[i]) {
                System.out.print(i + " ");
            }
        }
    }

    private static int time = 0;

    private static void APUtil(int s, int[] discovery, int[] low, int parent, boolean[] ap, AdjacencyListGraph graph) {
        graph.visited[s] = true;
        discovery[s] = low[s] = ++time;
        List<Integer> adjList = graph.adjListArray[s];
        int child = 0;
        for (Integer i : adjList) {
            if (!graph.visited[i]) {
                child++;
                APUtil(i, discovery, low, s, ap, graph);
                low[s] = Math.min(low[s], low[i]);
                if (parent != -1 && low[i] >= discovery[s]) {
                    ap[s] = true;
                }
            } else if (parent != i) {
                low[s] = Math.min(low[s], discovery[i]);
            }
        }
        if (parent == -1 && child > 1) {
            ap[s] = true;
        }
    }

    /**
     * Cut bridge or cut edge of a graph (Tarjan's algorithm)
     * <p>
     * TC: O(V+E) = O(n^2)
     * SC: O(V)
     */
    public static void cutEdge(AdjacencyListGraph graph) {
        int v = graph.vertex;
        int[] discovery = new int[v];
        int[] low = new int[v];
        boolean[] ap = new boolean[v];
        for (int i = 0; i < v; i++) {
            if (!graph.visited[i]) {
                cutEdgeUtil(i, discovery, low, -1, ap, graph);
            }
        }
    }

    private static void cutEdgeUtil(int s, int[] discovery, int[] low, int parent, boolean[] ap, AdjacencyListGraph graph) {
        graph.visited[s] = true;
        discovery[s] = low[s] = ++time;
        List<Integer> adjList = graph.adjListArray[s];
        for (Integer i : adjList) {
            if (!graph.visited[i]) {
                cutEdgeUtil(i, discovery, low, s, ap, graph);
                low[s] = Math.min(low[s], low[i]);
                if (low[i] >= discovery[s] && graph.adjListArray[i].size() > 1) {
                    System.out.println(s + " " + i);
                }
            } else if (parent != i) {
                low[s] = Math.min(low[s], discovery[i]);
            }
        }
    }

    /**
     * Is connected an undirected graph
     * <p>
     * TC: O(V+E) = O(n^2)
     * SC: O(V)
     */
    public static boolean isConnectedUndirectedGraph(AdjacencyListGraph graph) {
        int startVertex = 0;
        for (int i = 0; i < graph.vertex; i++) {
            if (graph.adjListArray[i].size() != 0) {
                startVertex = i;
                break;
            }
        }
        if (startVertex == graph.vertex - 1) {
            return true;
        }
        graph.DFS(startVertex);
        for (int i = 0; i < graph.vertex; i++) {
            if (!graph.visited[i] && graph.adjListArray[i].size() > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Is undirected graph an Eulerian Path
     * <p>
     * Eulerian Path is a path in a graph that visits every edge exactly once.
     * a. All vertices with non-zero degree are connected.
     * b. If zero or two vertices have odd degree and all other vertices have even degree
     * <p>
     * TC: O(V+E) = O(n^2)
     * SC: O(V)
     */
    public static boolean isEulerianPath(AdjacencyListGraph graph) {
        if (!isConnectedUndirectedGraph(graph)) {
            return false;
        }
        int odd = 0;
        for (int i = 0; i < graph.vertex; i++) {
            if (graph.adjListArray[i].size() % 2 != 0) {
                odd++;
            }
        }
        return odd == 0 || odd == 2;
    }

    /**
     * Is undirected graph an Eulerian Cycle
     * <p>
     * Eulerian Circuit is an Eulerian Path that starts and ends on the same vertex.
     * a. All vertices with non-zero degree are connected.
     * b. All vertices have even degree.
     * <p>
     * TC: O(V+E) = O(n^2)
     * SC: O(V)
     */
    public static boolean isEulerianCycle(AdjacencyListGraph graph) {
        if (!isConnectedUndirectedGraph(graph)) {
            return false;
        }
        int odd = 0;
        for (int i = 0; i < graph.vertex; i++) {
            if (graph.adjListArray[i].size() % 2 != 0) {
                odd++;
            }
        }
        return odd == 0;
    }

    /**
     * Strongly connected components of a directed graph
     * <p>
     * A strongly connected component of a directed graph is a maximal subgraph where every pair of vertices is mutually reachable.
     * <p>
     * TC: O(V(V+E)) = O(n^3)
     * SC: O(V)
     */
    private static void stronglyConnectedComponents(AdjacencyListGraph graph) {
        List<List<Integer>> stronglyConnectedComponents = new ArrayList<>();
        int v = graph.vertex;
        for (int i = 0; i < v; i++) {
            //To check if the vertex is already part of any connected component
            if (!graph.visited[i]) {
                List<Integer> connectedComponent = new ArrayList<>();
                connectedComponent.add(i);
                for (int j = i + 1; j < v; j++) {
                    if (!graph.visited[j] && Path.hasSimplePath(i, j, graph) && Path.hasSimplePath(j, i, graph)) {
                        graph.visited[j] = true;
                        connectedComponent.add(j);
                    }
                }
                stronglyConnectedComponents.add(connectedComponent);
            }
        }
        for (List<Integer> component : stronglyConnectedComponents) {
            component.forEach(c -> System.out.print(c + " "));
            System.out.println();
        }
    }

    /**
     * Strongly connected components of a directed graph (Kosaraju's algorithm)
     * <p>
     * a. DFS on Original Graph: Record finish times.
     * b. Transpose the Graph: Reverse all edges.
     * c. DFS on Transposed Graph: Process nodes in order of decreasing finish times to find SCCs.
     * <p>
     * TC: O(V+E) = O(n^2)
     * SC: O(V)
     */
    public static void stronglyConnectedComponentsKosaraju(AdjacencyListGraph graph) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < graph.vertex; i++) {
            if (!graph.visited[i]) {
                fillOrder(i, graph, stack);
            }
        }
        AdjacencyListGraph transposedGraph = Manipulation.transpose(graph);
        List<List<Integer>> stronglyConnectedComponents = new ArrayList<>();
        while (!stack.empty()) {
            int i = stack.pop();
            if (!transposedGraph.visited[i]) {
                List<Integer> component = new ArrayList<>();
                dfs(i, transposedGraph, component);
                stronglyConnectedComponents.add(component);
            }
        }
        for (List<Integer> component : stronglyConnectedComponents) {
            component.forEach(c -> System.out.print(c + " "));
            System.out.println();
        }
    }

    private static void dfs(int s, AdjacencyListGraph graph, List<Integer> component) {
        graph.visited[s] = true;
        component.add(s);
        List<Integer> list = graph.adjListArray[s];
        for (Integer i : list) {
            if (!graph.visited[i]) {
                dfs(i, graph, component);
            }
        }
    }

    private static void fillOrder(int s, AdjacencyListGraph graph, Stack<Integer> stack) {
        graph.visited[s] = true;
        List<Integer> list = graph.adjListArray[s];
        for (Integer i : list) {
            if (!graph.visited[i]) {
                fillOrder(i, graph, stack);
            }
        }
        stack.push(s);
    }

    /**
     * Strongly connected components of a directed graph (Tarjan's algorithm)
     * <p>
     * Tarjan’s Algorithm is more efficient because it finds SCCs in a single DFS pass using a stack and some additional bookkeeping:
     * DFS Traversal: During the DFS, maintain an index for each node and the smallest index (low-link value) that can be reached from the node.
     * Stack: Keep track of nodes currently in the recursion stack (part of the current SCC being explored).
     * Identifying SCCs: When a node’s low-link value equals its index, it means we have found an SCC. Pop all nodes from the stack until we reach the current node.
     * <p>
     * TC: O(V+E) = O(n^2)
     * SC: O(V)
     */
    public static void stronglyConnectedComponentsTarjan(AdjacencyListGraph graph) {
        int v = graph.vertex;
        int[] discovery = new int[v];
        int[] low = new int[v];
        boolean[] stackMember = new boolean[v];
        Stack<Integer> stack = new Stack<>();
        List<List<Integer>> connectedComponents = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            if (!graph.visited[i]) {
                tarjanUtil(i, graph, discovery, low, stackMember, stack, connectedComponents);
            }
        }
        for (List<Integer> component : connectedComponents) {
            component.forEach(c -> System.out.print(c + " "));
            System.out.println();
        }
    }

    private static void tarjanUtil(int s, AdjacencyListGraph graph, int[] discovery, int[] low, boolean[] stackMember,
                                   Stack<Integer> stack, List<List<Integer>> connectedComponents) {
        graph.visited[s] = true;
        discovery[s] = low[s] = ++time;
        stackMember[s] = true;
        stack.push(s);
        List<Integer> list = graph.adjListArray[s];
        for (Integer i : list) {
            if (!graph.visited[i]) {
                tarjanUtil(i, graph, discovery, low, stackMember, stack, connectedComponents);
                low[s] = Math.min(low[s], low[i]);
            } else if (stackMember[i]) {
                // Comparing with discovery instead of low because low signifies the discovery of  head of the component.
                // low value must be part of same strongly connected component, if we consider low instead of discovery it may get
                // the low value of other connected component, which is wrong.
                low[s] = Math.min(low[s], discovery[i]);
            }
        }
        if (low[s] == discovery[s]) {
            int w;
            List<Integer> component = new ArrayList<>();
            do {
                w = stack.pop();
                component.add(w);
            } while (w != s);
            connectedComponents.add(component);
        }
    }

    /**
     * Is the directed graph strongly connected
     * <p>
     * TC: O(V+E) = O(n^2)
     * SC: O(V)
     */
    public static boolean isStronglyConnected(AdjacencyListGraph graph) {
        int startVertex = 0;
        for (int i = 0; i < graph.vertex; i++) {
            if (graph.adjListArray[i].size() > 0) {
                startVertex = i;
                break;
            }
        }
        graph.DFS(startVertex);
        for (int i = 0; i < graph.vertex; i++) {
            if (i != startVertex && !graph.visited[i] && graph.adjListArray[i].size() > 0) {
                return false;
            }
        }
        AdjacencyListGraph trGraph = Manipulation.transpose(graph);
        for (int i = 0; i < trGraph.vertex; i++) {
            if (trGraph.adjListArray[i].size() > 0) {
                startVertex = i;
                break;
            }
        }
        trGraph.DFS(startVertex);
        for (int i = 0; i < trGraph.vertex; i++) {
            if (i != startVertex && !trGraph.visited[i] && trGraph.adjListArray[i].size() > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Is the directed graph Eulerian Cycle
     * <p>
     * a. All vertices with nonzero degree belong to a single strongly connected component.
     * b. In degree is equal to the out degree for every vertex.
     * <p>
     * TC: O(V+E) = O(n^2)
     * SC: O(V)
     */
    public static boolean isEulerianCycleDirected(AdjacencyListGraph graph) {
        if (!isStronglyConnected(graph)) {
            return false;
        }
        int[] indegree = new int[graph.vertex];
        for (int i = 0; i < graph.vertex; i++) {
            List<Integer> list = graph.adjListArray[i];
            for (Integer j : list) {
                indegree[j]++;
            }
        }
        for (int i = 0; i < graph.vertex; i++) {
            if (graph.adjListArray[i].size() != indegree[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Detecting cycle in undirected graph (Using DFS)
     * <p>
     * TC: O(V+E) = O(n^2)
     * SC: O(V)
     */
    public static boolean isCyclicUndirected(AdjacencyListGraph graph) {
        for (int i = 0; i < graph.vertex; i++) {
            if (!graph.visited[i]) {
                if (isCyclicUndirectedUtil(i, graph, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isCyclicUndirectedUtil(int s, AdjacencyListGraph graph, int parent) {
        graph.visited[s] = true;
        List<Integer> list = graph.adjListArray[s];
        for (Integer i : list) {
            if (!graph.visited[i]) {
                if (isCyclicUndirectedUtil(i, graph, s)) {
                    return true;
                }
            } else if (parent != i) {
                return true;
            }
        }
        return false;
    }

    /**
     * Detecting cycle in undirected graph (Using BFS)
     * <p>
     * TC: O(V+E) = O(n^2)
     * SC: O(V)
     */
    public static boolean isCyclicUndirectedBFS(AdjacencyListGraph graph) {
        int[] parent = new int[graph.vertex];
        for (int i = 0; i < graph.vertex; i++) {
            if (!graph.visited[i]) {
                if (isCyclicUndirectedBFSUtil(i, graph, parent)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isCyclicUndirectedBFSUtil(int s, AdjacencyListGraph graph, int[] parent) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        parent[s] = -1;
        while (!queue.isEmpty()) {
            Integer current = queue.remove();
            graph.visited[current] = true;
            List<Integer> list = graph.adjListArray[current];
            for (Integer i : list) {
                if (!graph.visited[i]) {
                    queue.add(i);
                    parent[i] = current;
                } else if (parent[current] != i) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Detecting cycle in directed graph (Using DFS)
     * <p>
     * TC: O(V+E) = O(n^2)
     * SC: O(V)
     */
    public static boolean isCyclicDirectedDFS(AdjacencyListGraph graph) {
        boolean[] recStack = new boolean[graph.vertex];
        for (int i = 0; i < graph.vertex; i++) {
            if (!graph.visited[i]) {
                if (isCyclicUtil(i, graph, recStack)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isCyclicUtil(int s, AdjacencyListGraph graph, boolean[] recStack) {
        graph.visited[s] = true;
        recStack[s] = true;
        List<Integer> list = graph.adjListArray[s];
        for (Integer i : list) {
            if (!graph.visited[i]) {
                if (isCyclicUtil(i, graph, recStack)) {
                    return true;
                }
            } else if (recStack[i]) {
                return true;
            }
        }
        recStack[s] = false;
        return false;
    }

    /**
     * Detecting cycle in directed graph (Using BFS - Topological Sorting)
     * <p>
     * TC: O(V+E) = O(n^2)
     * SC: O(V)
     */
    public static boolean isCyclicDirectedTopologicalSortBFS(AdjacencyListGraph graph) {
        int[] indegree = new int[graph.vertex];
        int counter = 0;
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<graph.vertex; i++) {
            List<Integer> adjList = graph.adjListArray[i];
            for(Integer j : adjList) {
                indegree[j]++;
            }
        }
        for(int i=0; i<graph.vertex; i++) {
            if(indegree[i] == 0) {
                queue.add(i);
            }
        }
        while(!queue.isEmpty()) {
            int current = queue.remove();
            List<Integer> adjList = graph.adjListArray[current];
            for(Integer i : adjList) {
                if(--indegree[i] == 0) {
                    queue.add(i);
                }
            }
        }
        return counter != graph.vertex;
    }

    /**
     * Depth of undirected acyclic graph from a source (Using BFS)
     *
     * TC: O(V+E) = O(n^2)
     * SC: O(V)
     */
    private static int depthUndirectedUtil(int s, AdjacencyListGraph graph) {
        int[] distance = new int[graph.vertex];
        Arrays.fill(distance, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        distance[s] = 0;
        while(!queue.isEmpty()) {
            int current = queue.remove();
            graph.visited[current] = true;
            List<Integer> list = graph.adjListArray[current];
            for(Integer i : list) {
                if(!graph.visited[i]) {
                    distance[i] = distance[current] + 1;
                    queue.add(i);
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for(int j : distance) {
            max = Math.max(j, max);
        }
        return max;
    }

    /**
     * Depth of directed acyclic graph (Using BFS)
     *
     * TC: O(V+E) = O(n^2)
     * SC: O(V)
     */
    public static int depthDirected(AdjacencyListGraph graph) {
        int[] indegree = new int[graph.vertex];
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<graph.vertex; i++) {
            List<Integer> list = graph.adjListArray[i];
            for(Integer j : list) {
                indegree[j] ++;
            }
        }
        for(int i=0; i<graph.vertex; i++)  {
            if(indegree[i] == 0) {
                queue.add(i);
            }
        }
        int count = 0;
        while(!queue.isEmpty()) {
            int nodeCount = queue.size();
            count++;
            while(nodeCount-->0) {
                int current = queue.remove();
                List<Integer> list = graph.adjListArray[current];
                for(Integer i : list) {
                    if(--indegree[i] == 0) {
                        queue.add(i);
                    }
                }
            }
        }
        return count-1;
    }
}
