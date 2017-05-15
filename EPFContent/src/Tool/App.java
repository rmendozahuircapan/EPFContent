package Tool;

import ContentElements.WorkProductElement;
import ContentElements.TemplateElement;
import ContentElements.ContentElements;
import ContentElements.RoleElement;
import ContentElements.ActivityElement;
import ContentElements.TaskElement;
import ContentElements.StepElement;
import ContentElements.ProcessElement;

import DeliveryProcess.*;


import java.io.*;
import java.util.*;


public class App {
    /*************************************************************************************************************/
    
    public static void main(String[] args) throws IOException {

        String Folder0 = "C:\\Users\\Rodrigo\\Desktop\\Library\\Formalización Proceso";
        String Folder1 = "C:\\Users\\Rodrigo\\Desktop\\Library3\\workflow";
        String Folder2 = "C:\\Users\\Rodrigo\\Desktop\\Proceso\\proceso_de_prueba";
        String Folder3 = "C:\\Users\\Rodrigo\\Desktop\\Proceso2\\Prueba final";
        String Folder4 = "C:\\Users\\Rodrigo\\Desktop\\Proceso3\\Prueba2";
        
        ContentElements PE = new ContentElements(Folder2);

        DeliveryProcess DP = new DeliveryProcess(Folder2);
        
        resumeProcessElements(PE);
        resumeWorkFlow(DP);
        
        showAll(PE,DP);
    }
    /*************************************************************************************************************/
    
    public static void resumeProcessElements(ContentElements PE){
        
        System.out.println("-------------------------------------");
        System.out.println("Roles: "+PE.getRoles().size());
        System.out.println("Templates: "+PE.getTemplates().size());
        System.out.println("WorkProducts: "+PE.getWorkProducts().size());
        System.out.println("Tasks: "+PE.getTasks().size());
        System.out.println("Steps: "+PE.getSteps().size());
        System.out.println("Activities: "+PE.getActivities().size());
        System.out.println("Processes: "+PE.getProcesses().size());
        System.out.println("-------------------------------------");
    }
    
    public static void showAll(ContentElements pe, DeliveryProcess dp){
        showRoles(pe.getRoles());
        System.out.println("-------------------------------------");
        showTemplates(pe.getTemplates());
        System.out.println("-------------------------------------");
        showWorkProducts(pe.getWorkProducts());
        System.out.println("-------------------------------------");
        showTasks(pe.getTasks());
        System.out.println("-------------------------------------");
        showSteps(pe.getSteps());
        System.out.println("-------------------------------------");
        showActivities(pe.getActivities());
        System.out.println("-------------------------------------");
        showProcesses(pe.getProcesses());
        System.out.println("-------------------------------------");
        showWorkFlow(dp);
        
    }
    
    public static void showRoles(ArrayList<RoleElement> RolesApp){
        int i = 0;
        for (RoleElement role : RolesApp) {
            System.out.println("Role "+(++i));
            System.out.println("PresentationName: "+role.getPresentationName());
            System.out.println("Name: "+role.getName());
            System.out.println("Id: "+role.getId());
            System.out.println("Description: "+role.getDescription());
            System.out.println("        *******         ");
        }
    }
    
    public static void showTemplates(ArrayList<TemplateElement> TemplatesApp){
        int i = 0;
        for (TemplateElement template : TemplatesApp) {
            System.out.println("Template "+(++i));
            System.out.println("PresentationName: "+template.getPresentationName());
            System.out.println("Name: "+template.getName());
            System.out.println("Id: "+template.getId());
            System.out.println("Description: "+template.getDescription());
            System.out.println("        *******         ");
        }
    }
    
    public static void showWorkProducts(ArrayList<WorkProductElement> WorkProductsApp){
        int i = 0;
        for (WorkProductElement wp : WorkProductsApp) {
            System.out.println("WorkProduct "+(++i));
            System.out.println("PresentationName: "+wp.getPresentationName());
            System.out.println("Name: "+wp.getName());
            System.out.println("Id: "+wp.getId());
            System.out.println("Type: "+wp.getType());
            System.out.println("Description: "+wp.getDescription());
            System.out.println("Templates: "+wp.getTemplates().size());
            for (TemplateElement template : wp.getTemplates()) {
                System.out.println("    - "+template.getPresentationName());
            }
            System.out.println("        *******         ");
        }
    }
    
    public static void showTasks(ArrayList<TaskElement> TasksApp){
        int i = 0;
        for (TaskElement task : TasksApp) {
            System.out.println("Task "+(++i));
            System.out.println("PresentationName: "+task.getPresentationName());
            System.out.println("Name: "+task.getName());
            System.out.println("Id: "+task.getId());
            System.out.println("Description: "+task.getDescription());
            System.out.println("Producers: "+task.getProducers().size());
            for (RoleElement role : task.getProducers()) {
                System.out.println("    - "+role.getPresentationName());
            }
            System.out.println("Collaborators: "+task.getCollaborators().size());
            for (RoleElement role : task.getCollaborators()) {
                System.out.println("    - "+role.getPresentationName());
            }
            System.out.println("Inputs: "+task.getInputs().size());
            for (WorkProductElement wp : task.getInputs()) {
                System.out.println("    - "+wp.getPresentationName());
            }
            System.out.println("Outputs: "+task.getOutputs().size());
            for (WorkProductElement wp : task.getOutputs()) {
                System.out.println("    - "+wp.getPresentationName());
            }
            System.out.println("Steps: "+task.getSteps().size());
            for (StepElement step : task.getSteps()) {
                System.out.println("    - "+step.getName());
            }
            System.out.println("        *******         ");
        }
    }
    
    public static void showSteps(ArrayList<StepElement> StepsApp){
        int i = 0;
        for (StepElement step : StepsApp) {
            System.out.println("Step "+(++i));
            System.out.println("Name: "+step.getName());
            System.out.println("Id: "+step.getId());
            System.out.println("        *******         ");
        }
    }
    
    public static void showActivities(ArrayList<ActivityElement> ActivitiesApp){
        int i = 0;
        for (ActivityElement activity : ActivitiesApp) {
            System.out.println("Activity "+(++i));
            System.out.println("Name: "+activity.getName());
            System.out.println("Id: "+activity.getId());
            System.out.println("Tasks: ");
            for (TaskElement task : activity.getTasks()) {
                System.out.println("    - "+task.getPresentationName());
            }
            System.out.println("        *******         ");
        }
    }
    
    public static void showProcesses(ArrayList<ProcessElement> ProcessesApp){
        int i = 0;
        for (ProcessElement process : ProcessesApp) {
            System.out.println("Process "+(++i));
            System.out.println("Name: "+process.getName());
            System.out.println("Id: "+process.getId());
            System.out.println("Activities: ");
            for (ActivityElement activity : process.getActivities()) {
                System.out.println("    - "+activity.getName());
            }
            System.out.println("        *******         ");
        }
    }
    
    /*************************************************************************************************************/
    
    public static void resumeWorkFlow(DeliveryProcess dp) {
        System.out.println("-------------------------------------");
        System.out.println("Nodes: "+dp.getNodes().size());
        System.out.println("Edges: "+dp.getEdges().size());
        System.out.println("WorkFlows: "+dp.getWorkFlows().size());
        System.out.println("-------------------------------------");        
    }
    
    public static void showWorkFlow(DeliveryProcess dp) {
        System.out.println("-------------------------------------");
        for (WorkFlow wf : dp.getWorkFlows()) {
            System.out.println("name: "+wf.getName());
            System.out.println(wf.getNodes().size()+" nodes "+wf.getEdges().size()+" edges ");
            System.out.println("");
            for (Edge e : wf.getEdges()) {
                System.out.println(e.getSource().getName()+" -> "+e.getTarget().getName()+" ["+e.getName()+"]");
            }
            wf.getGraph();
            System.out.println("---");
        }
        
    }
    
    /*************************************************************************************************************/
}