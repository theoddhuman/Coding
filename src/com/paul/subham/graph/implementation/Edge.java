package com.paul.subham.graph.implementation;

public class Edge {
    int source;
    int destination;
    int weight;

    Edge(int source, int destination, int weight) {
        this.source = source;
        this.weight = weight;
        this.destination = destination;
    }
}
