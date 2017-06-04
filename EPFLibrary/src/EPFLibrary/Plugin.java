
package EPFLibrary;

import java.io.*;
import java.util.*;

/**
 * It corresponds a plugin framed in the elements of a software process.
 * @author Rodrigo
 */
public class Plugin {
    
    private String name;
    private MethodContent MethodContent;
    private Processes Processes;
    
    private ArrayList<String> pathsTasks = new ArrayList<>();
    private ArrayList<String> pathsDiagrams = new ArrayList<>();
    private ArrayList<String> pathsModels = new ArrayList<>();
    
    private String pathPlugin = new String();
    private String pathFolder = new String();

    /**
     *
     * @param path it is the path of the archive xmi of plugin
     * @throws IOException
     */
    public Plugin(String path) throws IOException {
        
        setPaths(path);
        searchResourceDescriptors();
        
        ArrayList<GuidanceElement> Guidances = searchGuidances();
        ArrayList<RoleElement> Roles = searchRoles();
        ArrayList<TemplateElement> Templates = searchTemplates(Guidances);
        ArrayList<WorkProductElement> WorkProducts = searchWorkProducts(Templates);
        ArrayList<TaskElement> Tasks = searchTasks(Roles, WorkProducts);
        
        searchSteps(Tasks);
        
        ArrayList<Node> Nodes = searchNodes();
        ArrayList<Edge> Edges = searchEdges(Nodes);
        ArrayList<Position> Positions = searchPositions();
        ArrayList<WorkFlow> Workflows = searchWorkFlows(Nodes, Edges, Positions);
        ArrayList<ActivityElement> Activities = searchActivities(Tasks, Workflows);
        ArrayList<ProcessElement> Process = searchProcesses(Activities, Workflows);
        
        DeliveryProcess DeliveryProcess = new DeliveryProcess(Process);
        
        this.name = path.replace("\\", "%").split("%")[path.replace("\\", "%").split("%").length - 2];
        this.MethodContent = new MethodContent(Guidances, Roles, Tasks, WorkProducts);
        this.Processes = new Processes(DeliveryProcess);
    }

    /**
     *
     * @return returns the name of plugin
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return returns all method content of plugin
     */
    public MethodContent getMethodContent() {
        return MethodContent;
    }

    /**
     *
     * @return returns the processes of plugin
     */
    public Processes getProcesses() {
        return Processes;
    }
    
    private void setPaths(String path) {
        this.pathPlugin = path;
        this.pathFolder = path.substring(0, path.length() - 11);
    }
    
/********************************************************************************************************/
/********************************************************************************************************/
    
    private void searchResourceDescriptors() throws IOException{
        
        ArrayList<String> PluginFile = XMIRead(pathPlugin);
        for (int i = 0; i < PluginFile.size(); i++) {
            String[] separated = PluginFile.get(i).split(" ");
            if (separated[0].contains("resourceDescriptors")) {
                for (int j = 0; j < separated.length; j++) {
                    if (separated[j].contains("uri")) {
                        String uri = separated[j].split("=")[1].split("/>")[0].split("\"")[1];
                        String uriType = uri.split("/")[0];
                        String path = pathFolder;
                        if (uriType.equals("tasks")) {
                            for (int k = 0; k < uri.split("/").length; k++) {
                                String part = uri.split("/")[k].replace("%20", " ");
                                path = path + "\\" + part;
                            }
                            pathsTasks.add(path);
                        }
                        else if (uriType.equals("deliveryprocesses")) {
                            for (int k = 0; k < uri.split("/").length; k++) {
                                String part = uri.split("/")[k].replace("%20", " ");
                                path = path + "\\" + part;
                            }
                            pathsModels.add(path);
                            File file = new File(path.replace("model.xmi", "diagram.xmi"));
                            if (file.exists()) {
                                pathsDiagrams.add(path.replace("model.xmi", "diagram.xmi"));
                            }
                        }
                    }
                }
            }
        }
    }
    
/********************************************************************************************************/    
    
    private ArrayList<RoleElement> searchRoles() throws IOException {
        
        ArrayList<RoleElement> roles = new ArrayList<>();
        ArrayList<String> PluginFile = new ArrayList<>();
        PluginFile = XMIRead(pathPlugin);
        boolean flag;
        for (int i = 0; i < PluginFile.size(); i++) {
            String[] separated = PluginFile.get(i).split(" ");
            if (separated[0].contains("contentElements")) {
                for (int j = 0; j < separated.length; j++) {
                    if (separated[j].contains("org.eclipse.epf.uma:Role")) {
                        String id = new String();
                        String nameRole = new String();
                        String presentationName = new String();
                        String description = new String();
                        for (int k = 0; k < separated.length; k++) {
                            if (separated[k].contains("xmi:id")) {
                                id = separated[k].split("=")[1].split("\"")[1];
                            }
                            else if (separated[k].contains("name")) {
                                nameRole = separated[k].split("=")[1].split("\"")[1];
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
                        RoleElement r = new RoleElement(id, nameRole, presentationName, description);
                        roles.add(r);
                    }
                }
            }
        }
        return roles;
    }
    
    private ArrayList<GuidanceElement> searchGuidances() throws IOException{
        
        ArrayList<GuidanceElement> guidances = new ArrayList<>();
        ArrayList<String> PluginFile = XMIRead(pathPlugin);
        boolean flag;
        for (int i = 0; i < PluginFile.size(); i++) {
            String[] separated = PluginFile.get(i).split(" ");
            if (separated[0].contains("contentElements")) {
                for (int j = 0; j < separated.length; j++) {
                    if ( separated[j].contains("org.eclipse.epf.uma:Checklist") ||
                         separated[j].contains("org.eclipse.epf.uma:Concept") ||
                         separated[j].contains("org.eclipse.epf.uma:Example") ||
                         separated[j].contains("org.eclipse.epf.uma:Guideline") ||
                         separated[j].contains("org.eclipse.epf.uma:EstimationConsiderations") ||
                         separated[j].contains("org.eclipse.epf.uma:Practice") ||
                         separated[j].contains("org.eclipse.epf.uma:Report") ||
                         separated[j].contains("org.eclipse.epf.uma:ReusableAsset") ||
                         separated[j].contains("org.eclipse.epf.uma:Roadmap") ||
                         separated[j].contains("org.eclipse.epf.uma:SupportingMaterial") ||
                         separated[j].contains("org.eclipse.epf.uma:Template") ||
                         separated[j].contains("org.eclipse.epf.uma:TermDefinition") ||
                         separated[j].contains("org.eclipse.epf.uma:ToolMentor") ||
                         separated[j].contains("org.eclipse.epf.uma:Whitepaper") ) {
                        String type = new String();
                        String id = new String();
                        String nameGuidance = new String();
                        String presentationName = new String();
                        String description = new String();
                        for (int k = 0; k < separated.length; k++) {
                            if (separated[k].contains("xsi:type")) {
                                type = separated[k].split("=")[1].split("\"")[1].split(":")[1];
                            }
                            else if (separated[k].contains("xmi:id")) {
                                id = separated[k].split("=")[1].split("\"")[1];
                            }
                            else if (separated[k].contains("name")) {
                                nameGuidance = separated[k].split("=")[1].split("\"")[1];
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
                        if (type.equals("Template")) {
                            TemplateElement t = new TemplateElement(id, nameGuidance, presentationName, description);
                            guidances.add(t);
                        }
                    }
                }
            }
        }
        return guidances;
    }
    
    private ArrayList<WorkProductElement> searchWorkProducts(ArrayList<TemplateElement> Templates) throws IOException {
        
        ArrayList<WorkProductElement> workproducts = new ArrayList<>();
        ArrayList<String> PluginFile = XMIRead(pathPlugin);
        boolean flag;
        for (int i = 0; i < PluginFile.size(); i++) {
            String[] separated = PluginFile.get(i).split(" ");
            if (separated[0].contains("contentElements")) {
                for (int j = 0; j < separated.length; j++) {
                    if (separated[j].contains("org.eclipse.epf.uma:Artifact") || separated[j].contains("org.eclipse.epf.uma:Deliverable") || separated[j].contains("org.eclipse.epf.uma:Outcome")) {
                        String id = new String();
                        String nameWorkProduct = new String();
                        String presentationName = new String();
                        String description = new String();
                        String type = new String();
                        String templatesLine = new String();
                        ArrayList<TemplateElement> templates = new ArrayList<>();
                        for (int k = 0; k < separated.length; k++) {
                            if (separated[k].contains("xmi:id")) {
                                id = separated[k].split("=")[1].split("\"")[1];
                            }
                            else if (separated[k].contains("name")) {
                                nameWorkProduct = separated[k].split("=")[1].split("\"")[1];
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
                        WorkProductElement wp = new WorkProductElement(id, nameWorkProduct, presentationName, description, type, templates);
                        workproducts.add(wp);
                    }
                }
            }
        }
        return workproducts;
    }
    
    private ArrayList<TaskElement> searchTasks(ArrayList<RoleElement> Roles, ArrayList<WorkProductElement> WorkProducts) throws IOException {
        
        ArrayList<TaskElement> tasks = new ArrayList<>();
        ArrayList<String> PluginFile = new ArrayList<>();
        PluginFile = XMIRead(pathPlugin);
        boolean flag;
        for (int i = 0; i < PluginFile.size(); i++) {
            String[] separated = PluginFile.get(i).split(" ");
            if (separated[0].contains("contentElements")) {
                for (int j = 0; j < separated.length; j++) {
                    if (separated[j].contains("org.eclipse.epf.uma:Task")) {
                        String id = new String();
                        String nameTask = new String();
                        String presentationName = new String();
                        String description = new String();
                        String producersLine = new String();
                        String collaboratorsLine = new String();
                        String inputsLine = new String();
                        String outputsLine = new String();
                        ArrayList<RoleElement> producers = new ArrayList<>();
                        ArrayList<RoleElement> collaborators = new ArrayList<>();
                        ArrayList<WorkProductElement> inputs = new ArrayList<>();
                        ArrayList<WorkProductElement> outputs = new ArrayList<>();
                        for (int k = 0; k < separated.length; k++) {
                            if (separated[k].contains("xmi:id")) {
                                id = separated[k].split("=")[1].split("\"")[1];
                            }
                            else if (separated[k].contains("name")) {
                                nameTask = separated[k].split("=")[1].split("\"")[1];
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
                        TaskElement t = new TaskElement(id, nameTask, presentationName, description, producers, collaborators, inputs, outputs);
                        tasks.add(t);
                    }
                }
            }
        }
        return tasks;
    }
    
    private void searchSteps(ArrayList<TaskElement> Tasks) throws IOException{
        
        boolean flag;
        for (int i = 0; i < pathsTasks.size(); i++) {
            String id = new String();
            String nameStep = new String();
            String nameTask = pathsTasks.get(i).substring(pathFolder.length() + 7).substring(0, pathsTasks.get(i).substring(pathFolder.length() + 7).length() - 4);
            ArrayList<StepElement> stepsTask = new ArrayList<>();
            ArrayList<String> TaskFile = XMIRead(pathsTasks.get(i));            
            for (int j = 0; j < TaskFile.size(); j++) {
                String[] separated = TaskFile.get(j).split(" ");
                if (separated[0].contentEquals("<sections")) {
                    for (int k = 0; k < separated.length; k++) {
                        if (separated[k].contains("xmi:id")) {
                            id = separated[k].split("=")[1].split("\"")[1];
                        }
                        else if (separated[k].contains("name")) {
                            nameStep = separated[k].split("=")[1];
                            if (!nameStep.endsWith("\"") && !nameStep.endsWith(">")) {
                                flag = true;
                                int l = k;
                                while (flag){
                                    l++;
                                    nameStep = nameStep + " " + separated[l];
                                    if (separated[l].contains("\"")) {
                                        flag = false;
                                    }
                                }
                            }
                            nameStep = nameStep.split("\"")[1];
                        }
                    }
                    StepElement s = new StepElement(id, nameStep);
                    stepsTask.add(s);
                }
            }
            for (TaskElement task : Tasks) {
                if (task.getName().endsWith(nameTask)) {
                    task.setSteps(stepsTask);
                }
            }
        }
    }
    
    private ArrayList<TemplateElement> searchTemplates(ArrayList<GuidanceElement> Guidances) throws IOException{
        
        ArrayList<TemplateElement> templates = new ArrayList<>();
        for (GuidanceElement g : Guidances) {
            if (g.getClass().getSimpleName().equals("TemplateElement")) {
                templates.add((TemplateElement) g );
            }
        }
        return templates;
    }
    
/********************************************************************************************************/
    
    private ArrayList<ActivityElement> searchActivities(ArrayList<TaskElement> Tasks, ArrayList<WorkFlow> Workflows) throws IOException{ 
        
        ArrayList<ActivityElement> activities = new ArrayList<>();
        ArrayList<TaskElement> tasksActivity = new ArrayList<>();
        String id = new String ();
        String nameActivity = new String ();    
        boolean flag;
        for (int i = 0; i < pathsModels.size(); i++) {
            ArrayList<String> ModelFile = XMIRead(pathsModels.get(i));
            for (int j = 0; j < ModelFile.size(); j++) {
                String[] separated = ModelFile.get(j).split(" ");
                if (separated[0].equals("<childPackages")) {
                    tasksActivity = new ArrayList<>();
                    for (int k = 0; k < separated.length; k++) {
                        if (separated[k].contains("xmi:id")) {
                            id = separated[k].split("=")[1].split("\"")[1];
                        }
                        else if (separated[k].contains("name")) {
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
                    ActivityElement a = new ActivityElement(id, nameActivity, tasksActivity);
                    activities.add(a);
                }
            }
        }
        for (ActivityElement a : activities) {
            for (WorkFlow wf : Workflows) {
                if (a.getName().equals(wf.getName())) {
                    a.setWorkflow(wf);
                }
            }
        }
        return activities;
    }
    
    private ArrayList<ProcessElement> searchProcesses(ArrayList<ActivityElement> Activities, ArrayList<WorkFlow> Workflows) throws IOException{
        
        ArrayList<ProcessElement> processes = new ArrayList<>();
        boolean flag;
        for (int i = 0; i < pathsModels.size(); i++) {
            ArrayList<String> ModelFile = XMIRead(pathsModels.get(i));
            String id = new String ();
            String nameProcess = new String ();
            ArrayList<ActivityElement> activityProcess = new ArrayList<>();
            for (int j = 0; j < ModelFile.size(); j++) {
                String[] separated = ModelFile.get(j).split(" ");
                if (separated[0].equals("<org.eclipse.epf.uma:ProcessComponent")) {    
                    for (int k = 0; k < separated.length; k++) {
                        if (separated[k].contains("xmi:id")) {
                            id = separated[k].split("=")[1].split("\"")[1];
                        }
                        else if (separated[k].contains("name")) {
                            nameProcess = separated[k].split("=")[1];
                            if (!nameProcess.endsWith("\"") && !nameProcess.endsWith(">")) {
                                flag = true;
                                int l = k;
                                while (flag){
                                    l++;
                                    nameProcess = nameProcess + " " + separated[l];
                                    if (separated[l].contains("\"")) {
                                        flag = false;
                                    }
                                }
                            }
                            nameProcess = nameProcess.split("\"")[1];
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
            ProcessElement p = new ProcessElement(id, nameProcess, activityProcess);
            processes.add(p);
        }
        for (ProcessElement p : processes) {
            for (WorkFlow wf : Workflows) {
                if (p.getName().equals(wf.getName())) {
                    p.setWorkflow(wf);
                }
            }
        }
        return processes;
    }
    
    private ArrayList<Node> searchNodes() throws IOException{
        
        ArrayList<Node> nodes = new ArrayList<>();
        boolean flag;
        for (int i = 0; i < pathsDiagrams.size(); i++) {
            ArrayList<String> DiagramFile = XMIRead(pathsDiagrams.get(i));
            for (int j = 0; j < DiagramFile.size(); j++) {
                String[] separated = DiagramFile.get(j).split(" ");
                if (separated[0].equals("<node")) {
                    String id = new String();
                    String nameNode = new String();
                    String type = new String();
                    for (int k = 0; k < separated.length; k++) {
                        if (separated[k].contains("xmi:type")) {
                            type = separated[k].split("=")[1].split("\"")[1].split(":")[1];
                        }
                        else if (separated[k].contains("xmi:id")) {
                            id = separated[k].split("=")[1].split("\"")[1];
                        }
                        else if (separated[k].contains("name")) {
                            nameNode = separated[k].split("=")[1];
                            if (!nameNode.endsWith("\"") && !nameNode.endsWith(">")) {
                                flag = true;
                                int l = k;
                                while (flag){
                                    l++;
                                    nameNode = nameNode + " " + separated[l];
                                    if (separated[l].contains("\"")) {
                                        flag = false;
                                    }
                                }
                            }
                            nameNode = nameNode.split("\"")[1];
                        }
                    }
                    if (nameNode.isEmpty()) {
                        nameNode = type;
                    }
                    Node n = new Node(id, nameNode, type);
                    nodes.add(n);
                }
            }
        }
        return nodes;
    }
    
    private ArrayList<Edge> searchEdges(ArrayList<Node> Nodes) throws IOException{
        
        ArrayList<Edge> edges = new ArrayList<>();
        boolean flag;
        for (int i = 0; i < pathsDiagrams.size(); i++) {
            ArrayList<String> DiagramFile = XMIRead(pathsDiagrams.get(i));
            for (int j = 0; j < DiagramFile.size(); j++) {
                String[] separated = DiagramFile.get(j).split(" ");
                if (separated[0].equals("<edge")) {
                    String id = new String();
                    String nameEdge = new String();
                    Node source = null;
                    Node target = null;
                    for (int k = 0; k < separated.length; k++) {
                        if (separated[k].contains("xmi:id")) {
                            id = separated[k].split("=")[1].split("\"")[1];
                        }
                        else if (separated[k].contains("name")) {
                            nameEdge = separated[k].split("=")[1];
                            if (!nameEdge.endsWith("\"") && !nameEdge.endsWith(">")) {
                                flag = true;
                                int l = k;
                                while (flag){
                                    l++;
                                    nameEdge = nameEdge + " " + separated[l];
                                    if (separated[l].contains("\"")) {
                                        flag = false;
                                    }
                                }
                            }
                            nameEdge = nameEdge.split("\"")[1];
                        }
                        else if (separated[k].contains("source")) {
                            String idSource = separated[k].split("=")[1].split("\"")[1];
                            for (Node node : Nodes) {
                                if (node.getId().equals(idSource)) {
                                    source = node;
                                }
                            }
                        }
                        else if (separated[k].contains("target")) {
                            String idTarget = separated[k].split("=")[1].split("\"")[1];
                            for (Node node : Nodes) {
                                if (node.getId().equals(idTarget)) {
                                    target = node;
                                }
                            }
                        }
                    }
                    Edge e = new Edge(id, nameEdge, source, target);
                    edges.add(e);
                }
            }
        }
        return edges;
    }
    
    private ArrayList<Position> searchPositions() throws IOException{
        
        ArrayList<Position> positions = new ArrayList<>();
        for (int i = 0; i < pathsDiagrams.size(); i++) {
            ArrayList<String> DiagramFile = XMIRead(pathsDiagrams.get(i));
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
                    positions.add(p);
                    id = new String();
                    x = -1;
                    y = -1;
                }
            }
        }
        return positions;
    }
    
    private ArrayList<WorkFlow> searchWorkFlows(ArrayList<Node> Nodes, ArrayList<Edge> Edges, ArrayList<Position> Positions) throws IOException{
        
        ArrayList<WorkFlow> workflows = new ArrayList<>();
        boolean flag;
        for (int i = 0; i < pathsDiagrams.size(); i++) {
            ArrayList<String> DiagramFile = XMIRead(pathsDiagrams.get(i));
            String id = new String();
            String nameWorkflow = new String();
            ArrayList<Node> nodesWorkFlow = new ArrayList<>();
            ArrayList<Edge> edgesWorkFlow = new ArrayList<>();
            ArrayList<Position> positionsWorkFlow = new ArrayList<>();

            for (int j = 0; j < DiagramFile.size(); j++) {
                String[] separated = DiagramFile.get(j).split(" ");

                if (separated[0].equals("<uml:Activity")) {
                    id = new String();
                    nameWorkflow = new String();
                    nodesWorkFlow = new ArrayList<>();
                    edgesWorkFlow = new ArrayList<>();
                    positionsWorkFlow = new ArrayList<>();
                    
                    for (int k = 0; k < separated.length; k++) {
                        if (separated[k].contains("xmi:id")) {
                            id = separated[k].split("=")[1].split("\"")[1];
                        }
                        else if (separated[k].contains("name")) {
                            nameWorkflow = separated[k].split("=")[1];
                            if (!nameWorkflow.endsWith("\"") && !nameWorkflow.endsWith(">")) {
                                flag = true;
                                int l = k;
                                while (flag){
                                    l++;
                                    nameWorkflow = nameWorkflow + " " + separated[l];
                                    if (separated[l].contains("\"")) {
                                        flag = false;
                                    }
                                }
                            }
                            nameWorkflow = nameWorkflow.split("\"")[1];
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
                    WorkFlow wf = new WorkFlow(id, nameWorkflow, nodesWorkFlow, edgesWorkFlow, positionsWorkFlow);
                    workflows.add(wf);
                }
            }
        }
        return workflows;
    }
    
/********************************************************************************************************/
/********************************************************************************************************/
    
    private ArrayList<String> XMIRead(String path) throws FileNotFoundException, IOException {
        
        File archive = null;
        FileReader fr = null;
        BufferedReader br = null;
        ArrayList<String> File = new ArrayList<>();
        try {
            archive = new File (path);
            fr = new FileReader (archive);
            br = new BufferedReader(fr);

            String line;
            while((line=br.readLine())!=null){
                File.add(cleanLine(line));
            }
        }
        catch(IOException e){
            System.out.println("ERROR: "+e.getMessage());
        }finally{
            try{
                if( null != fr ){   
                    fr.close();     
               }                  
            }catch (IOException e2){
                e2.printStackTrace();
            }
        }
        return File;
    }
    
    private String cleanLine(String line){

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