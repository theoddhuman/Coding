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
 * 3. add an edge (undirected graph)
 * 4. remove an edge
 * 5. no of edges
 * 6. degree of a vertex
 * 7. Depth first search Traversal(Recursive)
 * 8. Depth first search Traversal(Iterative)
 * 9. Breadth first search traversal
 */
public class AdjacencyListGraph {
    public int vertex;

    int edge;

    public LinkedList<Integer>[] adjListArray;

    public boolean[] visited;

    public AdjacencyListGraph(int vertex) {
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
        LinkedList<Integer> list = adjListArray[u];
        for (Integer integer : list) {
            if (integer == v) {
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
    public void addEdge(int u, int v) {
        if(!contains(u,v)) {
            adjListArray[u].add(v);
            //for undirected graph add extra
            //adjListArray[v].add(u);
            edge++;
        }
    }

    /**
     * add an edge (undirected graph)
     * TC: O(V)
     * SC: O(1)
     */
    public void addEdgeUndirected(int u, int v) {
        if(!contains(u,v)) {
            adjListArray[u].add(v);
            adjListArray[v].add(u);
            edge++;
        }
    }

    /**
     * remove an edge
     * TC: O(V)
     * SC: O(1)
     */
    void removeEdge(int u, int v) {
        LinkedList<Integer> list = adjListArray[u];
        for(int i=0; i<list.size(); i++) {
            if(list.get(i) == v) {
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
    int edgeCount() {
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
     * TC: O(V+E)
     * SC: O(V)
     */
    public void DFSTraversal() {
        for(int i=0; i<vertex; i++) {
            if(!visited[i]) {
                DFS(i);
            }
        }
    }

    public void DFS(int x) {
        System.out.println(x);
        visited[x] = true;
        List<Integer> adjList = adjListArray[x];
        for(Integer i : adjList) {
            if(!visited[i]) {
                DFS(i);
            }
        }
    }

    /**
     * Depth first search Traversal(Iterative)
     * TC: O(V+E)
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
            LinkedList<Integer> adjList = adjListArray[current];
            for(Integer i : adjList) {
                if(!visited[i]) {
                    stack.push(i);
                }
            }
        }
    }

    /**
     * Breadth first search traversal
     * TC: O(V+E), If V = n, total possible edge = n*(n-1)/2, O(n^2)
     * SC: O(V)
     */
    public void BFTraversal() {
        for(int i=0; i<vertex; i++) {
            if(!visited[i]) {
                BFS(i);
            }
        }
    }

    public void BFS(int x) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(x);
        while(!queue.isEmpty()) {
            int current = queue.remove();
            if(visited[current]) {
                continue;
            }
            visited[current] = true;
            System.out.println(current);
            List<Integer> adjList = adjListArray[current];
            for(Integer i : adjList) {
                if(!visited[i]) {
                    queue.add(i);
                }
            }
        }
    }

    void print() {
        for(int i=0; i<vertex; i++) {
            LinkedList<Integer> list = adjListArray[i];
            for (Integer value : list) {
                System.out.println(i + " is connected to " + value);
            }
        }
    }
}
