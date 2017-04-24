
package WorkFlow;

import static Tool.App.WorkFlows;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
  
public class DrawWorkFlow{
    
    public static void DrawWorkFlow(String id){
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new ArrowPanel(id));
        f.setSize(600,600);
        f.setLocation(200,200);
        f.setVisible(true);
    }
}
  
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
        super.paintComponent(g);
        Graphics2D graph = (Graphics2D)g;
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        // datos de worflow
        System.out.println("------------------------------------");
        System.out.println(wf.getName());
        System.out.println(wf.getId());
        System.out.println(wf.getNodes().size()+" nodes");
        System.out.println(wf.getEdges().size()+" edges");
        System.out.println("------------------------------------");
        ///////////////////////////////////////////////////////////////////////////////////////////////////////        
        int w = getWidth();
        int h = getHeight();
        double theta, x, y;
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        int x1 = 400, y1 = 100;
        int x2 = 100, y2 = 300;
        int lado = 40;
        int margen = 10;
        String posicion = new String();
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        ImageIcon Img = new ImageIcon(getClass().getResource("/Icons/task.gif"));
        graph.drawImage(Img.getImage(), x1, y1, lado, lado, null);
        graph.drawString("Tarea 1", x1 , y1 + lado + margen);
        graph.drawImage(Img.getImage(), x2, y2, lado, lado, null);
        graph.drawString("Tarea 2", x2 , y2 + lado + margen);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
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
  
    private void drawArrow(Graphics2D g2, double theta, double x0, double y0){
        double x = x0 - barb * Math.cos(theta + phi);
        double y = y0 - barb * Math.sin(theta + phi);
        g2.draw(new Line2D.Double(x0, y0, x, y));
        x = x0 - barb * Math.cos(theta - phi);
        y = y0 - barb * Math.sin(theta - phi);
        g2.draw(new Line2D.Double(x0, y0, x, y));
    }
}