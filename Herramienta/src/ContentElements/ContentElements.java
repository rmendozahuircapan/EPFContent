
package ContentElements;

import java.io.*;
import java.util.*;


public class ContentElements {
    
    private static ArrayList<ActivityElement> Activities;
    private static ArrayList<ProcessElement> Processes;
    private static ArrayList<RoleElement> Roles;
    private static ArrayList<StepElement> Steps;
    private static ArrayList<TaskElement> Tasks;
    private static ArrayList<TemplateElement> Templates;
    private static ArrayList<WorkProductElement> WorkProducts;
    
    private static ArrayList<String> pathTasks = new ArrayList<String>();
    private static ArrayList<String> pathModels = new ArrayList<String>();
    
    private static String pathPlugin;
    private static String mainFolder;
    
    public ContentElements(String Folder) throws IOException {
        setMainFolder(Folder);
        searchResourceDescriptors();
        ContentElements.Roles = searchRoles();
        ContentElements.Templates = searchTemplates();
        ContentElements.WorkProducts = searchWorkProducts();
        ContentElements.Tasks = searchTasks();
        ContentElements.Steps = searchSteps();
        ContentElements.Activities = searchActivities();
        ContentElements.Processes = searchProcesses();
    }
    
    public static ArrayList<ActivityElement> getActivities() {
        return Activities;
    }

    public static ArrayList<ProcessElement> getProcesses() {
        return Processes;
    }

    public static ArrayList<RoleElement> getRoles() {
        return Roles;
    }

    public static ArrayList<StepElement> getSteps() {
        return Steps;
    }

    public static ArrayList<TaskElement> getTasks() {
        return Tasks;
    }

    public static ArrayList<TemplateElement> getTemplates() {
        return Templates;
    }

    public static ArrayList<WorkProductElement> getWorkProducts() {
        return WorkProducts;
    }
    
    private static void setPathPlugin(String pathPlugin) {
        ContentElements.pathPlugin = pathPlugin;
        
    }

    private static void setMainFolder(String mainFolder) {
        ContentElements.mainFolder = mainFolder;
        setPathPlugin(mainFolder + "\\plugin.xmi");
    }
   
    
    
    private static void searchResourceDescriptors() throws IOException{
        ArrayList<String> PluginFile = new ArrayList<String>();
        PluginFile = XMIRead(pathPlugin);
        for (int i = 0; i < PluginFile.size(); i++) {
            String[] separated = PluginFile.get(i).split(" ");
            if (separated[0].contains("resourceDescriptors")) {
                for (int j = 0; j < separated.length; j++) {
                    if (separated[j].contains("uri")) {
                        String uri = separated[j].split("=")[1].split("/>")[0].split("\"")[1];
                        String uriType = uri.split("/")[0];
                        String path = mainFolder;
                        if (uriType.equals("deliveryprocesses")) {
                            for (int k = 0; k < uri.split("/").length; k++) {
                                String part = uri.split("/")[k].replace("%20", " ");
                                path = path + "\\" + part;
                            }
                            pathModels.add(path);
                        }
                        else if (uriType.equals("tasks")) {
                            for (int k = 0; k < uri.split("/").length; k++) {
                                String part = uri.split("/")[k].replace("%20", " ");
                                path = path + "\\" + part;
                            }
                            pathTasks.add(path);
                        }
                    }
                }
            }
        }
    }
    
    private static ArrayList<RoleElement> searchRoles() throws IOException {
        ArrayList<RoleElement> roles = new ArrayList<RoleElement>();
        ArrayList<String> PluginFile = new ArrayList<String>();
        PluginFile = XMIRead(pathPlugin);
        boolean flag;
        for (int i = 0; i < PluginFile.size(); i++) {
            String[] separated = PluginFile.get(i).split(" ");
            if (separated[0].contains("contentElements")) {
                for (int j = 0; j < separated.length; j++) {
                    if (separated[j].contains("org.eclipse.epf.uma:Role")) {
                        String id = new String();
                        String name = new String();
                        String presentationName = new String();
                        String description = new String();
                        for (int k = 0; k < separated.length; k++) {
                            if (separated[k].contains("xmi:id")) {
                                id = separated[k].split("=")[1].split("\"")[1];
                            }
                            else if (separated[k].contains("name")) {
                                name = separated[k].split("=")[1].split("\"")[1];
                            }
                            else if (separated[k].contains("presentationName")) {
                                presentationName = separated[k].split("=")[1];
                                if (!presentationName.endsWith("\"") && !presentationName.endsWith(">") ) {
                                    flag = true;
                                    int l = k;
                                    while (flag){
                                        l++;
                                        presentationName = presentationName + " " + separated[l];
                                        if (separated[l].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                presentationName = presentationName.split("\"")[1];
                            }
                            else if (separated[k].contains("briefDescription")) {
                                description = separated[k].split("=")[1];
                                if(!description.endsWith("\"") && !description.endsWith(">")){
                                    flag = true;
                                    int l = k;
                                    while (flag){
                                        l++;
                                        description = description + " " + separated[l];
                                        if (separated[l].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                description = description.split("\"")[1];
                            }
                        }
                        RoleElement r = new RoleElement(id, name, presentationName, description);
                        roles.add(r);
                    }
                }
            }
        }
        return roles;
    }
    
    private static ArrayList<TemplateElement> searchTemplates() throws IOException{
        ArrayList<TemplateElement> templates = new ArrayList<TemplateElement>();
        ArrayList<String> PluginFile = new ArrayList<String>();
        PluginFile = XMIRead(pathPlugin);
        boolean flag;
        for (int i = 0; i < PluginFile.size(); i++) {
            String[] separated = PluginFile.get(i).split(" ");
            if (separated[0].contains("contentElements")) {
                for (int j = 0; j < separated.length; j++) {
                    if (separated[j].contains("org.eclipse.epf.uma:Template")) {
                        String id = new String();
                        String name = new String();
                        String presentationName = new String();
                        String description = new String();
                        for (int k = 0; k < separated.length; k++) {
                            if (separated[k].contains("xmi:id")) {
                                id = separated[k].split("=")[1].split("\"")[1];
                            }
                            else if (separated[k].contains("name")) {
                                name = separated[k].split("=")[1].split("\"")[1];
                            }
                            else if (separated[k].contains("presentationName")) {
                                presentationName = separated[k].split("=")[1];
                                if (!presentationName.endsWith("\"") && !presentationName.endsWith(">")) {
                                    flag = true;
                                    int l = k;
                                    while (flag){
                                        l++;
                                        presentationName = presentationName + " " + separated[l];
                                        if (separated[l].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                presentationName = presentationName.split("\"")[1];
                            }
                            else if (separated[k].contains("briefDescription")) {
                                description = separated[k].split("=")[1];
                                if (!description.endsWith("\"") && !description.endsWith(">")) {
                                    flag = true;
                                    int l = k;
                                    while (flag){
                                        l++;
                                        description = description + " " + separated[l];
                                        if (separated[l].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                description = description.split("\"")[1];
                            }
                        }
                        TemplateElement t = new TemplateElement(id, name, presentationName, description);
                        templates.add(t);
                    }
                }
            }
        }
        return templates;
    }
    
    private static ArrayList<WorkProductElement> searchWorkProducts() throws IOException {
        ArrayList<WorkProductElement> workproducts = new ArrayList<WorkProductElement>();
        ArrayList<String> PluginFile = new ArrayList<String>();
        PluginFile = XMIRead(pathPlugin);
        boolean flag;
        for (int i = 0; i < PluginFile.size(); i++) {
            String[] separated = PluginFile.get(i).split(" ");
            if (separated[0].contains("contentElements")) {
                for (int j = 0; j < separated.length; j++) {
                    if (separated[j].contains("org.eclipse.epf.uma:Artifact") || separated[j].contains("org.eclipse.epf.uma:Deliverable") || separated[j].contains("org.eclipse.epf.uma:Outcome")) {
                        String id = new String();
                        String name = new String();
                        String presentationName = new String();
                        String description = new String();
                        String type = new String();
                        String templatesLine = new String();
                        ArrayList<TemplateElement> templates = new ArrayList<TemplateElement>();
                        for (int k = 0; k < separated.length; k++) {
                            if (separated[k].contains("xmi:id")) {
                                id = separated[k].split("=")[1].split("\"")[1];
                            }
                            else if (separated[k].contains("name")) {
                                name = separated[k].split("=")[1].split("\"")[1];
                            }
                            else if (separated[k].contains("presentationName")) {
                                presentationName = separated[k].split("=")[1];
                                if (!presentationName.endsWith("\"") && !presentationName.endsWith(">")) {
                                    flag = true;
                                    int l = k;
                                    while (flag){
                                        l++;
                                        presentationName = presentationName + " " + separated[l];
                                        if (separated[l].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                presentationName = presentationName.split("\"")[1];
                            }
                            else if (separated[k].contains("briefDescription")) {
                                description = separated[k].split("=")[1];
                                if (!description.endsWith("\"") && !description.endsWith(">")) {
                                    flag = true;
                                    int l = k;
                                    while (flag){
                                        l++;
                                        description = description + " " + separated[l];
                                        if (separated[l].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                description = description.split("\"")[1];
                            }
                            else if (separated[k].contains("org.eclipse.epf.uma")) {
                                type = separated[k].split("=")[1].split("\"")[1].split(":")[1];
                            }
                            else if(separated[k].contains("templates")){
                                templatesLine = separated[k].split("=")[1];
                                if (!templatesLine.endsWith("\"") && !templatesLine.endsWith(">")) {
                                    flag = true;
                                    int l = k;
                                    while (flag){
                                        l++;
                                        templatesLine = templatesLine + " " + separated[l];
                                        if (separated[l].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                templatesLine = templatesLine.split("\"")[1];
                                for (int l = 0; l < templatesLine.split(" ").length; l++) {
                                    for (int m = 0; m < Templates.size(); m++) {
                                        if (templatesLine.split(" ")[l].equals(Templates.get(m).getId())) {
                                            templates.add(Templates.get(m));
                                        }
                                    }
                                }
                            }
                        }
                        WorkProductElement wp = new WorkProductElement(id, name, presentationName, description, type, templates);
                        workproducts.add(wp);
                    }
                }
            }
        }
        return workproducts;
    }
    
    private static ArrayList<TaskElement> searchTasks() throws IOException {
        ArrayList<TaskElement> tasks = new ArrayList<TaskElement>();
        ArrayList<String> PluginFile = new ArrayList<String>();
        PluginFile = XMIRead(pathPlugin);
        boolean flag;
        for (int i = 0; i < PluginFile.size(); i++) {
            String[] separated = PluginFile.get(i).split(" ");
            if (separated[0].contains("contentElements")) {
                for (int j = 0; j < separated.length; j++) {
                    if (separated[j].contains("org.eclipse.epf.uma:Task")) {
                        String id = new String();
                        String name = new String();
                        String presentationName = new String();
                        String description = new String();
                        String producersLine = new String();
                        String collaboratorsLine = new String();
                        String inputsLine = new String();
                        String outputsLine = new String();
                        ArrayList<RoleElement> producers = new ArrayList<RoleElement>();
                        ArrayList<RoleElement> collaborators = new ArrayList<RoleElement>();
                        ArrayList<WorkProductElement> inputs = new ArrayList<WorkProductElement>();
                        ArrayList<WorkProductElement> outputs = new ArrayList<WorkProductElement>();
                        for (int k = 0; k < separated.length; k++) {
                            if (separated[k].contains("xmi:id")) {
                                id = separated[k].split("=")[1].split("\"")[1];
                            }
                            else if (separated[k].contains("name")) {
                                name = separated[k].split("=")[1].split("\"")[1];
                            }
                            else if (separated[k].contains("presentationName")) {
                                presentationName = separated[k].split("=")[1];
                                if (!presentationName.endsWith("\"") && !presentationName.endsWith(">")) {
                                    flag = true;
                                    int l = k;
                                    while (flag){
                                        l++;
                                        presentationName = presentationName + " " + separated[l];
                                        if (separated[l].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                presentationName = presentationName.split("\"")[1];
                            }
                            else if (separated[k].contains("briefDescription")) {
                                description = separated[k].split("=")[1];
                                if (!description.endsWith("\"") && !description.endsWith(">")) {
                                    flag = true;
                                    int l = k;
                                    while (flag){
                                        l++;
                                        description = description + " " + separated[l];
                                        if (separated[l].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                description = description.split("\"")[1];
                            }
                            else if (separated[k].split("=")[0].equals("performedBy")) {
                                producersLine = separated[k].split("=")[1];
                                if (!producersLine.endsWith("\"") && !producersLine.endsWith(">")) {
                                    flag = true;
                                    int l = k;
                                    while (flag){
                                        l++;
                                        producersLine = producersLine + " " + separated[l];
                                        if (separated[l].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                producersLine = producersLine.split("\"")[1];
                                for (int l = 0; l < producersLine.split(" ").length; l++) {
                                    for (int m = 0; m < Roles.size(); m++) {
                                        if (producersLine.split(" ")[l].equals(Roles.get(m).getId())) {
                                            producers.add(Roles.get(m));
                                        }
                                    }
                                }
                            }
                            else if (separated[k].split("=")[0].equals("additionallyPerformedBy")) {
                                collaboratorsLine = separated[k].split("=")[1];
                                if (!collaboratorsLine.endsWith("\"") && !collaboratorsLine.endsWith(">")) {
                                    flag = true;
                                    int l = k;
                                    while (flag){
                                        l++;
                                        collaboratorsLine = collaboratorsLine + " " + separated[l];
                                        if (separated[l].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                collaboratorsLine = collaboratorsLine.split("\"")[1];
                                for (int l = 0; l < collaboratorsLine.split(" ").length; l++) {
                                    for (int m = 0; m < Roles.size(); m++) {
                                        if (collaboratorsLine.split(" ")[l].equals(Roles.get(m).getId())) {
                                            collaborators.add(Roles.get(m));
                                        }
                                    }
                                }
                            }
                            else if (separated[k].split("=")[0].equals("mandatoryInput")) {
                                inputsLine = separated[k].split("=")[1];
                                if (!inputsLine.endsWith("\"") && !inputsLine.endsWith(">")) {
                                    flag = true;
                                    int l = k;
                                    while (flag){
                                        l++;
                                        inputsLine = inputsLine + " " + separated[l];
                                        if (separated[l].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                inputsLine = inputsLine.split("\"")[1];
                                for (int l = 0; l < inputsLine.split(" ").length; l++) {
                                    for (int m = 0; m < WorkProducts.size(); m++) {
                                        if (inputsLine.split(" ")[l].equals(WorkProducts.get(m).getId())) {
                                            inputs.add(WorkProducts.get(m));
                                        }
                                    }
                                }
                            }
                            else if (separated[k].split("=")[0].equals("output")) {
                                outputsLine = separated[k].split("=")[1];
                                if (!outputsLine.endsWith("\"") && !outputsLine.endsWith(">")) {
                                    flag = true;
                                    int l = k;
                                    while (flag){
                                        l++;
                                        outputsLine = outputsLine + " " + separated[l];
                                        if (separated[l].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                outputsLine = outputsLine.split("\"")[1];
                                for (int l = 0; l < outputsLine.split(" ").length; l++) {
                                    for (int m = 0; m < WorkProducts.size(); m++) {
                                        if (outputsLine.split(" ")[l].equals(WorkProducts.get(m).getId())) {
                                            outputs.add(WorkProducts.get(m));
                                        }
                                    }
                                }
                            }
                        }
                        TaskElement t = new TaskElement(id, name, presentationName, description, producers, collaborators, inputs, outputs);
                        tasks.add(t);
                    }
                }
            }
        }
        return tasks;
    }
    
    private static ArrayList<StepElement> searchSteps() throws IOException{
        ArrayList<StepElement> steps = new ArrayList<StepElement>();
        boolean flag;
        for (int i = 0; i < pathTasks.size(); i++) {
            String id = new String();
            String name = new String();
            String nameTask = pathTasks.get(i).substring(mainFolder.length() + 7).substring(0, pathTasks.get(i).substring(mainFolder.length() + 7).length() - 4);
            ArrayList<StepElement> stepsTask = new ArrayList<StepElement>();
            ArrayList<String> TaskFile = new ArrayList<String>();
            TaskFile = XMIRead(pathTasks.get(i));            
            for (int j = 0; j < TaskFile.size(); j++) {
                String[] separated = TaskFile.get(j).split(" ");
                if (separated[0].contentEquals("<sections")) {
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
                    StepElement s = new StepElement(id, name);
                    stepsTask.add(s);
                    steps.add(s);
                }
            }
            for (TaskElement task : Tasks) {
                if (task.getName().endsWith(nameTask)) {
                    task.setSteps(stepsTask);
                }
            }
        }
        return steps;
    }
    
    private static ArrayList<ActivityElement> searchActivities() throws IOException{ 
        ArrayList<ActivityElement> activities = new ArrayList<ActivityElement>();
        boolean flag;
        ArrayList<TaskElement> tasksActivity = new ArrayList<TaskElement>();
        String id = new String ();
        String name = new String ();    
        for (int i = 0; i < pathModels.size(); i++) {
            ArrayList<String> ModelFile = new ArrayList<String>();
            ModelFile = XMIRead(pathModels.get(i));
            for (int j = 0; j < ModelFile.size(); j++) {
                String[] separated = ModelFile.get(j).split(" ");
                if (separated[0].equals("<childPackages")) {
                    tasksActivity = new ArrayList<TaskElement>();
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
                else if(separated[0].equals("<processElements")){
                    if (separated[1].equals("xsi:type=\"org.eclipse.epf.uma:TaskDescriptor\"")) {
                        String nameTask = new String();
                        for (int k = 0; k < separated.length; k++) {
                            if (separated[k].contains("name")) {
                                nameTask = separated[k].split("=")[1];
                                if (!nameTask.endsWith("\"") && !nameTask.endsWith(">")) {
                                    flag = true;
                                    int l = k;
                                    while (flag){
                                        l++;
                                        nameTask = nameTask + " " + separated[l];
                                        if (separated[l].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                nameTask = nameTask.split("\"")[1];
                            }
                        }
                        for (TaskElement task : Tasks) {
                            if (task.getName().equals(nameTask)) {
                                tasksActivity.add(task);
                            }
                        }
                    }
                }
                else if (separated[0].equals("</childPackages>")) {
                    ActivityElement a = new ActivityElement(id, name, tasksActivity);
                    activities.add(a);
                }
            }
        }
        return activities;
    }
    
    private static ArrayList<ProcessElement> searchProcesses() throws IOException{
        ArrayList<ProcessElement> processes = new ArrayList<ProcessElement>();
        boolean flag;
        for (int i = 0; i < pathModels.size(); i++) {
            ArrayList<String> ModelFile = new ArrayList<String>();
            ModelFile = XMIRead(pathModels.get(i));
            String id = new String ();
            String name = new String ();
            ArrayList<ActivityElement> activityProcess = new ArrayList<ActivityElement>();
            for (int j = 0; j < ModelFile.size(); j++) {
                String[] separated = ModelFile.get(j).split(" ");
                if (separated[0].equals("<org.eclipse.epf.uma:ProcessComponent")) {    
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
                else if (separated[0].equals("<childPackages")) {
                    String nameActivity = new String();
                    for (int k = 0; k < separated.length; k++) {
                        if (separated[k].contains("name")) {
                            nameActivity = separated[k].split("=")[1];
                            if (!nameActivity.endsWith("\"") && !nameActivity.endsWith(">")) {
                                flag = true;
                                int l = k;
                                while (flag){
                                    l++;
                                    nameActivity = nameActivity + " " + separated[l];
                                    if (separated[l].contains("\"")) {
                                        flag = false;
                                    }
                                }
                            }
                            nameActivity = nameActivity.split("\"")[1];
                        }
                    }
                    for (ActivityElement activity : Activities) {
                        if (activity.getName().equals(nameActivity)) {
                            activityProcess.add(activity);
                        }
                    }
                }
            }
            ProcessElement p = new ProcessElement(id, name, activityProcess);
            processes.add(p);
        }
        return processes;
    }
    
    private static ArrayList<String> XMIRead(String path) throws FileNotFoundException, IOException {
        File archive = null;
        FileReader fr = null;
        BufferedReader br = null;
        ArrayList<String> File = new ArrayList<String>();
        try {
            archive = new File (path);
            fr = new FileReader (archive);
            br = new BufferedReader(fr);

            String line;
            while((line=br.readLine())!=null){
                File.add(cleanLine(line));
            }
        }
        catch(Exception e){
            System.out.println("ERROR: "+e.getMessage());
        }finally{
            try{
                if( null != fr ){   
                    fr.close();     
               }                  
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return File;
    }
    
    private static String cleanLine(String line){

        String[] separate = line.split(" ");
        String clean = "";
        
        for (int i = 0; i < separate.length; i++) {
            if (separate[i].length() > 0) {
                if ( i < (separate.length - 1)){
                    clean = clean + separate[i] + " ";
                }else{
                    clean = clean + separate[i];
                }
            }
        }
        return clean;
    }
}