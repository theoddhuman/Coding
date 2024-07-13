package com.paul.subham.graph.operations;

import com.paul.subham.graph.implementation.AdjacencyListGraph;

import java.util.List;

/**
 * @author subham.paul
 *
 * 1. Transpose of a graph
 */
public class Manipulation {
    public static void main(String[] args) {
        AdjacencyListGraph graph = new AdjacencyListGraph(6);
        graph.addEdge(1,2);
        graph.addEdge(1,4);
        graph.addEdge(2,3);
        graph.addEdge(2,5);
        graph.addEdge(4,5);
        graph.addEdge(3,5);
        graph.BFTraversal();
        System.out.println();
        transpose(graph).BFTraversal();
    }

    /**
     * Transpose of a graph
     *
     * TC: O(V+E)
     * SC: O(1)
     */
    public static AdjacencyListGraph transpose(AdjacencyListGraph graph) {
        AdjacencyListGraph g = new AdjacencyListGraph(graph.vertex);
        for(int i=0; i<graph.vertex; i++) {
            List<Integer> list = graph.adjListArray[i];
            for(Integer j : list) {
                g.addEdge(j, i);
            }
        }
        return g;
    }
}
