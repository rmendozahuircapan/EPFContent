/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tool;

import static Tool.App.*;
import static Tool.ReadFileXMI.*;
import WorkFlow.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Rodrigo
 */
public class SearchWorkFlow {
    
    public static void searchElementsWorkFlow() throws IOException{
        searchNodes();
        searchEdges();
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
    
    public static void searchWorkFlows() throws IOException{
        
        boolean flag;
        for (int i = 0; i < pathDiagrams.size(); i++) {
            DiagramFile = new ArrayList<String>();
            XMI(pathDiagrams.get(i),typePathDiagram);
            String id = new String();
            String name = new String();
            ArrayList<Node> nodesWorkFlow = new ArrayList<Node>();
            ArrayList<Edge> edgesWorkFlow = new ArrayList<Edge>();

            for (int j = 0; j < DiagramFile.size(); j++) {
                String[] separated = DiagramFile.get(j).split(" ");

                if (separated[0].equals("<uml:Activity")) {
                    id = new String();
                    name = new String();
                    nodesWorkFlow = new ArrayList<Node>();
                    edgesWorkFlow = new ArrayList<Edge>();
                    
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
                else if(separated[0].equals("</uml:Activity>")){
                    WorkFlow wf = new WorkFlow(id, name, nodesWorkFlow, edgesWorkFlow);
                    WorkFlows.add(wf);
                }
            }
        }
    }
    
}
