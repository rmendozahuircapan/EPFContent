package Tool;

import ProcessElements.*;
import WorkFlow.*;

import static Tool.SearchProcessElements.*;
import static Tool.SearchWorkFlow.*;
import java.io.*;
import java.util.*;


public class App {
    
    static ArrayList<String> PluginFile = new ArrayList<String>();
    static ArrayList<String> TaskFile = new ArrayList<String>();
    static ArrayList<String> ModelFile = new ArrayList<String>();
    static ArrayList<String> DiagramFile = new ArrayList<String>();
    
    static ArrayList<Role> Roles = new ArrayList<Role>();
    static ArrayList<Template> Templates = new ArrayList<Template>();
    static ArrayList<WorkProduct> WorkProducts = new ArrayList<WorkProduct>();
    static ArrayList<Task> Tasks = new ArrayList<Task>();
    static ArrayList<Step> Steps = new ArrayList<Step>();
    static ArrayList<Activity> Activities = new ArrayList<Activity>();
    static ArrayList<Processes> Processes = new ArrayList<Processes>();
    
    static ArrayList<Node> Nodes = new ArrayList<Node>();
    static ArrayList<Edge> Edges = new ArrayList<Edge>();
    static ArrayList<Position> Positions = new ArrayList<Position>();
    static ArrayList<WorkFlow> WorkFlows = new ArrayList<WorkFlow>();
    
    //static String mainFolder = "C:\\Users\\Rodrigo\\Desktop\\Library\\Formalización Proceso";
    static String mainFolder = "C:\\Users\\Rodrigo\\Desktop\\Library3\\workflow";
    //static String mainFolder = "C:\\Users\\Rodrigo\\Desktop\\Proceso\\proceso_de_prueba";
    
    static String pathPlugin = mainFolder + "\\plugin.xmi";
    
    static ArrayList<String> pathTasks = new ArrayList<String>();
    static ArrayList<String> pathModels = new ArrayList<String>();
    static ArrayList<String> pathDiagrams = new ArrayList<String>();
    
    static String typePathPlugin = "Plugin";
    static String typePathTask = "Task";
    static String typePathModel = "Model";
    static String typePathDiagram = "Diagram";
    
    /*************************************************************************************************************/
    
    public static void main(String[] args) throws IOException {
        
        searchProcessElements();
        searchElementsWorkFlow();
        //resumeProcessElements();
        //resumeWorkFlow();
        //showWorkFlow();

        
        Node n = new Node("a", "a", "hola");
        Node m = new Node("b", "b", "chao");
        Position p = new Position("a", 88, 88);
        Position q = new Position("b", 88, 188);
        Edge e = new Edge("id", "asd", n, m);
        
        ArrayList<Node> No = new ArrayList<Node>();
        ArrayList<Edge> Ed = new ArrayList<Edge>();
        ArrayList<Position> Po = new ArrayList<Position>();
        
        No.add(n);
        No.add(m);
        Po.add(p);
        Po.add(q);
        Ed.add(e);
        
        WorkFlow a = new WorkFlow("id", "Nodo", No, Ed, Po);
        WorkFlows.add(a);
        for (WorkFlow wf : WorkFlows) {
            wf.getGraph();
        }
    }   
}