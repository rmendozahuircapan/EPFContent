package Herramienta;



import ProcessElements.*;
import WorkFlow.*;
import static Herramienta.ShowProcessElements.*;
import static Herramienta.ShowWorkFlow.*;
import static Herramienta.SearchProcessElements.*;
import static Herramienta.SearchWorkFlow.*;

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
    static ArrayList<Activity> Activities = new ArrayList<Activity>();
    static ArrayList<Processes> Processes = new ArrayList<Processes>();
    
    static ArrayList<Node> Nodes = new ArrayList<Node>();
    static ArrayList<Edge> Edges = new ArrayList<Edge>();
    static ArrayList<WorkFlow> WorkFlows = new ArrayList<WorkFlow>();
    
    static String mainFolder = "C:\\Users\\Rodrigo\\Desktop\\Library\\Formalización Proceso";
    //static String mainFolder = "C:\\Users\\Rodrigo\\Desktop\\Library3\\workflow";
    //static String mainFolder = "C:\\Users\\Rodrigo\\Desktop\\Proceso\\proceso_de_prueba";
    
    static String pathPlugin = mainFolder + "\\plugin.xmi";
    
    static ArrayList<String> pathTasks = new ArrayList<String>();
    static ArrayList<String> pathModels = new ArrayList<String>();
    static ArrayList<String> pathDiagrams = new ArrayList<String>();
    
    static String typePathPlugin = "Plugin";
    static String typePathTask = "Task";
    static String typePathModel = "Model";
    static String typePathDiagram = "Diagram";
    
    /*************************************************************************************************************/
    
    public static void main(String[] args) throws IOException {
        
        searchProcessElements();
        searchElementsWorkFlow();
        resumeProcessElements();
        resumeWorkFlow();
        showWorkFlow();
        Draw.main(args);
        
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
                if (typePath.equals(typePathPlugin)) {
                    PluginFile.add(cleanLine(line));
                }
                else if (typePath.equals(typePathTask)) {
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
