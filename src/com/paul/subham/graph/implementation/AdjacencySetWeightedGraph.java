package com.paul.subham.graph.implementation;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of graph using adjacency set
 *
 * 1. contains an edge
 * 2. add an edge
 * 3. remove an edge
 * 4. no of edges
 * 5. degree of a vertex
 */
public class AdjacencySetWeightedGraph {
    int vertex;

    int edge;

    Set<Edge>[] adjSetArray;

    AdjacencySetWeightedGraph(int vertex) {
        this.vertex = vertex;
        edge = 0;
        adjSetArray = new Set[vertex];
        for(int i=0; i<vertex; i++) {
            adjSetArray[i] = new HashSet<>();
        }
    }

    /**
     * contains an edge
     * TC: O(V)
     * SC: O(1)
     */
    boolean contains(int u, int v) {
        Set<Edge> set = adjSetArray[u];
        for(Edge e: set) {
            if(e.destination == v) {
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
    void addEdge(int u, int v, int w) {
        if(!contains(u,v)) {
            Edge e = new Edge(u,v,w);
            //Edge re = new Edge(v,u,w);
            adjSetArray[u].add(e);
            //for undirected graph add extra
            //adjListArray[v].add(re);
            edge++;
        }
    }

    /**
     * remove an edge
     * TC: O(V)
     * SC: O(1)
     */
    void removeEdge(int u, int v) {
        Set<Edge> set = adjSetArray[u];
        for(Edge e: set) {
            if(e.destination == v) {
                set.remove(e);
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
        return adjSetArray[v].size();
    }

    void print() {
        for(int i=0; i<vertex; i++) {
            Set<Edge> set = adjSetArray[i];
            for (Edge value : set) {
                System.out.println(i + " is connected to " + value.destination + " weight "
                        + value.weight);
            }
        }
    }
}
