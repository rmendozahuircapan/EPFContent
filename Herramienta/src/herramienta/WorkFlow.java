
package Herramienta;

import java.util.*;

public class WorkFlow {
    private String id;
    private String name;
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;

    public WorkFlow(String id, String name, ArrayList<Node> nodes, ArrayList<Edge> edges) {
        this.id = id;
        this.name = name;
        this.nodes = nodes;
        this.edges = edges;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }

    
}
