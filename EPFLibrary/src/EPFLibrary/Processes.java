
package EPFLibrary;

import java.io.*;
import java.util.*;

/**
 * It corresponds to all processes framed in the elements of a software process.
 * @author Rodrigo
 */
public class Processes {
    
    private static DeliveryProcess DeliveryProcess;
    
    private static ArrayList<String> pathDiagrams = new ArrayList<String>();
    private static ArrayList<String> pathModels = new ArrayList<String>();
    
    private static String pathPlugin;
    private static String pathFolder;

    /**
     *
     * @param pathPlugin it is the path of the archive xmi of plugin.
     * @throws IOException
     */
    public Processes(String pathPlugin) throws IOException {
        setPathPlugin(pathPlugin);
        searchResourceDescriptors();
        Processes.DeliveryProcess = new DeliveryProcess(searchProcesses());
    }

    /**
     *
     * @return returns the delivery process.
     */
    public static DeliveryProcess getDeliveryProcess() {
        return DeliveryProcess;
    }
    
    
    protected static void setPathPlugin(String pathPlugin) {
        Processes.pathPlugin = pathPlugin;
        setPathFolder(pathPlugin.substring(0, pathPlugin.length() - 11));
    }
    
    protected static void setPathFolder(String pathFolder) {
        Processes.pathFolder = pathFolder;
    }
    
    private static void searchResourceDescriptors() throws IOException{
        ArrayList<String> PluginFile = XMIRead(pathPlugin);
        for (int i = 0; i < PluginFile.size(); i++) {
            String[] separated = PluginFile.get(i).split(" ");
            if (separated[0].contains("resourceDescriptors")) {
                for (int j = 0; j < separated.length; j++) {
                    if (separated[j].contains("uri")) {
                        String uri = separated[j].split("=")[1].split("/>")[0].split("\"")[1];
                        String uriType = uri.split("/")[0];
                        String path = pathFolder;
                        if (uriType.equals("deliveryprocesses")) {
                            for (int k = 0; k < uri.split("/").length; k++) {
                                String part = uri.split("/")[k].replace("%20", " ");
                                path = path + "\\" + part;
                            }
                            pathModels.add(path);
                            pathDiagrams.add(path.replace("model.xmi", "diagram.xmi"));
                        }
                    }
                }
            }
        }
    }
    
    
    private static ArrayList<ActivityElement> searchActivities() throws IOException{ 
        ArrayList<ActivityElement> activities = new ArrayList<>();
        boolean flag;
        ArrayList<TaskElement> tasksActivity = new ArrayList<>();
        ArrayList<TaskElement> Tasks = MethodContent.searchTasks();
        String id = new String ();
        String name = new String ();    
        for (int i = 0; i < pathModels.size(); i++) {
            ArrayList<String> ModelFile = XMIRead(pathModels.get(i));
            for (int j = 0; j < ModelFile.size(); j++) {
                String[] separated = ModelFile.get(j).split(" ");
                if (separated[0].equals("<childPackages")) {
                    tasksActivity = new ArrayList<>();
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
        ArrayList<WorkFlow> workflows = searchWorkFlows();
        for (ActivityElement a : activities) {
            for (WorkFlow wf : workflows) {
                if (a.getName().equals(wf.getName())) {
                    a.setWorkflow(wf);
                }
            }
        }
        return activities;
    }
    
    private static ArrayList<ProcessElement> searchProcesses() throws IOException{
        ArrayList<ProcessElement> processes = new ArrayList<>();
        ArrayList<ActivityElement> Activities = searchActivities();
        boolean flag;
        for (int i = 0; i < pathModels.size(); i++) {
            ArrayList<String> ModelFile = XMIRead(pathModels.get(i));
            String id = new String ();
            String name = new String ();
            ArrayList<ActivityElement> activityProcess = new ArrayList<>();
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
        ArrayList<WorkFlow> workflows = searchWorkFlows();
        for (ProcessElement p : processes) {
            for (WorkFlow wf : workflows) {
                if (p.getName().equals(wf.getName())) {
                    p.setWorkflow(wf);
                }
            }
        }
        return processes;
    }
    
    private static ArrayList<Node> searchNodes() throws IOException{
        ArrayList<Node> nodes = new ArrayList<>();
        boolean flag;
        for (int i = 0; i < pathDiagrams.size(); i++) {
            ArrayList<String> DiagramFile = XMIRead(pathDiagrams.get(i));
            for (int j = 0; j < DiagramFile.size(); j++) {
                String[] separated = DiagramFile.get(j).split(" ");
                if (separated[0].equals("<node")) {
                    String id = new String();
                    String name = new String();
                    String type = new String();
                    for (int k = 0; k < separated.length; k++) {
                        if (separated[k].contains("xmi:type")) {
                            type = separated[k].split("=")[1].split("\"")[1].split(":")[1];
                        }
                        else if (separated[k].contains("xmi:id")) {
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
                    if (name.isEmpty()) {
                        name = type;
                    }
                    Node n = new Node(id, name, type);
                    nodes.add(n);
                }
            }
        }
        return nodes;
    }
    
    private static ArrayList<Edge> searchEdges() throws IOException{
        ArrayList<Edge> edges = new ArrayList<>();
        ArrayList<Node> Nodes = searchNodes();
        boolean flag;
        for (int i = 0; i < pathDiagrams.size(); i++) {
            ArrayList<String> DiagramFile = XMIRead(pathDiagrams.get(i));
            for (int j = 0; j < DiagramFile.size(); j++) {
                String[] separated = DiagramFile.get(j).split(" ");
                if (separated[0].equals("<edge")) {
                    String id = new String();
                    String name = new String();
                    Node source = null;
                    Node target = null;
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
                    Edge e = new Edge(id, name, source, target);
                    edges.add(e);
                }
            }
        }
        return edges;
    }
    
    private static ArrayList<Position> searchPositions() throws IOException{
        ArrayList<Position> positions = new ArrayList<>();
        for (int i = 0; i < pathDiagrams.size(); i++) {
            ArrayList<String> DiagramFile = XMIRead(pathDiagrams.get(i));
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
    
    private static ArrayList<WorkFlow> searchWorkFlows() throws IOException{
        ArrayList<WorkFlow> workflows = new ArrayList<>();
        ArrayList<Node> Nodes = searchNodes();
        ArrayList<Edge> Edges = searchEdges();
        ArrayList<Position> Positions = searchPositions();
        boolean flag;
        for (int i = 0; i < pathDiagrams.size(); i++) {
            ArrayList<String> DiagramFile = XMIRead(pathDiagrams.get(i));
            String id = new String();
            String name = new String();
            ArrayList<Node> nodesWorkFlow = new ArrayList<>();
            ArrayList<Edge> edgesWorkFlow = new ArrayList<>();
            ArrayList<Position> positionsWorkFlow = new ArrayList<>();

            for (int j = 0; j < DiagramFile.size(); j++) {
                String[] separated = DiagramFile.get(j).split(" ");

                if (separated[0].equals("<uml:Activity")) {
                    id = new String();
                    name = new String();
                    nodesWorkFlow = new ArrayList<>();
                    edgesWorkFlow = new ArrayList<>();
                    positionsWorkFlow = new ArrayList<>();
                    
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
                    WorkFlow wf = new WorkFlow(id, name, nodesWorkFlow, edgesWorkFlow, positionsWorkFlow);
                    workflows.add(wf);
                }
            }
        }
        return workflows;
    }
    
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
