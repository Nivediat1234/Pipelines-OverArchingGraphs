package com.example.demo;

import java.util.List;

public class GraphAns {
    private List<GraphYaml> graphs;
    public GraphAns(){ }
    public GraphAns(List<GraphYaml> graphs) {
        this.graphs=graphs;
    }

    public List<GraphYaml> getGraphs() {
        return graphs;
    }

    public void setGraphs(List<GraphYaml> graphs) {
        this.graphs = graphs;
    }
}
