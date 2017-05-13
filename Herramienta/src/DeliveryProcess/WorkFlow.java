
package DeliveryProcess;

import java.util.*;
import javax.swing.*;

public class WorkFlow {
    private String id;
    private String name;
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    private ArrayList<Position> positions;

    public WorkFlow(String id, String name, ArrayList<Node> nodes, ArrayList<Edge> edges, ArrayList<Position> positions) {
        this.id = id;
        this.name = name;
        this.nodes = nodes;
        this.edges = edges;
        this.positions = positions;
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

    public ArrayList<Position> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<Position> positions) {
        this.positions = positions;
    }
    
    public void getGraph(){
        WorkFlow workflow = new WorkFlow(id, name, nodes, edges, positions);
        Draw(workflow);
    }
    
    private static void Draw(WorkFlow workflow){  
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new Panel(workflow));
        f.setSize(xMax(workflow),yMax(workflow));
        f.setLocationByPlatform(true);
        f.setVisible(true);
        f.setTitle(workflow.getName().toUpperCase());
    }
    
    private static int xMax(WorkFlow workflow) {
        ArrayList<Integer> x = new ArrayList<Integer>();
        for (Position p : workflow.getPositions()) {
            x.add(p.getX());
        }
        int max = (int) x.stream().mapToDouble(i -> i).max().getAsDouble();
        max = max + 39;
        return max;
    }
    
    public static int yMax(WorkFlow workflow) {
        ArrayList<Integer> y = new ArrayList<Integer>();
        for (Position p : workflow.getPositions()) {
            y.add(p.getY());
        }
        int max = (int) y.stream().mapToDouble(i -> i).max().getAsDouble();
        max = max + 18;
        return max;
    }
    
}
