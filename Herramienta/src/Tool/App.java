package Tool;

import DeliveryProcess.*;
import ProcessElements.*;
import ProcessElements.Process;


import java.io.*;
import java.util.*;


public class App {
    
    static ArrayList<Role> RolesApp = new ArrayList<Role>();
    static ArrayList<Template> TemplatesApp = new ArrayList<Template>();
    static ArrayList<WorkProduct> WorkProductsApp = new ArrayList<WorkProduct>();
    static ArrayList<Task> TasksApp = new ArrayList<Task>();
    static ArrayList<Step> StepsApp = new ArrayList<Step>();
    static ArrayList<Activity> ActivitiesApp = new ArrayList<Activity>();
    static ArrayList<Process> ProcessesApp = new ArrayList<Process>();
    
    static ArrayList<Node> Nodes1 = new ArrayList<Node>();
    static ArrayList<Edge> Edges1 = new ArrayList<Edge>();
    static ArrayList<Position> Positions1 = new ArrayList<Position>();
    static ArrayList<WorkFlow> WorkFlows1 = new ArrayList<WorkFlow>();
    
    //static String mainFolder = "C:\\Users\\Rodrigo\\Desktop\\Library\\Formalización Proceso";
    //static String mainFolder = "C:\\Users\\Rodrigo\\Desktop\\Library3\\workflow";
    static String mainFolder = "C:\\Users\\Rodrigo\\Desktop\\Proceso\\proceso_de_prueba";
    //static String mainFolder = "C:\\Users\\Rodrigo\\Desktop\\Proceso2\\Prueba final";
    //static String mainFolder = "C:\\Users\\Rodrigo\\Desktop\\Proceso3\\Prueba2";
    
    static String pathPlugin = mainFolder + "\\plugin.xmi";


    /*************************************************************************************************************/
    
    public static void main(String[] args) throws IOException {
        
        //Tool.SearchProcessElements.searchProcessElements();
        ProcessElements PE = new ProcessElements(mainFolder);
        RolesApp = PE.getRoles();
        TemplatesApp = PE.getTemplates();
        WorkProductsApp = PE.getWorkProducts();
        TasksApp = PE.getTasks();
        StepsApp = PE.getSteps();
        ActivitiesApp = PE.getActivities();
        ProcessesApp = PE.getProcesses();
        
        PE.XMI(pathPlugin);
        
        DeliveryProcess DP = new DeliveryProcess(mainFolder);
        Nodes1 = DP.getNodes();
        Edges1 = DP.getEdges();
        Positions1 = DP.getPositions();
        WorkFlows1 = DP.getWorkFlows();
        
        for (WorkFlow wf : WorkFlows1) {
            wf.getGraph();
        }
        
        
        resumeProcessElements();
        resumeWorkFlow();
        
        showAll();
    }
    /*************************************************************************************************************/
    
    public static void resumeProcessElements(){
        
        System.out.println("-------------------------------------");
        System.out.println("Roles: "+RolesApp.size());
        System.out.println("Templates: "+TemplatesApp.size());
        System.out.println("WorkProducts: "+WorkProductsApp.size());
        System.out.println("Tasks: "+TasksApp.size());
        System.out.println("Steps: "+StepsApp.size());
        System.out.println("Activities: "+ActivitiesApp.size());
        System.out.println("Processes: "+ProcessesApp.size());
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
        for (Role role : RolesApp) {
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
        for (Template template : TemplatesApp) {
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
        for (WorkProduct wp : WorkProductsApp) {
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
        for (Task task : TasksApp) {
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
        for (Step step : StepsApp) {
            System.out.println("Step "+(++i));
            System.out.println("Name: "+step.getName());
            System.out.println("Id: "+step.getId());
            System.out.println("        *******         ");
        }
    }
    
    public static void showActivities(){
        int i = 0;
        for (Activity activity : ActivitiesApp) {
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
        for (Process process : ProcessesApp) {
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
        System.out.println("Nodes: "+Nodes1.size());
        System.out.println("Edges: "+Edges1.size());
        System.out.println("Positions: "+Positions1.size());
        System.out.println("WorkFlows: "+WorkFlows1.size());
        System.out.println("-------------------------------------");        
    }
    
    public static void showWorkFlow() {
        System.out.println("-------------------------------------");
        for (WorkFlow wf : WorkFlows1) {
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