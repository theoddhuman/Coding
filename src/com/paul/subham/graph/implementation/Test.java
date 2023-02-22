package com.paul.subham.graph.implementation;

public class Test {
    public static void main(String[] args) {
        AdjacencySetWeightedGraph graph = new AdjacencySetWeightedGraph(10);
        graph.addEdge(1,2,4);
        graph.addEdge(2,4,3);
        graph.addEdge(2, 5, 6);
        graph.print();
        System.out.println(graph.edgeCount());
        graph.removeEdge(1,2);
        graph.print();
        System.out.println(graph.edgeCount());
    }
}
