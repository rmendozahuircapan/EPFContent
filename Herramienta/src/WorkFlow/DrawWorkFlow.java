
package WorkFlow;

import static Tool.App.WorkFlows;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import static WorkFlow.DrawWorkFlow.*;
  
public class DrawWorkFlow{
    
    
    public static void DrawWorkFlow(String id){
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new ArrowPanel(id));
        // Cambiar valores fijos *********************************
        f.setSize(xMax(id)+18,yMax(id)+39);
        f.setLocationByPlatform(true);
        f.setVisible(true);
        f.setTitle("WorkFlow");
    }
    public static int xMax(String id) {
        WorkFlow wf = null;
        for (WorkFlow w : WorkFlows) {
            if (w.getId().equals(id)) {
                wf = w;
            }
        }
        ArrayList<Integer> x = new ArrayList<Integer>();
        for (Position p : wf.getPositions()) {
            x.add(p.getX());
        }
        int size = (int) x.stream().mapToDouble(i -> i).max().getAsDouble();
        return size;
    }
    
    public static int yMax(String id) {
        WorkFlow wf = null;
        for (WorkFlow w : WorkFlows) {
            if (w.getId().equals(id)) {
                wf = w;
            }
        }
        ArrayList<Integer> y = new ArrayList<Integer>();
        for (Position p : wf.getPositions()) {
            y.add(p.getY());
        }
        int size = (int) y.stream().mapToDouble(i -> i).max().getAsDouble();
        return size;
    }
    
    public static int xMin(String id) {
        WorkFlow wf = null;
        for (WorkFlow w : WorkFlows) {
            if (w.getId().equals(id)) {
                wf = w;
            }
        }
        ArrayList<Integer> x = new ArrayList<Integer>();
        for (Position p : wf.getPositions()) {
            x.add(p.getX());
        }
        int size = (int) x.stream().mapToDouble(i -> i).min().getAsDouble();
        return size;
    }
    
    public static int yMin(String id) {
        WorkFlow wf = null;
        for (WorkFlow w : WorkFlows) {
            if (w.getId().equals(id)) {
                wf = w;
            }
        }
        ArrayList<Integer> y = new ArrayList<Integer>();
        for (Position p : wf.getPositions()) {
            y.add(p.getY());
        }
        int size = (int) y.stream().mapToDouble(i -> i).min().getAsDouble();
        return size;
    }
}

/******************************************************************************************************/  

class ArrowPanel extends JPanel{
    int barb;
    double phi;
    
    WorkFlow wf;
    
    
    public ArrowPanel(String id){
        barb = 20;                   // barb length
        phi = Math.PI/6;             // 30 degrees barb angle
        setBackground(Color.white);
        for (WorkFlow w : WorkFlows) {
            if (w.getId().equals(id)) {
                wf = w;
            }
        }
    }
  
    protected void paintComponent(Graphics g){
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        /*/ datos de worflow
        System.out.println("------------------------------------");
        System.out.println(wf.getName());
        System.out.println(wf.getId());
        System.out.println(wf.getNodes().size()+" nodes");
        System.out.println(wf.getEdges().size()+" edges");
        System.out.println(wf.getPositions().size()+" positions");
        System.out.println("------------------------------------");
        *///////////////////////////////////////////////////////////////////////////////////////////////////////
        super.paintComponent(g);
        Graphics2D graph = (Graphics2D)g;
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        
        Font font = new Font("Arial", Font.PLAIN, 10);
        graph.setFont(font);

        String id = wf.getId();        
        int side = 30;
        int border = 10;
        for (Node node : wf.getNodes()) {
            for (Position position : wf.getPositions()) {
                if (node.getId().equals(position.getIdNode())) {
                    if (node.getType().equals("StructuredActivityNode")) {
                        ImageIcon Img = new ImageIcon(getClass().getResource("/Icons/Activity.gif"));
                        graph.drawImage(Img.getImage(),position.getX() - xMin(id) + side,position.getY() - yMin(id), side, side, null);
                    }
                    else if (node.getType().equals("ActivityParameterNode")) {
                        ImageIcon Img = new ImageIcon(getClass().getResource("/Icons/Task.gif"));
                        graph.drawImage(Img.getImage(),position.getX() - xMin(id) + side,position.getY() - yMin(id), side, side, null);
                    }
                    else{
                        ImageIcon Img = new ImageIcon(getClass().getResource("/Icons/Extra.png"));
                        graph.drawImage(Img.getImage(),position.getX() - xMin(id) + side,position.getY() - yMin(id), side, side, null);
                    }
                    
                    
                    graph.drawString(node.getName(), position.getX() - xMin(id) + side, position.getY() - yMin(id) + side + border);
                }
            }
        }
        
        for (int i = 0; i < xMax(id); i++) {
            graph.drawString(".", i , yMax(id));
        }
        for (int i = 0; i < yMax(id); i++) {
            graph.drawString(".", xMax(id), i);
        }



        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        double theta;
        int x1 = 400, y1 = 100;
        int x2 = 100, y2 = 300;
        int lado = 40; // side
        int margen = 10; // border
        String posicion = new String();
        /*///////////////////////////////////////////////////////////////////////////////////////////////////////
        ImageIcon Img = new ImageIcon(getClass().getResource("/Icons/task.gif"));
        graph.drawImage(Img.getImage(), x1, y1, lado, lado, null);
        graph.drawString("Tarea 1", x1 , y1 + lado + margen);
        graph.drawImage(Img.getImage(), x2, y2, lado, lado, null);
        graph.drawString("Tarea 2", x2 , y2 + lado + margen);
        *////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Posiciones de flechas
        if (x1 == x2) {
            if (y1 < y2) posicion = "abajo";
            else if(y1 < y2) posicion = "arriba";
        }
        else if(x1 < x2){
            if(y1 == y2) posicion = "derecha";
            else if (y1 > y2) posicion = "arriba-derecha";
            else if (y1 < y2) posicion = "abajo-derecha";
        }
        else if(x1 > x2){
            if(y1 == y2) posicion = "izquierda";
            else if (y1 > y2) posicion = "arriba-izquierda";
            else if (y1 < y2) posicion = "abajo-izquierda";
        }
        posicion ="";
        if (posicion.equals("abajo")){
            graph.draw(new Line2D.Double(x1 + lado/2 , y1 + lado + margen*2, x2 + lado/2, y2));
            theta = Math.atan2(y2 - y1 , x2 - x1 );
            drawArrow(graph, theta, x2 + lado/2, y2 );
        }
        
        if (posicion.equals("arriba")) {
            graph.draw(new Line2D.Double( x1 + (lado/2), y1 , x2 + (lado/2), y2 + lado + margen*2));
            theta = Math.atan2(y2 - y1 , x2 - x1 );
            drawArrow(graph, theta, x2 + (lado/2) , y2 + lado + margen*2);
        }
        if (posicion.equals("derecha")) {
            graph.draw(new Line2D.Double( x1 + lado + margen, y1 + (lado/2) , x2 - margen, y2 + (lado/2)));
            theta = Math.atan2(y2 - y1 , x2 - x1 );
            drawArrow(graph, theta, x2 - margen , y2 + (lado/2));
        }
        
        if (posicion.equals("izquierda")) {
            graph.draw(new Line2D.Double( x1  - margen , y1 + (lado/2) , x2 + lado + margen, y2 + (lado/2)));
            theta = Math.atan2(y2 - y1 , x2 - x1 );
            drawArrow(graph, theta, x2 + margen + lado , y2 + (lado/2));
        }
        if (posicion.equals("arriba-derecha")) {
            graph.draw(new Line2D.Double( x1 + lado, y1 , x2 - margen, y2 + lado + margen));
            theta = Math.atan2(y2 - y1 , x2 - x1 - lado );
            drawArrow(graph, theta, x2 - margen, y2 + lado + margen);
        }
        if (posicion.equals("arriba-izquierda")) {
            graph.draw(new Line2D.Double( x1 , y1 , x2 + margen + lado, y2 + lado + margen));
            theta = Math.atan2(y2 - y1 , x2 - x1 );
            drawArrow(graph, theta, x2 + margen + lado, y2 + lado + margen);
        }
        if (posicion.equals("abajo-derecha")) {
            graph.draw(new Line2D.Double(x1 + lado, y1 + lado + margen*2, x2 , y2));
            theta = Math.atan2(y2 - y1 , x2 - x1 );
            drawArrow(graph, theta, x2, y2);
        }
        if (posicion.equals("abajo-izquierda")) {
            graph.draw(new Line2D.Double(x1, y1 + lado + margen*2, x2 + lado , y2));
            theta = Math.atan2(y2 - y1 , x2 - x1 );
            drawArrow(graph, theta, x2 + lado, y2);
        }
    }
  
    private void drawArrow(Graphics2D g, double theta, double x0, double y0){
        double x = x0 - barb * Math.cos(theta + phi);
        double y = y0 - barb * Math.sin(theta + phi);
        g.draw(new Line2D.Double(x0, y0, x, y));
        x = x0 - barb * Math.cos(theta - phi);
        y = y0 - barb * Math.sin(theta - phi);
        g.draw(new Line2D.Double(x0, y0, x, y));
    }
}