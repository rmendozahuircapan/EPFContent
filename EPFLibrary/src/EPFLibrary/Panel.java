
package EPFLibrary;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

class Panel extends JPanel{
    int sideNode;
    int sideStartEnd;
    int sideDecisionMerge;
    int widthForkJoin;
    int highForkJoin;
    int border;
    int barb;
    double phi;
    WorkFlow workflow;
    String path;
    
    
    protected Panel(WorkFlow workflow, String path){
        sideNode = 30;
        sideStartEnd = 20;
        sideDecisionMerge = 55;
        widthForkJoin = 105;
        highForkJoin = 15;
        border = 10;
        barb = 5;
        phi = Math.PI/6;
        this.workflow = workflow;
        this.path = path;
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D graph = (Graphics2D) g;
        Font font = new Font("Arial", Font.PLAIN, 10);
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graph.setFont(font);
        graph.setColor(Color.BLACK);
        ImageIcon Img = new ImageIcon(getClass().getResource("/Icons/Background.jpg"));
        graph.drawImage(Img.getImage(), 0, 0, WorkFlow.widthWorkflow(workflow), WorkFlow.highWorkflow(workflow), null);
        drawNodes(graph, workflow);
        drawEdges(graph, workflow);

        try {
            BufferedImage bi = new BufferedImage(WorkFlow.widthWorkflow(workflow), WorkFlow.highWorkflow(workflow), BufferedImage.TYPE_INT_RGB);
            Graphics2D image = bi.createGraphics();
            image.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            image.setFont(font);
            image.setColor(Color.BLACK);
            image.drawImage(Img.getImage(), 0, 0, WorkFlow.widthWorkflow(workflow), WorkFlow.highWorkflow(workflow), null);
            drawNodes(image, workflow);
            drawEdges(image, workflow);
            ImageIO.write(bi, "JPG", new File(path+"\\"+workflow.getName()+".jpg"));
        } catch (IOException e) {
            System.out.println("ERROR: "+e.getMessage());
        }
    }
    
    private void drawNodes(Graphics2D graph, WorkFlow workflow){
        for (Node node : workflow.getNodes()) {
            for (Position position : workflow.getPositions()) {
                if (node.getId().equals(position.getId())) {
                    
                    int xPosition;
                    int yPosition;
                    int yPositionName;
                    
                    if (node.getType().equals("StructuredActivityNode") || node.getType().equals("ActivityParameterNode")) {
                        
                        xPosition = position.getX() + sideNode ;
                        yPosition = position.getY() ;
                        yPositionName = yPosition + sideNode + border ;
                        ImageIcon Img;
                        if (node.getType().equals("StructuredActivityNode")) {
                            Img = new ImageIcon(getClass().getResource("/Icons/ActivityNode.gif"));
                        }
                        else{
                            Img = new ImageIcon(getClass().getResource("/Icons/TaskNode.gif"));
                        }
                        graph.drawImage(Img.getImage(), xPosition, yPosition, sideNode, sideNode, null);
                        graph.drawString(node.getName(), xPosition, yPositionName);
                    
                    }
                    else if (node.getType().equals("InitialNode") || node.getType().equals("ActivityFinalNode")) {
                        
                        xPosition = position.getX() + sideStartEnd ;
                        yPosition = position.getY() ;
                        ImageIcon Img;
                        if (node.getType().equals("InitialNode")) {
                            Img = new ImageIcon(getClass().getResource("/Icons/StartNode.png"));
                        }
                        else{
                            Img = new ImageIcon(getClass().getResource("/Icons/EndNode.png"));
                        }
                        graph.drawImage(Img.getImage(), xPosition, yPosition, sideStartEnd, sideStartEnd, null);
                    
                    }
                    else if (node.getType().equals("ForkNode") || node.getType().equals("JoinNode")) {
                        
                        xPosition = position.getX() + highForkJoin;
                        yPosition = position.getY() ;
                        
                        ImageIcon Img = new ImageIcon(getClass().getResource("/Icons/ForkJoinNode.png"));
                        graph.drawImage(Img.getImage(), xPosition, yPosition, widthForkJoin, highForkJoin, null);
                        
                    }
                    else if (node.getType().equals("DecisionNode") || node.getType().equals("MergeNode")) {
                        
                        xPosition = position.getX() ;
                        yPosition = position.getY() ;
                        yPositionName = yPosition + sideDecisionMerge/2 ;
                        ImageIcon Img;
                        if (node.getType().equals("DecisionNode")) {
                            Img = new ImageIcon(getClass().getResource("/Icons/DecisionMergeNode.png"));
                            graph.drawImage(Img.getImage(), xPosition, yPosition, sideDecisionMerge, sideDecisionMerge, null);
                            if (!node.getName().equals("DecisionNode")) {
                               graph.drawString(node.getName(), xPosition, yPositionName);
                            }
                        }
                        else{
                            Img = new ImageIcon(getClass().getResource("/Icons/DecisionMergeNode.png"));
                            graph.drawImage(Img.getImage(), xPosition, yPosition, sideDecisionMerge, sideDecisionMerge, null);
                            if (!node.getName().equals("MergeNode")) {
                                graph.drawString(node.getName(), xPosition, yPositionName);
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void drawEdges(Graphics2D graph, WorkFlow workflow){
        for (Edge edge : workflow.getEdges()) {
            int x1 = -1;
            int y1 = -1; 
            int x2 = -1;
            int y2 = -1;
            String typeSource = new String();
            String typeTarget = new String();
            for (Position position : workflow.getPositions()) {
                if (edge.getSource().getId().equals(position.getId())) {
                    Node node = edge.getSource();
                    typeSource = node.getType();
                    if (node.getType().equals("StructuredActivityNode") || node.getType().equals("ActivityParameterNode")) {
                        x1 = position.getX() + sideNode + sideNode/2;
                        y1 = position.getY() + sideNode/2;
                    }
                    else if (node.getType().equals("InitialNode") || node.getType().equals("ActivityFinalNode")) {
                        x1 = position.getX() + sideStartEnd + sideStartEnd/2;
                        y1 = position.getY() + sideStartEnd/2;
                        
                    }
                    else if (node.getType().equals("ForkNode") || node.getType().equals("JoinNode")) {
                        x1 = position.getX() + highForkJoin + widthForkJoin/2;
                        y1 = position.getY() + highForkJoin/2;
                    }
                    else if (node.getType().equals("DecisionNode") || node.getType().equals("MergeNode")) {
                        x1 = position.getX() + sideDecisionMerge/2;
                        y1 = position.getY() + sideDecisionMerge/2;
                    }
                }
                else if (edge.getTarget().getId().equals(position.getId())) {
                    Node node = edge.getTarget();
                    typeTarget = node.getType();
                    if (node.getType().equals("StructuredActivityNode") || node.getType().equals("ActivityParameterNode")) {
                        x2 = position.getX() + sideNode + sideNode/2;
                        y2 = position.getY() + sideNode/2;
                    }
                    else if (node.getType().equals("InitialNode") || node.getType().equals("ActivityFinalNode")) {
                        x2 = position.getX() + sideStartEnd + sideStartEnd/2;
                        y2 = position.getY() + sideStartEnd/2;
                    }
                    else if (node.getType().equals("ForkNode") || node.getType().equals("JoinNode")) {
                        x2 = position.getX() + highForkJoin + widthForkJoin/2;
                        y2 = position.getY() + highForkJoin/2;
                    }
                    else if (node.getType().equals("DecisionNode") || node.getType().equals("MergeNode")) {
                        x2 = position.getX() + sideDecisionMerge/2;
                        y2 = position.getY() + sideDecisionMerge/2;
                    }
                }
                
            }
            if ( (x1 != -1) && (y1 != -1) && (x2 != -1) && (y2 != -1)) {
                drawArrow(graph, x1, y1, x2, y2, typeSource, typeTarget);
                drawName(graph, edge.getName(), x1, y1, x2, y2);
            }
        }
    }
    
    private void drawArrow(Graphics2D graph, int x1, int y1, int x2, int y2, String typeSource, String typeTarget){

        double theta = Math.atan2(y2 - y1 , x2 - x1 );
        int a1 = -1;
        int b1 = -1;
        int a2 = -1;
        int b2 = -1;
        int xDelta1 = 0;
        int yDelta1 = 0;
        int xDelta2 = 0;
        int yDelta2 = 0;
        
        graph.setColor(Color.BLACK);
        
        if (x1 == x2) {
            if (y1 < y2){
//-------------------------------- DOWN ---------------------------
                // ----- SOURCE -----
                if (typeSource.equals("StructuredActivityNode") || typeSource.equals("ActivityParameterNode")) {
                    yDelta1 = sideNode/2 + border;
                }
                else if (typeSource.equals("InitialNode") || typeSource.equals("ActivityFinalNode")) {
                    yDelta1 = sideStartEnd/2; 
                }
                else if (typeSource.equals("ForkNode") || typeSource.equals("JoinNode")) {
                    yDelta1 = highForkJoin/2;
                }
                else if (typeSource.equals("DecisionNode") || typeSource.equals("MergeNode")) {
                    yDelta1 = sideDecisionMerge/2;
                }
                // ----- TARGET -----
                if (typeTarget.equals("StructuredActivityNode") || typeTarget.equals("ActivityParameterNode")) {
                    yDelta2 = sideNode/2;
                }
                else if (typeTarget.equals("InitialNode") || typeTarget.equals("ActivityFinalNode")) {
                    yDelta2 = sideStartEnd/2;
                }
                else if (typeTarget.equals("ForkNode") || typeTarget.equals("JoinNode")) {
                    yDelta2 = highForkJoin/2;
                }
                else if (typeTarget.equals("DecisionNode") || typeTarget.equals("MergeNode")) {
                    yDelta2 = sideDecisionMerge/2;
                }
                b1 = y1 + yDelta1;
                b2 = y2 - yDelta2;
                a1 = reverseFunction(b1, x1, y1, x2, y2);
                a2 = reverseFunction(b2, x1, y1, x2, y2);
                
                // -----------------
                if ( (a1 != -1) && (b1 != -1) && (a2 != -1) && (b2 != -1)) {
                    graph.drawLine( a1, b1, a2, b2);
                    drawArrowHead(graph, theta, a2, b2);
                }
// -----------------------------------------------------------------                
            }
            else if(y1 > y2){
//-------------------------------- UP --------------------------
                // ----- SOURCE -----
                if (typeSource.equals("StructuredActivityNode") || typeSource.equals("ActivityParameterNode")) {
                    yDelta1 = sideNode/2;
                }
                else if (typeSource.equals("InitialNode") || typeSource.equals("ActivityFinalNode")) {
                    yDelta1 = sideStartEnd/2;
                }
                else if (typeSource.equals("ForkNode") || typeSource.equals("JoinNode")) {
                    yDelta1 = highForkJoin/2;
                }
                else if (typeSource.equals("DecisionNode") || typeSource.equals("MergeNode")) {
                    yDelta1 = sideDecisionMerge/2;
                }
                // ----- TARGET -----
                if (typeTarget.equals("StructuredActivityNode") || typeTarget.equals("ActivityParameterNode")) {
                    yDelta2 = sideNode/2 + border;
                }
                else if (typeTarget.equals("InitialNode") || typeTarget.equals("ActivityFinalNode")) {
                    yDelta2 = sideStartEnd/2;
                }
                else if (typeTarget.equals("ForkNode") || typeTarget.equals("JoinNode")) {
                    yDelta2 = highForkJoin/2;
                }
                else if (typeTarget.equals("DecisionNode") || typeTarget.equals("MergeNode")) {
                    yDelta2 = sideDecisionMerge/2;
                }
                // -----------------
                
                b1 = y1 - yDelta1;
                b2 = y2 + yDelta2;
                a1 = reverseFunction(b1, x1, y1, x2, y2);
                a2 = reverseFunction(b2, x1, y1, x2, y2);
                
                if ( (a1 != -1) && (b1 != -1) && (a2 != -1) && (b2 != -1)) {
                    graph.drawLine( a1, b1, a2, b2);
                    drawArrowHead(graph, theta, a2, b2);
                }
// -----------------------------------------------------------------
            }
        }else if(x1 < x2){
            if(y1 == y2){
// ------------------------------ RIGHT --------------------------
                // ----- SOURCE -----
                if (typeSource.equals("StructuredActivityNode") || typeSource.equals("ActivityParameterNode")) {
                    xDelta1 = sideNode/2;
                }
                else if (typeSource.equals("InitialNode") || typeSource.equals("ActivityFinalNode")) {
                    xDelta1 = sideStartEnd/2;
                }
                else if (typeSource.equals("ForkNode") || typeSource.equals("JoinNode")) {
                    xDelta1 = widthForkJoin/2;
                }
                else if (typeSource.equals("DecisionNode") || typeSource.equals("MergeNode")) {
                    xDelta1 = sideDecisionMerge/2;
                }
                // ----- TARGET -----
                if (typeTarget.equals("StructuredActivityNode") || typeTarget.equals("ActivityParameterNode")) {
                    xDelta2 = sideNode/2;
                }
                else if (typeTarget.equals("InitialNode") || typeTarget.equals("ActivityFinalNode")) {
                    xDelta2 = sideStartEnd/2;
                }
                else if (typeTarget.equals("ForkNode") || typeTarget.equals("JoinNode")) {
                    xDelta2 = widthForkJoin/2;
                }
                else if (typeTarget.equals("DecisionNode") || typeTarget.equals("MergeNode")) {
                    xDelta2 = sideDecisionMerge/2;
                }
                // -----------------
                
                a1 = x1 + xDelta1;
                a2 = x2 - xDelta2;
                b1 = function(a1, x1, y1, x2, y2);
                b2 = function(a2, x1, y1, x2, y2);
                
                if ( (a1 != -1) && (b1 != -1) && (a2 != -1) && (b2 != -1)) {
                    graph.drawLine( a1, b1, a2, b2);
                    drawArrowHead(graph, theta, a2, b2);
                }
// -----------------------------------------------------------------
            }
            else if (y1 > y2){
// ----------------------- UP RIGHT --------------------------
                // ----- SOURCE -----
                if (typeSource.equals("StructuredActivityNode") || typeSource.equals("ActivityParameterNode")) {
                    xDelta1 = sideNode/2;
                    yDelta1 = sideNode/2;
                }
                else if (typeSource.equals("InitialNode") || typeSource.equals("ActivityFinalNode")) {
                    xDelta1 = sideStartEnd/2;
                    yDelta1 = sideStartEnd/2;
                }
                else if (typeSource.equals("ForkNode") || typeSource.equals("JoinNode")) {
                    xDelta1 = highForkJoin/2;
                    yDelta1 = highForkJoin/2;
                }
                else if (typeSource.equals("DecisionNode") || typeSource.equals("MergeNode")) {
                    xDelta1 = sideDecisionMerge/2;
                    yDelta1 = sideDecisionMerge/2;
                }
                // ----- TARGET -----
                if (typeTarget.equals("StructuredActivityNode") || typeTarget.equals("ActivityParameterNode")) {
                    xDelta2 = sideNode/2;
                    yDelta2 = sideNode/2;
                }
                else if (typeTarget.equals("InitialNode") || typeTarget.equals("ActivityFinalNode")) {
                    xDelta2 = sideStartEnd/2;
                    yDelta2 = sideStartEnd/2;
                }
                else if (typeTarget.equals("ForkNode") || typeTarget.equals("JoinNode")) {
                    xDelta2 = highForkJoin/2;
                    yDelta2 = highForkJoin/2;
                }
                else if (typeTarget.equals("DecisionNode") || typeTarget.equals("MergeNode")) {
                    xDelta2 = sideDecisionMerge/2;
                    yDelta2 = sideDecisionMerge/2;
                }
                // -----------------
                
                a1 = x1 + xDelta1;
                a2 = x2 - xDelta2;
                b1 = function(a1, x1, y1, x2, y2);
                b2 = function(a2, x1, y1, x2, y2);
                if ( Math.abs(b1 - y1) > yDelta1) {
                    b1 = y1 - yDelta1;
                    a1 = reverseFunction(b1, x1, y1, x2, y2);
                }
                if ( Math.abs(b2 - y2) > yDelta2) {
                    b2 = y2 + yDelta2;
                    a2 = reverseFunction(b2, x1, y1, x2, y2);
                }
                
                if ( (a1 != -1) && (b1 != -1) && (a2 != -1) && (b2 != -1)) {
                    graph.drawLine( a1, b1, a2, b2);
                    drawArrowHead(graph, theta, a2, b2);
                }
// -----------------------------------------------------------------
            }
            else if (y1 < y2){
// ------------------------ DOWN RIGHT -------------------------- 
                // ----- SOURCE -----
                if (typeSource.equals("StructuredActivityNode") || typeSource.equals("ActivityParameterNode")) {
                    xDelta1 = sideNode/2;
                    yDelta1 = sideNode/2;
                }
                else if (typeSource.equals("InitialNode") || typeSource.equals("ActivityFinalNode")) {
                    xDelta1 = sideStartEnd/2;
                    yDelta1 = sideStartEnd/2;
                }
                else if (typeSource.equals("ForkNode") || typeSource.equals("JoinNode")) {
                    xDelta1 = highForkJoin/2;
                    yDelta1 = highForkJoin/2;
                }
                else if (typeSource.equals("DecisionNode") || typeSource.equals("MergeNode")) {
                    xDelta1 = sideDecisionMerge/2;
                    yDelta1 = sideDecisionMerge/2;
                }
                // ----- TARGET -----
                if (typeTarget.equals("StructuredActivityNode") || typeTarget.equals("ActivityParameterNode")) {
                    xDelta2 = sideNode/2;
                    yDelta2 = sideNode/2;
                }
                else if (typeTarget.equals("InitialNode") || typeTarget.equals("ActivityFinalNode")) {
                    xDelta2 = sideStartEnd/2;
                    yDelta2 = sideStartEnd/2;
                }
                else if (typeTarget.equals("ForkNode") || typeTarget.equals("JoinNode")) {
                    xDelta2 = highForkJoin/2;
                    yDelta2 = highForkJoin/2;
                }
                else if (typeTarget.equals("DecisionNode") || typeTarget.equals("MergeNode")) {
                    xDelta2 = sideDecisionMerge/2;
                    yDelta2 = sideDecisionMerge/2;
                }
                // -----------------
                
                a1 = x1 + xDelta1;
                a2 = x2 - xDelta2;
                b1 = function(a1, x1, y1, x2, y2);
                b2 = function(a2, x1, y1, x2, y2);
                
                if ( Math.abs(b1 - y1) > yDelta1) {
                    b1 = y1 + yDelta1;
                    a1 = reverseFunction(b1, x1, y1, x2, y2);
                }
                if ( Math.abs(b2 - y2) > yDelta2) {
                    b2 = y2 - yDelta2;
                    a2 = reverseFunction(b2, x1, y1, x2, y2);
                }
                
                if ( (a1 != -1) && (b1 != -1) && (a2 != -1) && (b2 != -1)) {
                    graph.drawLine( a1, b1, a2, b2);
                    drawArrowHead(graph, theta, a2, b2);
                }
// -----------------------------------------------------------------
            }
        }else if(x1 > x2){
            if(y1 == y2){
// ---------------------------- LEFT -------------------------- 
                // ----- SOURCE -----
                if (typeSource.equals("StructuredActivityNode") || typeSource.equals("ActivityParameterNode")) {
                    xDelta1 = sideNode/2;
                }
                else if (typeSource.equals("InitialNode") || typeSource.equals("ActivityFinalNode")) {
                    xDelta1 = sideStartEnd/2;
                }
                else if (typeSource.equals("ForkNode") || typeSource.equals("JoinNode")) {
                    xDelta1 = widthForkJoin/2;
                }
                else if (typeSource.equals("DecisionNode") || typeSource.equals("MergeNode")) {
                    xDelta1 = sideDecisionMerge/2;
                }
                // ----- TARGET -----
                if (typeTarget.equals("StructuredActivityNode") || typeTarget.equals("ActivityParameterNode")) {
                    xDelta2 = sideNode/2;
                }
                else if (typeTarget.equals("InitialNode") || typeTarget.equals("ActivityFinalNode")) {
                    xDelta2 = sideStartEnd/2;
                }
                else if (typeTarget.equals("ForkNode") || typeTarget.equals("JoinNode")) {
                    xDelta2 = widthForkJoin/2;
                }
                else if (typeTarget.equals("DecisionNode") || typeTarget.equals("MergeNode")) {
                    xDelta2 = sideDecisionMerge/2;
                }
                // -----------------
                
                a1 = x1 - xDelta1;
                a2 = x2 + xDelta2;
                b1 = function(a1, x1, y1, x2, y2);
                b2 = function(a2, x1, y1, x2, y2);
                
                if ( (a1 != -1) && (b1 != -1) && (a2 != -1) && (b2 != -1)) {
                    graph.drawLine( a1, b1, a2, b2);
                    drawArrowHead(graph, theta, a2, b2);
                }
// -----------------------------------------------------------------
            }
            else if (y1 > y2){
// --------------------- UP LEFT -------------------------- 
                // ----- SOURCE -----
                if (typeSource.equals("StructuredActivityNode") || typeSource.equals("ActivityParameterNode")) {
                    xDelta1 = sideNode/2;
                    yDelta1 = sideNode/2;
                }
                else if (typeSource.equals("InitialNode") || typeSource.equals("ActivityFinalNode")) {
                    xDelta1 = sideStartEnd/2;
                    yDelta1 = sideStartEnd/2;
                }
                else if (typeSource.equals("ForkNode") || typeSource.equals("JoinNode")) {
                    xDelta1 = highForkJoin/2;
                    yDelta1 = highForkJoin/2;
                }
                else if (typeSource.equals("DecisionNode") || typeSource.equals("MergeNode")) {
                    xDelta1 = sideDecisionMerge/2;
                    yDelta1 = sideDecisionMerge/2;
                }
                // ----- TARGET -----
                if (typeTarget.equals("StructuredActivityNode") || typeTarget.equals("ActivityParameterNode")) {
                    xDelta2 = sideNode/2;
                    yDelta2 = sideNode/2;
                }
                else if (typeTarget.equals("InitialNode") || typeTarget.equals("ActivityFinalNode")) {
                    xDelta2 = sideStartEnd/2;
                    yDelta2 = sideStartEnd/2;
                }
                else if (typeTarget.equals("ForkNode") || typeTarget.equals("JoinNode")) {
                    xDelta2 = highForkJoin/2;
                    yDelta2 = highForkJoin/2;
                }
                else if (typeTarget.equals("DecisionNode") || typeTarget.equals("MergeNode")) {
                    xDelta2 = sideDecisionMerge/2;
                    yDelta2 = sideDecisionMerge/2;
                }
                // -----------------
                a1 = x1 - xDelta1;
                a2 = x2 + xDelta2;
                b1 = function(a1, x1, y1, x2, y2);
                b2 = function(a2, x1, y1, x2, y2);
                
                if ( Math.abs(b1 - y1) > yDelta1) {
                    b1 = y1 - yDelta1;
                    a1 = reverseFunction(b1, x1, y1, x2, y2);
                }
                if ( Math.abs(b2 - y2) > yDelta2) {
                    b2 = y2 + yDelta2;
                    a2 = reverseFunction(b2, x1, y1, x2, y2);
                }
                
                if ( (a1 != -1) && (b1 != -1) && (a2 != -1) && (b2 != -1)) {
                    graph.drawLine( a1, b1, a2, b2);
                    drawArrowHead(graph, theta, a2, b2);
                }
// -----------------------------------------------------------------
            }
            else if (y1 < y2){
// ---------------------- DOWN LEFT --------------------------
                // ----- SOURCE -----
                if (typeSource.equals("StructuredActivityNode") || typeSource.equals("ActivityParameterNode")) {
                    xDelta1 = sideNode/2;
                    yDelta1 = sideNode/2;
                }
                else if (typeSource.equals("InitialNode") || typeSource.equals("ActivityFinalNode")) {
                    xDelta1 = sideStartEnd/2;
                    yDelta1 = sideStartEnd/2;
                }
                else if (typeSource.equals("ForkNode") || typeSource.equals("JoinNode")) {
                    xDelta1 = highForkJoin/2;
                    yDelta1 = highForkJoin/2;
                }
                else if (typeSource.equals("DecisionNode") || typeSource.equals("MergeNode")) {
                    xDelta1 = sideDecisionMerge/2;
                    yDelta1 = sideDecisionMerge/2;
                }
                // ----- TARGET -----
                if (typeTarget.equals("StructuredActivityNode") || typeTarget.equals("ActivityParameterNode")) {
                    xDelta2 = sideNode/2;
                    yDelta2 = sideNode/2;
                }
                else if (typeTarget.equals("InitialNode") || typeTarget.equals("ActivityFinalNode")) {
                    xDelta2 = sideStartEnd/2;
                    yDelta2 = sideStartEnd/2;
                }
                else if (typeTarget.equals("ForkNode") || typeTarget.equals("JoinNode")) {
                    xDelta2 = highForkJoin/2;
                    yDelta2 = highForkJoin/2;
                }
                else if (typeTarget.equals("DecisionNode") || typeTarget.equals("MergeNode")) {
                    xDelta2 = sideDecisionMerge/2;
                    yDelta2 = sideDecisionMerge/2;
                }
                // -----------------
                a1 = x1 - xDelta1;
                a2 = x2 + xDelta2;
                b1 = function(a1, x1, y1, x2, y2);
                b2 = function(a2, x1, y1, x2, y2);
                
                if ( Math.abs(b1 - y1) > yDelta1) {
                    b1 = y1 + yDelta1;
                    a1 = reverseFunction(b1, x1, y1, x2, y2);
                }
                if ( Math.abs(b2 - y2) > yDelta2) {
                    b2 = y2 - yDelta2;
                    a2 = reverseFunction(b2, x1, y1, x2, y2);
                }
                if ( (a1 != -1) && (b1 != -1) && (a2 != -1) && (b2 != -1)) {
                    graph.drawLine( a1, b1, a2, b2);
                    drawArrowHead(graph, theta, a2, b2);
                }
            }
// -----------------------------------------------------------------
        }
    }
    
    private void drawName(Graphics2D graph, String name, int x1, int y1, int x2, int y2) {
        int x0 = (int) (x1 + x2)/2;
        int y0 = (int) (y1 + y2)/2;
        graph.setColor(Color.GRAY);
        graph.drawString(name, x0, y0);
    }
    
    private void drawArrowHead(Graphics2D g, double theta, double x0, double y0){
        double x = x0 - barb * Math.cos(theta + phi);
        double y = y0 - barb * Math.sin(theta + phi);
        g.draw(new Line2D.Double(x0, y0, x, y));
        x = x0 - barb * Math.cos(theta - phi);
        y = y0 - barb * Math.sin(theta - phi);
        g.draw(new Line2D.Double(x0, y0, x, y));
    }   
    
    private int function(double x, double x1, double y1, double x2, double y2){
        double m = (y2 - y1)/(x2 - x1);
        double y = m*x - m*x1 + y1;
        return (int)  y;
    }
    
    private int reverseFunction(double y, double x1, double y1, double x2, double y2){
        double m = (x2 - x1)/(y2 - y1);
        double x = m*y - m*y1 + x1;
        return (int) x;
    }
    
}