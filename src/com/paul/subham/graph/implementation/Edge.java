package com.paul.subham.graph.implementation;

public class Edge {
    public int source;
    public int destination;
    public int weight;

    Edge(int source, int destination, int weight) {
        this.source = source;
        this.weight = weight;
        this.destination = destination;
    }
}
