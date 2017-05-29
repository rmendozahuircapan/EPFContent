
package EPFLibrary;

import java.util.*;
import javax.swing.*;

/**
 * It corresponds a workflow or activity diagram, framed in the elements of a software process.
 * @author Rodrigo
 */
public class WorkFlow {
    private String id;
    private String name;
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    private ArrayList<Position> positions;

    /**
     *
     * @param id workflow identifier
     * @param name name of workflow
     * @param nodes set of nodes of workflow
     * @param edges set of edges of workflow
     * @param positions set of positions of workflow
     */
    protected WorkFlow(String id, String name, ArrayList<Node> nodes, ArrayList<Edge> edges, ArrayList<Position> positions) {
        this.id = id;
        this.name = name;
        this.nodes = nodes;
        this.edges = edges;
        this.positions = positions;
    }

    /**
     *
     * @return returns identifier of workflow
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return returns name of workflow
     */
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

    /**
     *
     * @param path it is the path for save the image of workflow in format jpg
     */
    public void getGraph(String path){
        WorkFlow workflow = new WorkFlow(id, name, nodes, edges, positions);
        Draw(workflow, path);
    }
    
    private static void Draw(WorkFlow workflow, String path){  
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new Panel(workflow, path));
        f.setSize(widthWorkflow(workflow) + 18,highWorkflow(workflow) + 39);
        f.setLocationByPlatform(true);
        f.setVisible(true);
        f.setTitle("WorkFlow of \"" + workflow.getName() + "\"");
    }
    
    protected static int widthWorkflow(WorkFlow workflow) {
        ArrayList<Integer> x = new ArrayList<>();
        for (Position p : workflow.getPositions()) {
            x.add(p.getX());
        }
        int max = (int) x.stream().mapToDouble(i -> i).max().getAsDouble();
        max = max + 120;
        return max;
    }
    
    protected static int highWorkflow(WorkFlow workflow) {
        ArrayList<Integer> y = new ArrayList<>();
        for (Position p : workflow.getPositions()) {
            y.add(p.getY());
        }
        int max = (int) y.stream().mapToDouble(i -> i).max().getAsDouble();
        max = max + 60;
        return max;
    }
    
}
