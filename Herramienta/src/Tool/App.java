package Tool;

import DeliveryProcesses.Position;
import DeliveryProcesses.WorkFlow;
import DeliveryProcesses.Node;
import DeliveryProcesses.Edge;
import ProcessElements.*;
import ProcessElements.Process;

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
    static ArrayList<Process> Processes = new ArrayList<Process>();
    
    static ArrayList<Node> Nodes = new ArrayList<Node>();
    static ArrayList<Edge> Edges = new ArrayList<Edge>();
    static ArrayList<Position> Positions = new ArrayList<Position>();
    static ArrayList<WorkFlow> WorkFlows = new ArrayList<WorkFlow>();
    
    //static String mainFolder = "C:\\Users\\Rodrigo\\Desktop\\Library\\Formalización Proceso";
    //static String mainFolder = "C:\\Users\\Rodrigo\\Desktop\\Library3\\workflow";
    static String mainFolder = "C:\\Users\\Rodrigo\\Desktop\\Proceso\\proceso_de_prueba";
    //static String mainFolder = "C:\\Users\\Rodrigo\\Desktop\\Proceso1\\nodoInicio";
    //static String mainFolder = "C:\\Users\\Rodrigo\\Desktop\\Proceso2\\Prueba final";
    //static String mainFolder = "C:\\Users\\Rodrigo\\Desktop\\Proceso3\\Prueba2";
    
    static String pathPlugin = mainFolder + "\\plugin.xmi";
    
    static ArrayList<String> pathTasks = new ArrayList<String>();
    static ArrayList<String> pathModels = new ArrayList<String>();
    static ArrayList<String> pathDiagrams = new ArrayList<String>();


    /*************************************************************************************************************/
    
    public static void main(String[] args) throws IOException {
        
        Tool.SearchProcessElements.searchProcessElements();
        Tool.SearchWorkFlow.searchElementsWorkFlow();
        
        
        for (WorkFlow wf : WorkFlows) {
            wf.getGraph();
        }
        
        
        resumeProcessElements();
        resumeWorkFlow();
        
        showAll();
    }
    /*************************************************************************************************************/
    
    public static void resumeProcessElements(){
        
        System.out.println("-------------------------------------");
        System.out.println("Roles: "+Roles.size());
        System.out.println("Templates: "+Templates.size());
        System.out.println("WorkProducts: "+WorkProducts.size());
        System.out.println("Tasks: "+Tasks.size());
        System.out.println("Steps: "+Steps.size());
        System.out.println("Activities: "+Activities.size());
        System.out.println("Processes: "+Processes.size());
        System.out.println("-------------------------------------");
    }
    
    public static void showAll(){
        showRoles();
        System.out.println("-------------------------------------");
        showTemplates();
        System.out.println("-------------------------------------");
        showWorkProducts();
        System.out.println("-------------------------------------");
        showTasks();
        System.out.println("-------------------------------------");
        showSteps();
        System.out.println("-------------------------------------");
        showActivities();
        System.out.println("-------------------------------------");
        showProcesses();
        System.out.println("-------------------------------------");
        showWorkFlow();
        
    }
    
    public static void showRoles(){
        int i = 0;
        for (Role role : Roles) {
            System.out.println("Role "+(++i));
            System.out.println("PresentationName: "+role.getPresentationName());
            System.out.println("Name: "+role.getName());
            System.out.println("Id: "+role.getId());
            System.out.println("Description: "+role.getDescription());
            System.out.println("        *******         ");
        }
    }
    
    public static void showTemplates(){
        int i = 0;
        for (Template template : Templates) {
            System.out.println("Template "+(++i));
            System.out.println("PresentationName: "+template.getPresentationName());
            System.out.println("Name: "+template.getName());
            System.out.println("Id: "+template.getId());
            System.out.println("Description: "+template.getDescription());
            System.out.println("        *******         ");
        }
    }
    
    public static void showWorkProducts(){
        int i = 0;
        for (WorkProduct wp : WorkProducts) {
            System.out.println("WorkProduct "+(++i));
            System.out.println("PresentationName: "+wp.getPresentationName());
            System.out.println("Name: "+wp.getName());
            System.out.println("Id: "+wp.getId());
            System.out.println("Type: "+wp.getType());
            System.out.println("Description: "+wp.getDescription());
            System.out.println("Templates: "+wp.getTemplates().size());
            for (Template template : wp.getTemplates()) {
                System.out.println("    - "+template.getPresentationName());
            }
            System.out.println("        *******         ");
        }
    }
    
    public static void showTasks(){
        int i = 0;
        for (Task task : Tasks) {
            System.out.println("Task "+(++i));
            System.out.println("PresentationName: "+task.getPresentationName());
            System.out.println("Name: "+task.getName());
            System.out.println("Id: "+task.getId());
            System.out.println("Description: "+task.getDescription());
            System.out.println("Producers: "+task.getProducers().size());
            for (Role role : task.getProducers()) {
                System.out.println("    - "+role.getPresentationName());
            }
            System.out.println("Collaborators: "+task.getCollaborators().size());
            for (Role role : task.getCollaborators()) {
                System.out.println("    - "+role.getPresentationName());
            }
            System.out.println("Inputs: "+task.getInputs().size());
            for (WorkProduct wp : task.getInputs()) {
                System.out.println("    - "+wp.getPresentationName());
            }
            System.out.println("Outputs: "+task.getOutputs().size());
            for (WorkProduct wp : task.getOutputs()) {
                System.out.println("    - "+wp.getPresentationName());
            }
            System.out.println("Steps: "+task.getSteps().size());
            for (Step step : task.getSteps()) {
                System.out.println("    - "+step.getName());
            }
            System.out.println("        *******         ");
        }
    }
    
    public static void showSteps(){
        int i = 0;
        for (Step step : Steps) {
            System.out.println("Step "+(++i));
            System.out.println("Name: "+step.getName());
            System.out.println("Id: "+step.getId());
            System.out.println("        *******         ");
        }
    }
    
    public static void showActivities(){
        int i = 0;
        for (Activity activity : Activities) {
            System.out.println("Activity "+(++i));
            System.out.println("Name: "+activity.getName());
            System.out.println("Id: "+activity.getId());
            System.out.println("Tasks: ");
            for (Task task : activity.getTasks()) {
                System.out.println("    - "+task.getPresentationName());
            }
            System.out.println("        *******         ");
        }
    }
    
    public static void showProcesses(){
        int i = 0;
        for (Process process : Processes) {
            System.out.println("Process "+(++i));
            System.out.println("Name: "+process.getName());
            System.out.println("Id: "+process.getId());
            System.out.println("Activities: ");
            for (Activity activity : process.getActivities()) {
                System.out.println("    - "+activity.getName());
            }
            System.out.println("        *******         ");
        }
    }
    
    /*************************************************************************************************************/
    
    public static void resumeWorkFlow() {
        System.out.println("-------------------------------------");
        System.out.println("Nodes: "+Nodes.size());
        System.out.println("Edges: "+Edges.size());
        System.out.println("Positions: "+Positions.size());
        System.out.println("WorkFlows: "+WorkFlows.size());
        System.out.println("-------------------------------------");        
    }
    
    public static void showWorkFlow() {
        System.out.println("-------------------------------------");
        for (WorkFlow wf : WorkFlows) {
            System.out.println("name: "+wf.getName());
            System.out.println(wf.getNodes().size()+" nodes "+wf.getEdges().size()+" edges " + wf.getPositions().size()+" positions");
            System.out.println("");
            for (Edge e : wf.getEdges()) {
                System.out.println(e.getSource().getName()+" -> "+e.getTarget().getName()+" ["+e.getName()+"]");
            }
            System.out.println("---");
        }
        
    }
    
    /*************************************************************************************************************/
}