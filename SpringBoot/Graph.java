package com.example.demo;

import java.util.List;

public class Graph {
    private List<String> nodes;
    private List<String> edges;
    private List<String> edge;

    public List<String> getEdge() {
        return edge;
    }

    public List<String> getEdges() {
        return edges;
    }

    public List<String> getNodes() {
        return nodes;
    }

    public void setEdge(List<String> edge) {
        this.edge = edge;
    }

    public void setEdges(List<String> edges) {
        this.edges = edges;
    }

    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }
}
