package herramienta;

import java.io.*;
import java.util.*;


public class Herramienta {
    
    static ArrayList<String> File = new ArrayList<String>();
    static ArrayList<Rol> Roles = new ArrayList<Rol>();
    static ArrayList<Template> Templates = new ArrayList<Template>();
    static ArrayList<Artifact> Artifacts = new ArrayList<Artifact>();
    static ArrayList<Task> Tasks = new ArrayList<Task>();
    
    
    public static void main(String[] args) throws IOException {
        //String ruta_archivo = "C:\\Users\\Rodrigo\\Desktop\\Library\\Formalización Proceso\\plugin.xmi";
        String path = "C:\\Users\\Rodrigo\\Desktop\\Proceso\\proceso_de_prueba\\plugin.xmi";
        XMI(path);
        searchRoles(File);
        System.out.println(Roles.size());
        System.out.println(Templates.size());
        System.out.println(Artifacts.size());
        System.out.println(Tasks.size());
        for (int i = 0; i < Roles.size(); i++) {
            System.out.println(Roles.get(i).getPresentationName());
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
                        System.out.println(File.get(i));
                        System.out.println("id: "+id);
                        System.out.println("name: "+name);
                        System.out.println("presentationName: "+presentationName);
                        System.out.println("description: "+description);
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
        String producers;
        String collaborators;
        String input;
        String output;
        
        ArrayList<String> id_producers = new ArrayList<String>();
        ArrayList<String> id_collaborators = new ArrayList<String>();
        ArrayList<String> id_input = new ArrayList<String>();
        ArrayList<String> id_output = new ArrayList<String>();
        
        
        boolean flag;

        
        if (separated[0].contains("contentElements")) {
            for (int i = 0; i < separated.length; i++) {
                if (separated[i].contains("org.eclipse.epf.uma")){
/****************************************            ROLES               ****************************************/                    
                    if(separated[i].contains("Role")){
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
                                if(!description.endsWith("\"") && !description.endsWith(">")){
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
                        Rol r = new Rol(name, presentationName, description, id);
                        Roles.add(r);
                    }
/****************************************            TEMPLATE               ****************************************/                    
                    else if(separated[i].contains("Template")){   //Se recupera la informacion de los templates
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
                        Template t = new Template(name, presentationName, id, description);
                        Templates.add(t);
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
                        Artifact a = new Artifact(name, presentationName, description, id, id_template);
                        Artifacts.add(a);
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
                                producers = separated[j].split("=")[1];
                                if (!producers.endsWith("\"") && !producers.endsWith(">")) {
                                    flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        producers = producers + " " + separated[k];
                                        if (separated[k].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                producers = producers.split("\"")[1];
                                for (int l = 0; l < producers.split(" ").length; l++) {
                                    id_producers.add(producers.split(" ")[l]);
                                }
                            }
                            else if (separated[j].split("=")[0].equals("additionallyPerformedBy")){
                                collaborators = separated[j].split("=")[1];
                                if (!collaborators.endsWith("\"") && !collaborators.endsWith(">")) {
                                    flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        collaborators = collaborators + " " + separated[k];
                                        if (separated[k].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                collaborators = collaborators.split("\"")[1];
                                for (int l = 0; l < collaborators.split(" ").length; l++) {
                                    id_collaborators.add(collaborators.split(" ")[l]);
                                }
                            }
                            
                            else if (separated[j].split("=")[0].equals("mandatoryInput")){
                                input = separated[j].split("=")[1];
                                if (!input.endsWith("\"") && !input.endsWith(">")) {
                                    flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        input = input + " " + separated[k];
                                        if (separated[k].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                input = input.split("\"")[1];
                                for (int l = 0; l < input.split(" ").length; l++) {
                                    id_input.add(input.split(" ")[l]);
                                }
                            }
                            
                            else if (separated[j].split("=")[0].equals("output")){
                                output = separated[j].split("=")[1];
                                if (!output.endsWith("\"") && !output.endsWith(">")) {
                                    flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        output = output + " " + separated[k];
                                        if (separated[k].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                output = output.split("\"")[1];
                                for (int l = 0; l < output.split(" ").length; l++) {
                                    id_output.add(output.split(" ")[l]);
                                }
                            }
                        }
                        Task t = new Task(name, presentationName, description, id, id_producers, id_collaborators, id_input, id_output);
                        Tasks.add(t);
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
