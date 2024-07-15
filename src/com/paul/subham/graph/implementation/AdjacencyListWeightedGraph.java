package com.paul.subham.graph.implementation;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Implementation of graph using adjacency list
 *
 * 1. contains an edge
 * 2. add an edge
 * 3. add an edge undirected
 * 4. remove an edge
 * 5. no of edges
 * 6. degree of a vertex
 * 7. Depth first search Traversal(Recursive)
 * 8. Depth first search Traversal(Iterative)
 * 9. Breadth first search traversal
 */
public class AdjacencyListWeightedGraph {
    public int vertex;

    int edge;

    public LinkedList<Edge>[] adjListArray;

    public boolean[] visited;

    public AdjacencyListWeightedGraph(int vertex) {
        this.vertex = vertex;
        edge = 0;
        adjListArray = new LinkedList[vertex];
        for(int i=0; i<vertex; i++) {
            adjListArray[i] = new LinkedList<>();
        }
        visited = new boolean[vertex];
    }

    /**
     * contains an edge
     * TC: O(V)
     * SC: O(1)
     */
    boolean contains(int u, int v) {
        LinkedList<Edge> list = adjListArray[u];
        for (Edge value : list) {
            if (value.destination == v) {
                return true;
            }
        }
        return false;
    }

    /**
     * add an edge
     * TC: O(V)
     * SC: O(1)
     */
    public void addEdge(int u, int v, int w) {
        if(!contains(u,v)) {
            Edge e = new Edge(u,v,w);
            adjListArray[u].add(e);
            edge++;
        }
    }

    /**
     * Add an edge undirected
     *
     * TC: O(V)
     * SC: O(1)
     */
    public void addEdgeUndirected(int u, int v, int w) {
        addEdge(u,v,w);
        addEdge(v,u,w);
    }

    /**
     * remove an edge
     * TC: O(V)
     * SC: O(1)
     */
     void removeEdge(int u, int v) {
        LinkedList<Edge> list = adjListArray[u];
        for(int i=0; i<list.size(); i++) {
            if(list.get(i).destination == v) {
                list.remove(i);
                edge--;
                break;
            }
        }
    }

    /**
     * no of edges
     * TC: O(1)
     * SC: O(1)
     */
    public int edgeCount() {
        return edge;
    }

    /**
     * degree of a vertex
     * TC: O(1)
     * SC: O(1)
     */
    int degree(int v){
        return adjListArray[v].size();
    }

    /**
     * Depth first search Traversal(Recursive)
     * TC: O(V+E), If V = n, total possible edge = n*(n-1)/2, O(n^2)
     * SC: O(V)
     */
    void DFSTraversal() {
        for(int i=0; i<vertex; i++) {
            if(!visited[i]) {
                DFS(i);
            }
        }
    }

    void DFS(int x) {
        System.out.println(x);
        visited[x] = true;
        List<Edge> adjList = adjListArray[x];
        for(Edge edge : adjList) {
            if(!visited[edge.destination]) {
                DFS(edge.destination);
            }
        }
    }

    /**
     * Depth first search Traversal(Iterative)
     * TC: O(V+E), If V = n, total possible edge = n*(n-1)/2, O(n^2)
     * SC: O(V)
     */
    void DFSTraversalIterative() {
        for(int i=0; i<vertex; i++) {
            if(!visited[i]) {
                DFSIterative(i);
            }
        }
    }

    void DFSIterative(int x) {
        Stack<Integer> stack = new Stack<>();
        stack.push(x);
        while(!stack.empty()) {
            int current = stack.pop();
            visited[current] = true;
            System.out.println(current);
            LinkedList<Edge> adjList = adjListArray[current];
            for(Edge edge : adjList) {
                if(!visited[edge.destination]) {
                    stack.push(edge.destination);
                }
            }
        }
    }

    /**
     * Breadth first search traversal
     * TC: O(V+E), If V = n, total possible edge = n*(n-1)/2, O(n^2)
     * SC: O(V)
     */
    void BFTraversal() {
        for(int i=0; i<vertex; i++) {
            if(!visited[i]) {
                BFS(i);
            }
        }
    }

    void BFS(int x) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(x);
        while(!queue.isEmpty()) {
            int current = queue.remove();
            visited[current] = true;
            System.out.println(current);
            List<Edge> adjList = adjListArray[current];
            for(Edge edge : adjList) {
                if(!visited[edge.destination]) {
                    queue.add(edge.destination);
                }
            }
        }
    }

    void print() {
        for(int i=0; i<vertex; i++) {
            LinkedList<Edge> list = adjListArray[i];
            for (Edge value : list) {
                System.out.println(i + " is connected to " + value.destination + " weight "
                        + value.weight);
            }
        }
    }
}


