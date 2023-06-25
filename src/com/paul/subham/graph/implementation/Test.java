package com.paul.subham.graph.implementation;

public class Test {
    public static void main(String[] args) {
        AdjacencyListWeightedGraph graph = new AdjacencyListWeightedGraph(10);
        graph.addEdge(1,2,3);
        graph.addEdge(2,4, 3);
        graph.addEdge(2, 5, 4);
        graph.DFSTraversal();
        //graph.print();
        //System.out.println(graph.edgeCount());
    }
}
