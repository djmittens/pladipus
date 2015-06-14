package me.ngrid.graphs;

/**
 */
public interface Graph {
    boolean isEdge(int u, int v);
    boolean isEdge(int u , int v, int weight);
    int getNumVertices();
    int edgeWeight(int u, int v);

    void addEdge(int u, int v);
    void addEdge(int u, int v, int weight);
    void removeEdge(int u, int v);
}
