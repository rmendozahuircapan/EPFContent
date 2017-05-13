
package Tool;

import DeliveryProcesses.Position;
import DeliveryProcesses.WorkFlow;
import DeliveryProcesses.Node;
import DeliveryProcesses.Edge;
import static Tool.App.*;
import static Tool.XMIReadFile.*;
import java.io.*;
import java.util.*;

public class SearchWorkFlow {
    
    static String typePathDiagram = "Diagram";
    
    public static void searchElementsWorkFlow() throws IOException{
        searchNodes();
        searchEdges();
        searchPositions();
        searchWorkFlows();
    }
    
    public static void searchNodes() throws IOException{
        boolean flag;
        for (int i = 0; i < pathDiagrams.size(); i++) {
            DiagramFile = new ArrayList<String>();
            XMI(pathDiagrams.get(i),typePathDiagram);
            for (int j = 0; j < DiagramFile.size(); j++) {
                String[] separated = DiagramFile.get(j).split(" ");
                if (separated[0].equals("<node")) {
                    String id = new String();
                    String name = new String();
                    String type = new String();
                    for (int k = 0; k < separated.length; k++) {
                        if (separated[k].contains("xmi:type")) {
                            type = separated[k].split("=")[1].split("\"")[1].split(":")[1];
                        }
                        else if (separated[k].contains("xmi:id")) {
                            id = separated[k].split("=")[1].split("\"")[1];
                        }
                        else if (separated[k].contains("name")) {
                            name = separated[k].split("=")[1];
                            if (!name.endsWith("\"") && !name.endsWith(">")) {
                                flag = true;
                                int l = k;
                                while (flag){
                                    l++;
                                    name = name + " " + separated[l];
                                    if (separated[l].contains("\"")) {
                                        flag = false;
                                    }
                                }
                            }
                            name = name.split("\"")[1];
                        }
                    }
                    if (name.isEmpty()) {
                        name = type;
                    }
                    Node n = new Node(id, name, type);
                    Nodes.add(n);
                }
            }
        }
    }
    
    public static void searchEdges() throws IOException{
        
        boolean flag;
        for (int i = 0; i < pathDiagrams.size(); i++) {
            DiagramFile = new ArrayList<String>();
            XMI(pathDiagrams.get(i),typePathDiagram);
            for (int j = 0; j < DiagramFile.size(); j++) {
                String[] separated = DiagramFile.get(j).split(" ");
                if (separated[0].equals("<edge")) {
                    String id = new String();
                    String name = new String();
                    Node source = null;
                    Node target = null;
                    String idSource = new String();
                    String idTarget = new String();
                    for (int k = 0; k < separated.length; k++) {
                        if (separated[k].contains("xmi:id")) {
                            id = separated[k].split("=")[1].split("\"")[1];
                        }
                        else if (separated[k].contains("name")) {
                            name = separated[k].split("=")[1];
                            if (!name.endsWith("\"") && !name.endsWith(">")) {
                                flag = true;
                                int l = k;
                                while (flag){
                                    l++;
                                    name = name + " " + separated[l];
                                    if (separated[l].contains("\"")) {
                                        flag = false;
                                    }
                                }
                            }
                            name = name.split("\"")[1];
                        }
                        else if (separated[k].contains("source")) {
                            //String 
                            idSource = separated[k].split("=")[1].split("\"")[1];
                            for (Node node : Nodes) {
                                if (node.getId().equals(idSource)) {
                                    source = node;
                                }
                            }
                        }
                        else if (separated[k].contains("target")) {
                            //String
                            idTarget = separated[k].split("=")[1].split("\"")[1];
                            for (Node node : Nodes) {
                                if (node.getId().equals(idTarget)) {
                                    target = node;
                                }
                            }
                        }
                    }
                    Edge e = new Edge(id, name, source, target);
                    Edges.add(e);
                }
            }
        }
    }
    
    public static void searchPositions() throws IOException{
        for (int i = 0; i < pathDiagrams.size(); i++) {
            DiagramFile = new ArrayList<String>();
            XMI(pathDiagrams.get(i), typePathDiagram);
            String id = new String();
            int x = -1;
            int y = -1;
            for (int j = 0; j < DiagramFile.size(); j++) {
                String[] separated = DiagramFile.get(j).split(" ");
                if (separated[0].equals("<children") && separated[1].equals("xmi:type=\"notation:Node\"")) {
                    for (int k = 0; k < separated.length; k++) {
                        if (separated[k].contains("element")) {
                            id = separated[k].split("=")[1].split("\"")[1];
                        }
                    }
                }
                else if(separated[0].equals("<layoutConstraint") && separated[1].equals("xmi:type=\"notation:Bounds\"")){
                    for (int k = 0; k < separated.length; k++) {
                        if (separated[k].contains("x=\"")) {
                            x = Integer.parseInt(separated[k].split("=")[1].split("\"")[1]);
                        }
                        else if (separated[k].contains("y=\"")) {
                            y = Integer.parseInt(separated[k].split("=")[1].split("\"")[1]);
                        }
                    }   
                }
                else if ( (!id.isEmpty()) && (x != -1) && (y != -1) ) {
                    Position p = new Position(id, x, y);
                    Positions.add(p);
                    id = new String();
                    x = -1;
                    y = -1;
                }
            }
        }
    }
    
    public static void searchWorkFlows() throws IOException{
        
        boolean flag;
        for (int i = 0; i < pathDiagrams.size(); i++) {
            DiagramFile = new ArrayList<String>();
            XMI(pathDiagrams.get(i),typePathDiagram);
            String id = new String();
            String name = new String();
            ArrayList<Node> nodesWorkFlow = new ArrayList<Node>();
            ArrayList<Edge> edgesWorkFlow = new ArrayList<Edge>();
            ArrayList<Position> positionsWorkFlow = new ArrayList<Position>();

            for (int j = 0; j < DiagramFile.size(); j++) {
                String[] separated = DiagramFile.get(j).split(" ");

                if (separated[0].equals("<uml:Activity")) {
                    id = new String();
                    name = new String();
                    nodesWorkFlow = new ArrayList<Node>();
                    edgesWorkFlow = new ArrayList<Edge>();
                    positionsWorkFlow = new ArrayList<Position>();
                    
                    for (int k = 0; k < separated.length; k++) {
                        if (separated[k].contains("xmi:id")) {
                            id = separated[k].split("=")[1].split("\"")[1];
                        }
                        else if (separated[k].contains("name")) {
                            name = separated[k].split("=")[1];
                            if (!name.endsWith("\"") && !name.endsWith(">")) {
                                flag = true;
                                int l = k;
                                while (flag){
                                    l++;
                                    name = name + " " + separated[l];
                                    if (separated[l].contains("\"")) {
                                        flag = false;
                                    }
                                }
                            }
                            name = name.split("\"")[1];
                        }
                    }
                }
                else if(separated[0].equals("<node") || separated[0].equals("<edge") ){
                    String idAux = new String();
                    for (int k = 0; k < separated.length; k++) {
                        if (separated[k].contains("xmi:id")) {
                            idAux = separated[k].split("=")[1].split("\"")[1];
                        }
                    }
                    if (separated[0].equals("<node")) {
                        for (Node node : Nodes) {
                            if (node.getId().equals(idAux)) {
                                nodesWorkFlow.add(node);
                            }
                        }    
                    }
                    else if (separated[0].equals("<edge")) {
                        for (Edge edge : Edges) {
                            if (edge.getId().equals(idAux)) {
                                edgesWorkFlow.add(edge);
                            }
                        }    
                    }
                }
                else if (separated[0].equals("<children") && separated[1].equals("xmi:type=\"notation:Node\"")) {
                    String idAux = new String();
                    for (int k = 0; k < separated.length; k++) {
                        if (separated[k].contains("element")) {
                            idAux = separated[k].split("=")[1].split("\"")[1];
                        }
                    }
                    for (Position position : Positions) {
                        if (position.getId().equals(idAux)) {
                            positionsWorkFlow.add(position);
                        }
                    }
                    
                }
                else if(separated[0].equals("</notation:Diagram>")){
                    WorkFlow wf = new WorkFlow(id, name, nodesWorkFlow, edgesWorkFlow, positionsWorkFlow);
                    WorkFlows.add(wf);
                }
            }
        }
    }
    
}
