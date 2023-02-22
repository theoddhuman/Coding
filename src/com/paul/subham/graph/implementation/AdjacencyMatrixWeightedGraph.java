package com.paul.subham.graph.implementation;

/**
 * Implementation of graph using adjacency matrix
 *
 * 1. add unweighted edge
 * 2. add weighted edge
 * 3. contains a edge
 * 4. remove a edge
 * 5. get no of edges
 */
public class AdjacencyMatrixWeightedGraph {
    int vertex;
    int edge;
    int[][] adjMatrix;

    public AdjacencyMatrixWeightedGraph(int vertex) {
        this.vertex = vertex;
        edge = 0;
        adjMatrix = new int[vertex][vertex];
    }

    /**
     * add unweighted edge
     * TC: O(1)
     * SC: O(1)
     */
    void addEdge(int u, int v) {
        if(contains(u, v)) {
            adjMatrix[u][v] = 1;
            //for undirected graph add extra
            //adjMatrix[v][u] = 1;
            edge++;
        }
    }

    /**
     * add weighted edge
     * TC: O(1)
     * SC: O(1)
     */
    void addEdge(int u, int v, int w) {
        if(!contains(u, v)) {
            adjMatrix[u][v] = w;
            //for undirected graph add extra
            //adjMatrix[v][u] = w;
            edge++;
        }
    }

    /**
     * contains a edge
     * TC: O(1)
     * SC: O(1)
     */
    boolean contains(int u, int v) {
        return adjMatrix[u][v] > 0;
    }

    /**
     * remove edge
     * TC: O(1)
     * SC: O(1)
     */
    void removeEdge(int u, int v) {
        if(contains(u, v)) {
            adjMatrix[u][v] = 0;
            //for undirected graph add extra
            //adjMatrix[v][u] = 0;
            edge--;
        }
    }

    /**
     * get no of edges
     * TC: O(1)
     * SC: O(1)
     */
    int edgeCount() {
        return edge;
    }

    void print() {
        System.out.print(" ");
        for(int i=0; i<vertex; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for(int i=0; i<vertex; i++) {
            System.out.print(i + " ");
            for(int j=0; j< vertex; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
