
package WorkFlow;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import static WorkFlow.DrawWorkFlow.*;
  
public class DrawWorkFlow{
    
    public static void Draw(WorkFlow workflow){  
        int xSize = xMax(workflow) + 18 ;
        int ySize = yMax(workflow) + 39 ;
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new Panel(workflow));
        f.setSize(xSize,ySize);
        f.setLocationByPlatform(true);
        f.setVisible(true);
        f.setTitle(workflow.getName().toUpperCase());
    }
    
    public static int xMax(WorkFlow workflow) {
        ArrayList<Integer> x = new ArrayList<Integer>();
        for (Position p : workflow.getPositions()) {
            x.add(p.getX());
        }
        int max = (int) x.stream().mapToDouble(i -> i).max().getAsDouble();
        return max;
    }
    
    public static int yMax(WorkFlow workflow) {
        ArrayList<Integer> y = new ArrayList<Integer>();
        for (Position p : workflow.getPositions()) {
            y.add(p.getY());
        }
        int max = (int) y.stream().mapToDouble(i -> i).max().getAsDouble();
        return max;
    }
    
    public static int xMin(WorkFlow workflow) {
        ArrayList<Integer> x = new ArrayList<Integer>();
        for (Position p : workflow.getPositions()) {
            x.add(p.getX());
        }
        int min = (int) x.stream().mapToDouble(i -> i).min().getAsDouble();
        return min;
    }
    
    public static int yMin(WorkFlow workflow) {
        ArrayList<Integer> y = new ArrayList<Integer>();
        for (Position p : workflow.getPositions()) {
            y.add(p.getY());
        }
        int min = (int) y.stream().mapToDouble(i -> i).min().getAsDouble();
        return min;
    }
}

/******************************************************************************************************/  

class Panel extends JPanel{
    int side;
    int border;
    int barb;
    double phi;
    WorkFlow workflow;
    
    
    public Panel(WorkFlow workflow){
        side = 30;
        border = 10;
        barb = 10;
        phi = Math.PI/6;
        this.workflow = workflow;
        setBackground(Color.white);
    }
  
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D graph = (Graphics2D) g;
        Font font = new Font("Arial", Font.PLAIN, 10);
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graph.setFont(font);
        
        drawNodes(graph, workflow);
        drawArrow(graph, workflow);
        
        // BORRAR /////////////////////////////////////
        for (int i = 0; i < xMax(workflow); i++) {
            graph.drawString(".", i , yMax(workflow));
        }
        for (int i = 0; i < yMax(workflow); i++) {
            graph.drawString(".", xMax(workflow), i);
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        
    }
    
    private void drawNodes(Graphics2D graph, WorkFlow workflow){
        for (Node node : workflow.getNodes()) {
            for (Position position : workflow.getPositions()) {
                if (node.getId().equals(position.getId())) {
                    
                    int xPosition = position.getX() - xMin(workflow) + side ;
                    int yPosition = position.getY() - yMin(workflow) ;
                    int yPositionName = position.getY() - yMin(workflow) + side + border ;
                    
                    if (node.getType().equals("StructuredActivityNode")) {
                        ImageIcon Img = new ImageIcon(getClass().getResource("/Icons/activity.gif"));
                        graph.drawImage(Img.getImage(), xPosition, yPosition, side, side, null);
                    }
                    else if (node.getType().equals("ActivityParameterNode")) {
                        ImageIcon Img = new ImageIcon(getClass().getResource("/Icons/task.gif"));
                        graph.drawImage(Img.getImage(), xPosition, yPosition, side, side, null);
                    }
                    else{
                        ImageIcon Img = new ImageIcon(getClass().getResource("/Icons/Extra.png"));
                        graph.drawImage(Img.getImage(), xPosition, yPosition, side, side, null);
                    }
                    graph.drawString(node.getName(), xPosition, yPositionName); // Cambiar, según tipo
                }
            }
        }
    }
    
    private void drawArrow(Graphics2D graph, WorkFlow workflow){
        System.out.println("Workflow: "+workflow.getName().toUpperCase());
        for (Edge edge : workflow.getEdges()) {
            int x1 = -1;
            int y1 = -1; 
            int x2 = -1;
            int y2 = -1;
            for (Position position : workflow.getPositions()) {
                if (edge.getSource().getId().equals(position.getId())) {
                    x1 = position.getX() - xMin(workflow) + side + side/2;
                    y1 = position.getY() - yMin(workflow) + side/2;
                }
                else if (edge.getTarget().getId().equals(position.getId())) {
                    x2 = position.getX() - xMin(workflow) + side + side/2;
                    y2 = position.getY() - yMin(workflow) + side/2;
                }
            }
            
            
            //System.out.println(edge.getSource().getName() + " " + x1 + " " + y1);
            //System.out.println(edge.getTarget().getName() + " " + x2 + " " + y2);
            graph.setColor(Color.black);
            graph.draw(new Line2D.Double( x1, y1 , x2, y2));
            graph.setColor(Color.yellow);
            System.out.println(edge.getSource().getName());
            arrowDirection(graph, x1, y1, x2, y2);
            //System.out.println("---------");
        }
        System.out.println("**********************************");
    }
    
    private void arrowDirection(Graphics2D graph, int x1, int y1, int x2, int y2){

        double theta;
        String direction = new String();        
        System.out.println("( " + x1 + " , " + y1 + " )");
        System.out.println("( " + x2 + " , " + y2 + " )");
        System.out.println("---------");
        
    }
  
    private void arrowHead(Graphics2D g, double theta, double x0, double y0){
        double x = x0 - barb * Math.cos(theta + phi);
        double y = y0 - barb * Math.sin(theta + phi);
        g.draw(new Line2D.Double(x0, y0, x, y));
        x = x0 - barb * Math.cos(theta - phi);
        y = y0 - barb * Math.sin(theta - phi);
        g.draw(new Line2D.Double(x0, y0, x, y));
    }
    
}

/*      if (x1 == x2) {
            if (y1 < y2) direction = "abajo";
            else if(y1 < y2) direction = "arriba";
        }else if(x1 < x2){
            if(y1 == y2) direction = "derecha";
            else if (y1 > y2) direction = "arriba-derecha";
            else if (y1 < y2) direction = "abajo-derecha";
        }else if(x1 > x2){
            if(y1 == y2) direction = "izquierda";
            else if (y1 > y2) direction = "arriba-izquierda";
            else if (y1 < y2) direction = "abajo-izquierda";
        }
        if (direction.equals("abajo")){
            graph.drawLine( x1, y1, x2, y2);
            theta = Math.atan2(y2 - y1 , x2 - x1 );
            arrowHead(graph, theta, x2, y2);
        }
        
        if (direction.equals("arriba")) {
            graph.drawLine( x1, y1, x2, y2);
            theta = Math.atan2(y2 - y1 , x2 - x1 );
            arrowHead(graph, theta, x2, y2);
        }
        if (direction.equals("derecha")) { //lista
            
            x1 = x1 + side/2 + border;
            x2 = x2 - side/2 - border;
            
            graph.drawLine( x1, y1, x2, y2);
            theta = Math.atan2(y2 - y1 , x2 - x1);
            arrowHead(graph, theta, x2, y2);
        }
        if (direction.equals("izquierda")) { //lita
            x1 = x1 - side/2 - border;
            x2 = x2 + side/2 + border;
            
            graph.drawLine( x1, y1, x2, y2);
            theta = Math.atan2(y2 - y1 , x2 - x1 );
            arrowHead(graph, theta, x2 , y2);
        }
        /*if (direction.equals("arriba-derecha")) {
            x1 = x1 + side/2;
            y1 = y1 - side/2;
            
            graph.drawLine( x1, y1, x2, y2);
            theta = Math.atan2(y2 - y1 , x2 - x1);
            arrowHead(graph, theta, x2, y2);
        }
        if (direction.equals("arriba-izquierda")) {
            graph.draw(new Line2D.Double( x1 , y1 , x2 + border + side, y2 + side + border));
            theta = Math.atan2(y2 - y1 , x2 - x1 );
            arrowHead(graph, theta, x2 + border + side, y2 + side + border);
        }else if (direction.equals("abajo-derecha")) {
            graph.draw(new Line2D.Double(x1 + side, y1 + side + border*2, x2 , y2));
            theta = Math.atan2(y2 - y1 , x2 - x1 );
            arrowHead(graph, theta, x2, y2);
        }else if (direction.equals("abajo-izquierda")) {
            graph.draw(new Line2D.Double(x1, y1 + side + border*2, x2 + side , y2));
            theta = Math.atan2(y2 - y1 , x2 - x1 );
            arrowHead(graph, theta, x2 + side, y2);
        }*/