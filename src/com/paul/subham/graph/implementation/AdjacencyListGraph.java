package com.paul.subham.graph.implementation;

import java.util.LinkedList;

/**
 * Implementation of graph using adjacency list
 *
 * 1. contains an edge
 * 2. add an edge
 * 3. remove an edge
 * 4. no of edges
 * 5. degree of a vertex
 */
public class AdjacencyListGraph {
    int vertex;

    int edge;

    LinkedList<Integer>[] adjListArray;

    AdjacencyListGraph(int vertex) {
        this.vertex = vertex;
        edge = 0;
        adjListArray = new LinkedList[vertex];
        for(int i=0; i<vertex; i++) {
            adjListArray[i] = new LinkedList<>();
        }
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
    void addEdge(int u, int v) {
        if(!contains(u,v)) {
            adjListArray[u].add(v);
            //for undirected graph add extra
            //adjListArray[v].add(u);
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

    void print() {
        for(int i=0; i<vertex; i++) {
            LinkedList<Integer> list = adjListArray[i];
            for (Integer value : list) {
                System.out.println(i + " is connected to " + value);
            }
        }
    }
}
