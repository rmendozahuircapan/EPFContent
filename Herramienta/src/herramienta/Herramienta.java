package herramienta;

import java.io.*;
import java.util.*;


public class Herramienta {
    
    static ArrayList<String> File = new ArrayList<String>();
    static ArrayList<Role> Roles = new ArrayList<Role>();
    static ArrayList<Template> Templates = new ArrayList<Template>();
    static ArrayList<WorkProduct> WorkProducts = new ArrayList<WorkProduct>();
    static ArrayList<Task> Tasks = new ArrayList<Task>();
    
    
    public static void main(String[] args) throws IOException {
        //String ruta_archivo = "C:\\Users\\Rodrigo\\Desktop\\Library\\Formalización Proceso\\plugin.xmi";
        String path = "C:\\Users\\Rodrigo\\Desktop\\Proceso\\proceso_de_prueba\\plugin.xmi";
        XMI(path);
        searchRoles(File);
        searchTemplates(File);
        System.out.println("-------------------------------------");
        System.out.println("Roles: "+Roles.size());
        System.out.println("Templates: "+Templates.size());
        System.out.println("WorkProducts: "+WorkProducts.size());
        System.out.println("Tasks: "+Tasks.size());
        System.out.println("-------------------------------------");
        for (int i = 0; i < WorkProducts.size(); i++) {
            System.out.println(WorkProducts.get(i).getPresentationName());
        }
        
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
    
    public static void searchRoles(ArrayList<String> File) {
        
        String id = "";
        String name = "";
        String presentationName = "";
        String description = "";
        
        boolean flag;
        
        for (int i = 0; i < File.size(); i++) {
            String[] separated = File.get(i).split(" ");
            if (separated[0].contains("contentElements")) {
                for (int j = 0; j < separated.length; j++) {
                    if (separated[j].contains("org.eclipse.epf.uma:Role")) {
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
    
    public static void searchTemplates(ArrayList<String> File){
        String name = "";
        String presentationName = "";
        String description = "";
        String id = "";
        
        boolean flag;
        
        for (int i = 0; i < File.size(); i++) {
            String[] separated = File.get(i).split(" ");
            if (separated[0].contains("contentElements")) {
                for (int j = 0; j < separated.length; j++) {
                    if (separated[j].contains("org.eclipse.epf.uma:Template")) {
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
    
    public static void Informacion(String linea){// (Cambiar nombre fx) Se recupera la información de roles, templates y artefactos
        String[] separated = linea.split(" ");

        String name = "";
        String presentationName = "";
        String description = "";
        String id = "";
        String id_template = "";
        String producerLine;
        String collaboratorLine;
        String inputLine;
        String outputLine;
        
        ArrayList<String> producers = new ArrayList<String>();
        ArrayList<String> collaborators = new ArrayList<String>();
        ArrayList<String> inputs = new ArrayList<String>();
        ArrayList<String> outputs = new ArrayList<String>();
        
        
        boolean flag;

        
        if (separated[0].contains("contentElements")) {
            for (int i = 0; i < separated.length; i++) {
                if (separated[i].contains("org.eclipse.epf.uma")){
/****************************************            TEMPLATE               ****************************************/                    
                    if(separated[i].contains("Template")){   //Se recupera la informacion de los templates
                        for (int j = 0; j < separated.length; j++) {
                            if (separated[j].contains("xmi:id")) {
                                id = separated[j].split("=")[1].split("\"")[1];
                            }
                            else if (separated[j].contains("name")) {
                                name = separated[j].split("=")[1].split("\"")[1];
                            }
                            else if (separated[j].contains("presentationName")) {
                                presentationName = separated[j].split("=")[1];
                                if (!presentationName.endsWith("\"") && !presentationName.endsWith(">")) {
                                    flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        presentationName = presentationName + " " + separated[k];
                                        if (separated[k].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                presentationName = presentationName.split("\"")[1];
                            }
                            else if (separated[j].contains("briefDescription")) {
                                description = separated[j].split("=")[1];
                                if (!description.endsWith("\"") && !description.endsWith(">")) {
                                    flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        description = description + " " + separated[k];
                                        if (separated[k].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                description = description.split("\"")[1];
                            }
                        }
                        //Template t = new Template(name, presentationName, id, description);
                        //Templates.add(t);
                    }
/****************************************            ARTIFACT               ****************************************/
                    else if(separated[i].contains("Artifact")){ //Falta buscar e identificar cada uno de los templates asociados
                        for (int j = 0; j < separated.length; j++) {
                            if (separated[j].contains("xmi:id")) {
                                id = separated[j].split("=")[1].split("\"")[1];
                            }
                            else if (separated[j].contains("name")) {
                                name = separated[j].split("=")[1].split("\"")[1];
                            }
                            else if (separated[j].contains("presentationName")) {
                                presentationName = separated[j].split("=")[1];
                                if (!presentationName.endsWith("\"") && !presentationName.endsWith(">")) {
                                    flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        presentationName = presentationName + " " + separated[k];
                                        if (separated[k].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                presentationName = presentationName.split("\"")[1];
                            }
                            else if (separated[j].contains("briefDescription")) {
                                description = separated[j].split("=")[1];
                                if (!description.endsWith("\"") && !description.endsWith(">")) {
                                    flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        description = description + " " + separated[k];
                                        if (separated[k].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                description = description.split("\"")[1];
                            }
                            else if(separated[j].contains("templates")){
                                id_template = separated[j].split("=")[1].split("\"")[1];
                            }
                        }
                        //Artifact a = new Artifact(name, presentationName, description, id, id_template);
                        //Artifacts.add(a);
                    }
/****************************************            TASK               ****************************************/                    
                    else if(separated[i].contains("Task")){ //Falta buscar e identificar cada uno de los tareas asociados
                        for (int j = 0; j < separated.length; j++) {
                            if (separated[j].contains("xmi:id")) {
                                id = separated[j].split("=")[1].split("\"")[1];
                            }
                            else if (separated[j].contains("name")) {
                                name = separated[j].split("=")[1].split("\"")[1];
                            }
                            else if (separated[j].contains("presentationName")) {
                                presentationName = separated[j].split("=")[1];
                                if (!presentationName.endsWith("\"") && !presentationName.endsWith(">") ) {
                                    flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        presentationName = presentationName + " " + separated[k];
                                        if (separated[k].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                presentationName = presentationName.split("\"")[1];
                            }
                            else if (separated[j].contains("briefDescription")) {
                                description = separated[j].split("=")[1];
                                if (!description.endsWith("\"") && !description.endsWith(">")) {
                                    flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        description = description + " " + separated[k];
                                        if (separated[k].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                description = description.split("\"")[1];
                            }
                            else if (separated[j].split("=")[0].equals("performedBy")){
                                producerLine = separated[j].split("=")[1];
                                if (!producerLine.endsWith("\"") && !producerLine.endsWith(">")) {
                                    flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        producerLine = producerLine + " " + separated[k];
                                        if (separated[k].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                producerLine = producerLine.split("\"")[1];
                                for (int l = 0; l < producerLine.split(" ").length; l++) {
                                    producers.add(producerLine.split(" ")[l]);
                                }
                            }
                            else if (separated[j].split("=")[0].equals("additionallyPerformedBy")){
                                collaboratorLine = separated[j].split("=")[1];
                                if (!collaboratorLine.endsWith("\"") && !collaboratorLine.endsWith(">")) {
                                    flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        collaboratorLine = collaboratorLine + " " + separated[k];
                                        if (separated[k].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                collaboratorLine = collaboratorLine.split("\"")[1];
                                for (int l = 0; l < collaboratorLine.split(" ").length; l++) {
                                    collaborators.add(collaboratorLine.split(" ")[l]);
                                }
                            }
                            
                            else if (separated[j].split("=")[0].equals("mandatoryInput")){
                                inputLine = separated[j].split("=")[1];
                                if (!inputLine.endsWith("\"") && !inputLine.endsWith(">")) {
                                    flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        inputLine = inputLine + " " + separated[k];
                                        if (separated[k].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                inputLine = inputLine.split("\"")[1];
                                for (int l = 0; l < inputLine.split(" ").length; l++) {
                                    inputs.add(inputLine.split(" ")[l]);
                                }
                            }
                            
                            else if (separated[j].split("=")[0].equals("output")){
                                outputLine = separated[j].split("=")[1];
                                if (!outputLine.endsWith("\"") && !outputLine.endsWith(">")) {
                                    flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        outputLine = outputLine + " " + separated[k];
                                        if (separated[k].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                outputLine = outputLine.split("\"")[1];
                                for (int l = 0; l < outputLine.split(" ").length; l++) {
                                    outputs.add(outputLine.split(" ")[l]);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
        
    public static void XMI(String path) throws FileNotFoundException, IOException {
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
                File.add(cleanLine(line));
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
