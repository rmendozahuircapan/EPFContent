package herramienta;

import java.io.*;
import java.util.*;
import javax.management.relation.RoleStatus;


public class Herramienta {
    
    static ArrayList<Rol> Roles = new ArrayList<Rol>();
    static ArrayList<Template> Templates = new ArrayList<Template>();
    static ArrayList<Artifact> Artifacts = new ArrayList<Artifact>();
    static ArrayList<Task> Tasks = new ArrayList<Task>();
    
    public static void main(String[] args) throws IOException {
        String ruta_archivo = "C:\\Users\\Rodrigo\\Desktop\\Library\\Formalización Proceso\\plugin.xmi";
        Contenido_XMI(ruta_archivo);
        System.out.println("********************************************************************");
        System.out.println("********************    INFORMACIÓN GENERAL     ********************");
        System.out.println("********************************************************************");
        System.out.println("El archivo contiene:");
        System.out.println("                    > "+Roles.size()+" roles.");
        System.out.println("                    > "+Templates.size()+" templates.");
        System.out.println("                    > "+Artifacts.size()+" artefactos.");
        System.out.println("                    > "+Tasks.size()+" tareas.");
        System.out.println("********************************************************************");
        System.out.println("********************            DETALLE         ********************");
        System.out.println("********************************************************************");
        System.out.println("********************             ROLES          ********************");
        imprimirRoles();
        System.out.println("********************************************************************");
        System.out.println("********************            TEMPLATE        ********************");
        imprimirTemplates();
        System.out.println("********************************************************************");
        System.out.println("********************            ARTIFACT        ********************");
        imprimirArtifacts();
        System.out.println("********************************************************************");
        System.out.println("********************             TAREAS         ********************");
        imprimirTasks();
        System.out.println("********************************************************************");
        
    }
    
    public static String linea_limpia(String linea){
        String[] linea_separada = linea.split(" ");
        String limpieza = "";
        for (int i = 0; i < linea_separada.length; i++) {
            if (linea_separada[i].length() > 0) {
                if ( i < (linea_separada.length - 1)){
                        limpieza = limpieza + linea_separada[i] + " ";
                }else{
                        limpieza = limpieza + linea_separada[i];
                    }
            }
        }
        return limpieza;
    }
    
    public static void Informacion(String linea){// (Cambiar nombre fx) Se recupera la información de roles, templates y artefactos
        String[] aux = linea.split(" ");

        String name = "";
        String nombre = "";
        String descripcion = "";
        String id = "";
        String id_template = "";
        String realizadores = "";
        String colaboradores = "";
        String input = "";
        String output = "";
        
        ArrayList<String> id_realizadores = new ArrayList<String>();
        ArrayList<String> id_colaboradores = new ArrayList<String>();
        ArrayList<String> id_input = new ArrayList<String>();
        ArrayList<String> id_output = new ArrayList<String>();
        
        
        boolean flag;

        
        if (aux[0].contains("contentElements")) {
            for (int i = 0; i < aux.length; i++) {
                if (aux[i].contains("org.eclipse.epf.uma")){
/****************************************            ROLES               ****************************************/                    
                    if(aux[i].contains("Role")){
                        for (int j = 0; j < aux.length; j++) {
                            if (aux[j].contains("xmi:id")) {
                                id = aux[j].split("=")[1].split("\"")[1];
                            }
                            else if (aux[j].contains("name")) {
                                name = aux[j].split("=")[1].split("\"")[1];
                            }
                            else if (aux[j].contains("presentationName")) {
                                nombre = aux[j].split("=")[1];
                                if (!nombre.endsWith("\"") && !nombre.endsWith(">") ) {
                                    flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        nombre = nombre + " " + aux[k];
                                        if (aux[k].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                nombre = nombre.split("\"")[1];
                            }
                            else if (aux[j].contains("briefDescription")) {
                                descripcion = aux[j].split("=")[1];
                                if(!descripcion.endsWith("\"") && !descripcion.endsWith(">")){
                                    flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        descripcion = descripcion + " " + aux[k];
                                        if (aux[k].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                descripcion = descripcion.split("\"")[1];
                            }
                        }
                        Rol r = new Rol(name, nombre, descripcion, id);
                        Roles.add(r);
                    }
/****************************************            TEMPLATE               ****************************************/                    
                    else if(aux[i].contains("Template")){   //Se recupera la informacion de los templates
                        for (int j = 0; j < aux.length; j++) {
                            if (aux[j].contains("xmi:id")) {
                                id = aux[j].split("=")[1].split("\"")[1];
                            }
                            else if (aux[j].contains("name")) {
                                name = aux[j].split("=")[1].split("\"")[1];
                            }
                            else if (aux[j].contains("presentationName")) {
                                nombre = aux[j].split("=")[1];
                                if (!nombre.endsWith("\"") && !nombre.endsWith(">")) {
                                    flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        nombre = nombre + " " + aux[k];
                                        if (aux[k].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                nombre = nombre.split("\"")[1];
                            }
                        }
                        Template t = new Template(name, nombre, id);
                        Templates.add(t);
                    }
/****************************************            ARTIFACT               ****************************************/
                    else if(aux[i].contains("Artifact")){ //Falta buscar e identificar cada uno de los templates asociados
                        for (int j = 0; j < aux.length; j++) {
                            if (aux[j].contains("xmi:id")) {
                                id = aux[j].split("=")[1].split("\"")[1];
                            }
                            else if (aux[j].contains("name")) {
                                name = aux[j].split("=")[1].split("\"")[1];
                            }
                            else if (aux[j].contains("presentationName")) {
                                nombre = aux[j].split("=")[1];
                                if (!nombre.endsWith("\"") && !nombre.endsWith(">")) {
                                    flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        nombre = nombre + " " + aux[k];
                                        if (aux[k].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                nombre = nombre.split("\"")[1];
                            }
                            else if (aux[j].contains("briefDescription")) {
                                descripcion = aux[j].split("=")[1];
                                if (!descripcion.endsWith("\"") && !descripcion.endsWith(">")) {
                                    flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        descripcion = descripcion + " " + aux[k];
                                        if (aux[k].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                descripcion = descripcion.split("\"")[1];
                            }
                            else if(aux[j].contains("templates")){
                                id_template = aux[j].split("=")[1].split("\"")[1];
                            }
                        }
                        Artifact a = new Artifact(name, nombre, descripcion, id, id_template);
                        Artifacts.add(a);
                    }
/****************************************            TASK               ****************************************/                    
                    else if(aux[i].contains("Task")){ //Falta buscar e identificar cada uno de los tareas asociados
                        for (int j = 0; j < aux.length; j++) {
                            if (aux[j].contains("xmi:id")) {
                                id = aux[j].split("=")[1].split("\"")[1];
                            }
                            else if (aux[j].contains("name")) {
                                name = aux[j].split("=")[1].split("\"")[1];
                            }
                            else if (aux[j].contains("presentationName")) {
                                nombre = aux[j].split("=")[1];
                                if (!nombre.endsWith("\"") && !nombre.endsWith(">") ) {
                                    flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        nombre = nombre + " " + aux[k];
                                        if (aux[k].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                nombre = nombre.split("\"")[1];
                            }
                            else if (aux[j].contains("briefDescription")) {
                                descripcion = aux[j].split("=")[1];
                                if (!descripcion.endsWith("\"") && !descripcion.endsWith(">")) {
                                    flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        descripcion = descripcion + " " + aux[k];
                                        if (aux[k].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                descripcion = descripcion.split("\"")[1];
                            }
                            else if (aux[j].split("=")[0].equals("performedBy")){
                                realizadores = aux[j].split("=")[1];
                                if (!realizadores.endsWith("\"") && !realizadores.endsWith(">")) {
                                    flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        realizadores = realizadores + " " + aux[k];
                                        if (aux[k].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                realizadores = realizadores.split("\"")[1];
                                for (int l = 0; l < realizadores.split(" ").length; l++) {
                                    id_realizadores.add(realizadores.split(" ")[l]);
                                }
                            }
                            else if (aux[j].split("=")[0].equals("additionallyPerformedBy")){
                                colaboradores = aux[j].split("=")[1];
                                if (!colaboradores.endsWith("\"") && !colaboradores.endsWith(">")) {
                                    flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        colaboradores = colaboradores + " " + aux[k];
                                        if (aux[k].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                colaboradores = colaboradores.split("\"")[1];
                                for (int l = 0; l < colaboradores.split(" ").length; l++) {
                                    id_colaboradores.add(colaboradores.split(" ")[l]);
                                }
                            }
                            
                            else if (aux[j].split("=")[0].equals("mandatoryInput")){
                                input = aux[j].split("=")[1];
                                if (!input.endsWith("\"") && !input.endsWith(">")) {
                                    flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        input = input + " " + aux[k];
                                        if (aux[k].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                input = input.split("\"")[1];
                                for (int l = 0; l < input.split(" ").length; l++) {
                                    id_input.add(input.split(" ")[l]);
                                }
                            }
                            
                            else if (aux[j].split("=")[0].equals("output")){
                                output = aux[j].split("=")[1];
                                if (!output.endsWith("\"") && !output.endsWith(">")) {
                                    flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        output = output + " " + aux[k];
                                        if (aux[k].contains("\"")) {
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
                        Task t = new Task(name, nombre, descripcion, id, id_realizadores, id_colaboradores, id_input, id_output);
                        Tasks.add(t);
                    }
                }
            }
        }
    }
    
    public static String nombreTemplate(String id){
        for (int i = 0; i < Templates.size(); i++) {
            if(Templates.get(i).getId().equals(id)){
                return Templates.get(i).getNombre();
            }
        }
        return "";
    }
    
    public static String nombreRol(String id){
        for (int i = 0; i < Roles.size(); i++) {
            if(Roles.get(i).getId().equals(id)){
                return Roles.get(i).getNombre();
            }
        }
        return "";
    }
    
    public static String nombreArtifact(String id){
        for (int i = 0; i < Artifacts.size(); i++) {
            if(Artifacts.get(i).getId().equals(id)){
                return Artifacts.get(i).getNombre();
            }
        }
        return "";
    }
    
    public static void imprimirRoles(){
        for (int i = 0; i < Roles.size(); i++) {
            System.out.println("");
            System.out.println("Nombre: "+Roles.get(i).getNombre());
            System.out.println("Descripción: "+Roles.get(i).getDescripcion());
            if(i < (Roles.size()-1)){
                System.out.println("--------------------------------------------------------------------");
            }
        }
    }
    
    public static void imprimirTemplates(){
        for (int i = 0; i < Templates.size(); i++) {
            System.out.println("");
            System.out.println("Nombre: "+Templates.get(i).getNombre());
            if(i < (Templates.size()-1)){
                System.out.println("--------------------------------------------------------------------");
            }
        }
    }
    
    public static void imprimirArtifacts(){
        for (int i = 0; i < Artifacts.size(); i++) {
            System.out.println("");
            System.out.println("Nombre: "+Artifacts.get(i).getNombre());
            System.out.println("Descripción: "+Artifacts.get(i).getDescripcion());
            if (!Artifacts.get(i).getId_template().equals("")) {
                System.out.println("Template: "+nombreTemplate(Artifacts.get(i).getId_template()));
            }
            if(i < (Artifacts.size()-1)){
                System.out.println("--------------------------------------------------------------------");
            }
        }
    }
    
    public static void imprimirTasks(){
        for (int i = 0; i < Tasks.size(); i++) {
            System.out.println("");
            System.out.println("Nombre: "+Tasks.get(i).getNombre());
            System.out.println("Descripción: "+Tasks.get(i).getDescripcion());
            if (Tasks.get(i).getId_realizadores().size()>0) {
                System.out.println("Realizadores:");
                for (int j = 0; j < Tasks.get(i).getId_realizadores().size(); j++) {
                    System.out.println("             > "+nombreRol(Tasks.get(i).getId_realizadores().get(j)));
                }
            }
            if (Tasks.get(i).getId_colaboradores().size()>0) {
                System.out.println("Colaboradores:");
                for (int j = 0; j < Tasks.get(i).getId_colaboradores().size(); j++) {
                    System.out.println("             > "+nombreRol(Tasks.get(i).getId_colaboradores().get(j)));
                }
            }
            if (Tasks.get(i).getId_input().size()>0) {
                System.out.println("Input:");
                for (int j = 0; j < Tasks.get(i).getId_input().size(); j++) {
                    System.out.println("             > "+nombreArtifact(Tasks.get(i).getId_input().get(j)));
                }
            }
            if (Tasks.get(i).getId_output().size()>0) {
                System.out.println("Output:");
                for (int j = 0; j < Tasks.get(i).getId_output().size(); j++) {
                    System.out.println("             > "+nombreArtifact(Tasks.get(i).getId_output().get(j)));
                }
            }
            if(i < (Tasks.size()-1)){
                System.out.println("--------------------------------------------------------------------");
            }
        }
    }
    
    public static void Contenido_XMI(String ruta_archivo) throws FileNotFoundException, IOException {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File (ruta_archivo);
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while((linea=br.readLine())!=null){
                Informacion(linea_limpia(linea));
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
