package com.example.demo;

import java.util.List;

public class GraphYaml {
    String name;
    List<String> nodes;
    List<String> source;
    List<String> destination;

    public GraphYaml(String name, List<String> nodes, List<String> source
            , List<String> destination) {
        this.name=name;
        this.nodes=nodes;
        this.source=source;
        this.destination=destination;
    }

    public GraphYaml(){}

    public String getName(){
        return name;
    }
    public List<String> getNodes(){
        return nodes;
    }
    public List<String> getSource(){
        return source;
    }
    public   List<String> getDestination(){
        return destination;
    }

    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }

    public void setSource(List<String> source) {
        this.source = source;
    }

    public void setDestination(List<String> destination) {
        this.destination = destination;
    }

    public void setName(String name) {
        this.name = name;
    }
}
