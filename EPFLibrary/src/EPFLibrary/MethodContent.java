
package EPFLibrary;

import java.io.*;
import java.util.*;

/**
 * Corresponds to all the content of method framed in the elements of a software process.
 * @author Rodrigo
 */
public class MethodContent {
    
    private static ArrayList<GuidanceElement> Guidances;
    private static ArrayList<RoleElement> Roles;
    private static ArrayList<TaskElement> Tasks;
    private static ArrayList<WorkProductElement> WorkProducts;
    
    private static ArrayList<String> pathTasks = new ArrayList<>();
    
    private static String pathPlugin;
    private static String pathFolder;
    
    /**
     *
     * @param pathPlugin it is the path of the archive xmi of plugin
     * @throws IOException
     */
    public MethodContent(String pathPlugin) throws IOException {
        setPathPlugin(pathPlugin);
        searchResourceDescriptors();
        MethodContent.Roles = searchRoles();
        MethodContent.Guidances = searchGuidances();
        MethodContent.WorkProducts = searchWorkProducts();
        MethodContent.Tasks = searchTasks();
        searchSteps();
    }

    /**
     *
     * @return returns all guidances of plugin
     */
    public static ArrayList<GuidanceElement> getGuidances() {
        return Guidances;
    }

    /**
     *
     * @return returns all roles of plugin
     */
    public static ArrayList<RoleElement> getRoles() {
        return Roles;
    }

    /**
     *
     * @return returns all taks of plugin
     */
    public static ArrayList<TaskElement> getTasks() {
        return Tasks;
    }

    /**
     *
     * @return returns all workproducts of plugin
     */
    public static ArrayList<WorkProductElement> getWorkProducts() {
        return WorkProducts;
    }

    private static void setPathPlugin(String pathPlugin) {
        MethodContent.pathPlugin = pathPlugin;
        setPathFolder(pathPlugin.substring(0, pathPlugin.length() - 11));
    }

    private static void setPathFolder(String pathFolder) {
        MethodContent.pathFolder = pathFolder;
    }
    
    private static void searchResourceDescriptors() throws IOException{
        ArrayList<String> PluginFile = new ArrayList<>();
        PluginFile = XMIRead(pathPlugin);
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
                            pathTasks.add(path);
                        }
                    }
                }
            }
        }
    }
    
    private static ArrayList<RoleElement> searchRoles() throws IOException {
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
    
    private static ArrayList<GuidanceElement> searchGuidances() throws IOException{
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
                        String name = new String();
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
                        if (type.equals("Template")) {
                            TemplateElement t = new TemplateElement(id, name, presentationName, description);
                            guidances.add(t);
                        }
                    }
                }
            }
        }
        return guidances;
    }
    
    private static ArrayList<WorkProductElement> searchWorkProducts() throws IOException {
        ArrayList<WorkProductElement> workproducts = new ArrayList<>();
        ArrayList<String> PluginFile = XMIRead(pathPlugin);
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
                        ArrayList<TemplateElement> templates = new ArrayList<>();
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
                                ArrayList<TemplateElement> Templates = searchTemplates();
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
    
    protected static ArrayList<TaskElement> searchTasks() throws IOException {
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
                        String name = new String();
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
    
    private static void searchSteps() throws IOException{
        boolean flag;
        for (int i = 0; i < pathTasks.size(); i++) {
            String id = new String();
            String name = new String();
            String nameTask = pathTasks.get(i).substring(pathFolder.length() + 7).substring(0, pathTasks.get(i).substring(pathFolder.length() + 7).length() - 4);
            ArrayList<StepElement> stepsTask = new ArrayList<>();
            ArrayList<String> TaskFile = XMIRead(pathTasks.get(i));            
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
                }
            }
            for (TaskElement task : Tasks) {
                if (task.getName().endsWith(nameTask)) {
                    task.setSteps(stepsTask);
                }
            }
        }
    }
    
    private static ArrayList<TemplateElement> searchTemplates() throws IOException{
        ArrayList<TemplateElement> templates = new ArrayList<>();
        ArrayList<GuidanceElement> guidances = searchGuidances();
        for (GuidanceElement g : guidances) {
            if (g.getClass().getSimpleName().equals("TemplateElement")) {
                templates.add((TemplateElement) g );
            }
        }
        return templates;
    }
    
    /*******************************************************************************/
    private static ArrayList<String> XMIRead(String path) throws FileNotFoundException, IOException {
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
