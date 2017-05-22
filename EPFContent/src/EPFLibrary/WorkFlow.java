
package EPFLibrary;

import java.util.*;
import javax.swing.*;

public class WorkFlow {
    private String id;
    private String name;
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    private ArrayList<Position> positions;

    protected WorkFlow(String id, String name, ArrayList<Node> nodes, ArrayList<Edge> edges, ArrayList<Position> positions) {
        this.id = id;
        this.name = name;
        this.nodes = nodes;
        this.edges = edges;
        this.positions = positions;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    protected ArrayList<Node> getNodes() {
        return nodes;
    }

    protected ArrayList<Edge> getEdges() {
        return edges;
    }

    protected ArrayList<Position> getPositions() {
        return positions;
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
        f.setTitle("WorkFlow of \"" + workflow.getName() + "\"");
    }
    
    private static int xMax(WorkFlow workflow) {
        ArrayList<Integer> x = new ArrayList<>();
        for (Position p : workflow.getPositions()) {
            x.add(p.getX());
        }
        int max = (int) x.stream().mapToDouble(i -> i).max().getAsDouble();
        max = max + 39;
        return max;
    }
    
    private static int yMax(WorkFlow workflow) {
        ArrayList<Integer> y = new ArrayList<>();
        for (Position p : workflow.getPositions()) {
            y.add(p.getY());
        }
        int max = (int) y.stream().mapToDouble(i -> i).max().getAsDouble();
        max = max + 18;
        return max;
    }
    
}
