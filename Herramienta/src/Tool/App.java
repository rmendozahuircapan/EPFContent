package Tool;

import ProcessElements.*;
import WorkFlow.*;

import static Tool.SearchProcessElements.*;
import static Tool.SearchWorkFlow.*;
import static Tool.ShowProcessElements.*;
import static Tool.ShowWorkFlow.*;
import static WorkFlow.DrawWorkFlow.*;

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
    static public ArrayList<WorkFlow> WorkFlows = new ArrayList<WorkFlow>();
    static public ArrayList<Position> Positions = new ArrayList<Position>();
    
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
        for (WorkFlow wf : WorkFlows) {
            DrawWorkFlow(wf.getId());
        }
    }   
}