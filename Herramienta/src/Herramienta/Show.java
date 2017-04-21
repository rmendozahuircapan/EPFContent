
package Herramienta;

import static Herramienta.Herramienta.*;
import ProcessElements.*;

public class Show {
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
        for (Processes process : Processes) {
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
}
