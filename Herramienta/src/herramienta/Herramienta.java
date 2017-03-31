package herramienta;

import java.io.*;
import java.util.*;


public class Herramienta {
    
    static ArrayList<String> PluginFile = new ArrayList<String>();
    static ArrayList<String> TaskFile = new ArrayList<String>();
    static ArrayList<String> ModelFile = new ArrayList<String>();
    static ArrayList<String> DiagramFile = new ArrayList<String>();
    
    static ArrayList<Role> Roles = new ArrayList<Role>();
    static ArrayList<Template> Templates = new ArrayList<Template>();
    static ArrayList<WorkProduct> WorkProducts = new ArrayList<WorkProduct>();
    static ArrayList<Task> Tasks = new ArrayList<Task>();
    static ArrayList<Step> Steps = new ArrayList<Step>();
    
    static String mainFolder = "C:\\Users\\Rodrigo\\Desktop\\Nueva carpeta\\Prueba";
    //static String mainFolder = "C:\\Users\\Rodrigo\\Desktop\\Proceso\\proceso_de_prueba";
    static String pathFile = mainFolder + "\\plugin.xmi";
    static String pathModel = "";
    static String pathDiagram = "";
    static ArrayList<String> pathTasks = new ArrayList<String>();
    static String typePathFile = "File";
    static String typePathTask = "Task";
    static String typePathModel = "Model";
    static String typePathDiagram = "Diagram";
    
    
    
    public static void main(String[] args) throws IOException {
        
        searchInformation();
        resumeInformation();
        showTasks();
        
    }
    
    public static String cleanLine(String line){

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
    
    public static void searchRoles() {
        
        boolean flag;
        
        for (int i = 0; i < PluginFile.size(); i++) {
            String[] separated = PluginFile.get(i).split(" ");
            if (separated[0].contains("contentElements")) {
                for (int j = 0; j < separated.length; j++) {
                    if (separated[j].contains("org.eclipse.epf.uma:Role")) {
                        
                        String id = "";
                        String name = "";
                        String presentationName = "";
                        String description = "";
                        
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
                        Role r = new Role(id, name, presentationName, description);
                        Roles.add(r);
                    }
                }
            }
        }
    }
    
    public static void searchTemplates(){
        
        boolean flag;
        
        for (int i = 0; i < PluginFile.size(); i++) {
            String[] separated = PluginFile.get(i).split(" ");
            if (separated[0].contains("contentElements")) {
                for (int j = 0; j < separated.length; j++) {
                    if (separated[j].contains("org.eclipse.epf.uma:Template")) {
                        
                        String id = "";
                        String name = "";
                        String presentationName = "";
                        String description = "";
                        
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
                        Template t = new Template(id, name, presentationName, description);
                        Templates.add(t);
                    }
                }
            }
        }
    }
    
    public static void searchWorkProducts() {
        
        boolean flag;
        
        for (int i = 0; i < PluginFile.size(); i++) {
            String[] separated = PluginFile.get(i).split(" ");
            if (separated[0].contains("contentElements")) {
                for (int j = 0; j < separated.length; j++) {
                    if (separated[j].contains("org.eclipse.epf.uma:Artifact") || separated[j].contains("org.eclipse.epf.uma:Deliverable") || separated[j].contains("org.eclipse.epf.uma:Outcome")) {
                        
                        String id = "";
                        String name = "";
                        String presentationName = "";
                        String description = "";
                        String type = "";
                        String templatesLine;
                        ArrayList<Template> templates = new ArrayList<Template>();
                        
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
                        WorkProduct wp = new WorkProduct(id, name, presentationName, description, type, templates);
                        WorkProducts.add(wp);
                    }
                }
            }
        }
    }
    
    public static void searchTasks() {
        
        boolean flag;
        
        for (int i = 0; i < PluginFile.size(); i++) {
            String[] separated = PluginFile.get(i).split(" ");
            if (separated[0].contains("contentElements")) {
                for (int j = 0; j < separated.length; j++) {
                    if (separated[j].contains("org.eclipse.epf.uma:Task")) {
                        
                        String id = "";
                        String name = "";
                        String presentationName = "";
                        String description = "";
                        String producersLine;
                        String collaboratorsLine;
                        String inputsLine;
                        String outputsLine;
                        
                        ArrayList<Role> producers = new ArrayList<Role>();
                        ArrayList<Role> collaborators = new ArrayList<Role>();
                        ArrayList<WorkProduct> inputs = new ArrayList<WorkProduct>();
                        ArrayList<WorkProduct> outputs = new ArrayList<WorkProduct>();
                        
                        
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
                        Task t = new Task(id, name, presentationName, description, producers, collaborators, inputs, outputs);
                        Tasks.add(t);
                    }
                }
            }
        }
    }
    
    public static void searchSteps() throws IOException{

        String id = "";
        String name = "";
        String nameTask = "";
        boolean flag;
        ArrayList<Step> stepsTask;

        for (int i = 0; i < pathTasks.size(); i++) {
            
            TaskFile = new ArrayList<String>();
            stepsTask = new ArrayList<Step>();
            String[] separated;
            XMI(pathTasks.get(i), typePathTask);
            nameTask = pathTasks.get(i).substring(mainFolder.length() + 7).substring(0, pathTasks.get(i).substring(mainFolder.length() + 7).length() - 4);
            
            for (int j = 0; j < TaskFile.size(); j++) {
                separated = TaskFile.get(j).split(" ");
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
                    Step s = new Step(id, name);
                    stepsTask.add(s);
                    Steps.add(s);
                }
            }
            for (Task task : Tasks) {
                if (task.getName().endsWith(nameTask)) {
                    task.setSteps(Steps);
                }
            }
        }
    }
    
    public static void searchResourceDescriptors() throws IOException{
        
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
                            pathModel = path;
                            pathDiagram = path.replace("model.xmi", "diagram.xmi");
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
    
    public static void searchInformation() throws IOException {
        
        XMI(pathFile, typePathFile);
        searchResourceDescriptors();
        searchRoles();
        searchTemplates();
        searchWorkProducts();
        searchTasks();
        searchSteps();
        
    }
    
    public static void resumeInformation(){
        
        System.out.println("-------------------------------------");
        System.out.println("Roles: "+Roles.size());
        System.out.println("Templates: "+Templates.size());
        System.out.println("WorkProducts: "+WorkProducts.size());
        System.out.println("Tasks: "+Tasks.size());
        System.out.println("Steps: "+Steps.size());
        System.out.println("-------------------------------------");
    }
    
    public static void showInformation(){
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
         
    public static void XMI(String path, String typePath) throws FileNotFoundException, IOException {
        File archive = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archive = new File (path);
            fr = new FileReader (archive);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String line;
            while((line=br.readLine())!=null){
                if (typePath.equals(typePathFile)) {
                    PluginFile.add(cleanLine(line));
                }
                else if (typePath.equals(typePathTask)) {
                    //System.out.println(line);
                    TaskFile.add(cleanLine(line));
                }
                else if (typePath.equals(typePathModel)){
                    ModelFile.add(cleanLine(line));
                }
                else if (typePath.equals(typePathDiagram)){
                    DiagramFile.add(cleanLine(line));
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try{
                if( null != fr ){   
                    fr.close();     
               }                  
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }

}
