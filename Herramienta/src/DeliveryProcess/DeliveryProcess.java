
package DeliveryProcess;

import java.io.*;
import java.util.*;


public class DeliveryProcess {
    
    private static ArrayList<Edge> Edges;
    private static ArrayList<Node> Nodes;
    private static ArrayList<Position> Positions;
    private static ArrayList<WorkFlow> WorkFlows;
    
    private static ArrayList<String> pathDiagrams = new ArrayList<String>();
    
    private static String pathPlugin;
    private static String mainFolder;

    public DeliveryProcess(String Folder) throws IOException {
        setMainFolder(Folder);
        searchResourceDescriptors();
        DeliveryProcess.Nodes = searchNodes();
        DeliveryProcess.Edges = searchEdges();
        DeliveryProcess.Positions = searchPositions();
        DeliveryProcess.WorkFlows = searchWorkFlows();
    }

    public static ArrayList<Edge> getEdges() {
        return Edges;
    }

    public static ArrayList<Node> getNodes() {
        return Nodes;
    }

    public static ArrayList<Position> getPositions() {
        return Positions;
    }

    public static ArrayList<WorkFlow> getWorkFlows() {
        return WorkFlows;
    }



    public static void setPathPlugin(String pathPlugin) {
        DeliveryProcess.pathPlugin = pathPlugin;
    }

    public static void setMainFolder(String mainFolder) {
        DeliveryProcess.mainFolder = mainFolder;
        setPathPlugin(mainFolder + "\\plugin.xmi");
    }
    
    public static void searchResourceDescriptors() throws IOException{
        ArrayList<String> PluginFile = new ArrayList<String>();
        PluginFile = XMI(pathPlugin);
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
                            pathDiagrams.add(path.replace("model.xmi", "diagram.xmi"));
                        }
                    }
                }
            }
        }
    }
    
    public static ArrayList<Node> searchNodes() throws IOException{
        ArrayList<Node> nodes = new ArrayList<Node>();
        boolean flag;
        for (int i = 0; i < pathDiagrams.size(); i++) {
            ArrayList<String> DiagramFile = new ArrayList<String>();
            DiagramFile = XMI(pathDiagrams.get(i));
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
    
    public static ArrayList<Edge> searchEdges() throws IOException{
        ArrayList<Edge> edges = new ArrayList<Edge>();
        boolean flag;
        for (int i = 0; i < pathDiagrams.size(); i++) {
            ArrayList<String> DiagramFile = new ArrayList<String>();
            DiagramFile = XMI(pathDiagrams.get(i));
            for (int j = 0; j < DiagramFile.size(); j++) {
                String[] separated = DiagramFile.get(j).split(" ");
                if (separated[0].equals("<edge")) {
                    String id = new String();
                    String name = new String();
                    Node source = null;
                    Node target = null;
                    String idSource = new String();
                    String idTarget = new String();
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
                            //String 
                            idSource = separated[k].split("=")[1].split("\"")[1];
                            for (Node node : Nodes) {
                                if (node.getId().equals(idSource)) {
                                    source = node;
                                }
                            }
                        }
                        else if (separated[k].contains("target")) {
                            //String
                            idTarget = separated[k].split("=")[1].split("\"")[1];
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
    
    public static ArrayList<Position> searchPositions() throws IOException{
        ArrayList<Position> positions = new ArrayList<Position>();
        for (int i = 0; i < pathDiagrams.size(); i++) {
            ArrayList<String> DiagramFile = new ArrayList<String>();
            DiagramFile = XMI(pathDiagrams.get(i));
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
    
    public static ArrayList<WorkFlow> searchWorkFlows() throws IOException{
        ArrayList<WorkFlow> workflows = new ArrayList<WorkFlow>();
        boolean flag;
        for (int i = 0; i < pathDiagrams.size(); i++) {
            ArrayList<String> DiagramFile = new ArrayList<String>();
            DiagramFile = XMI(pathDiagrams.get(i));
            String id = new String();
            String name = new String();
            ArrayList<Node> nodesWorkFlow = new ArrayList<Node>();
            ArrayList<Edge> edgesWorkFlow = new ArrayList<Edge>();
            ArrayList<Position> positionsWorkFlow = new ArrayList<Position>();

            for (int j = 0; j < DiagramFile.size(); j++) {
                String[] separated = DiagramFile.get(j).split(" ");

                if (separated[0].equals("<uml:Activity")) {
                    id = new String();
                    name = new String();
                    nodesWorkFlow = new ArrayList<Node>();
                    edgesWorkFlow = new ArrayList<Edge>();
                    positionsWorkFlow = new ArrayList<Position>();
                    
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
    
    public static ArrayList<String> XMI(String path) throws FileNotFoundException, IOException {
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

}
