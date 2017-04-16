
package herramienta;

import java.util.*;

public class WorkFlow {
    private String name;
    private String type;
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;

    public WorkFlow(String name, String type, ArrayList<Node> nodes, ArrayList<Edge> edges) {
        this.name = name;
        this.type = type;
        this.nodes = nodes;
        this.edges = edges;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
