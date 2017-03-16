package herramienta;

import java.io.*;
import java.util.*;
import javax.management.relation.RoleStatus;


public class Herramienta {
    
    static ArrayList<Rol> Roles = new ArrayList<Rol>();
    static ArrayList<Template> Templates = new ArrayList<Template>();
    static ArrayList<Artifact> Artifacts = new ArrayList<Artifact>();
    
    public static void main(String[] args) throws IOException {
        String ruta_archivo = "C:\\Users\\Rodrigo\\Desktop\\Library\\Formalización Proceso\\plugin.xmi";
        Contenido_XMI(ruta_archivo);
        
        System.out.println("*******************************************");

        for (int i = 0; i < Roles.size(); i++) {
            System.out.println("Rol: "+Roles.get(i).getNombre());
        }
        
        System.out.println("*******************************************");        
        
        for (int i = 0; i < Templates.size(); i++) {
            System.out.println("Template: "+Templates.get(i).getNombre());
        }

        System.out.println("*******************************************");
        
        for (int i = 0; i < Artifacts.size(); i++) {
            System.out.println("Artifact: "+Artifacts.get(i).getNombre());
        }
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
    
    public static void Mostrar_todo(String linea){// (Cambiar nombre fx) Se recupera la información de roles, templates y artefactos
        String[] aux = linea.split(" ");

        String name = "";
        String nombre = "";
        String descripcion = "";
        String id = "";
        String id_template = "";
        
        boolean flag;

        
        if (aux[0].contains("contentElements")) {
            for (int i = 0; i < aux.length; i++) {
                if (aux[i].contains("org.eclipse.epf.uma")){
                    if(aux[i].contains("Role")){    // Se recupera la infomación de los roles
                        for (int j = 0; j < aux.length; j++) {
                            if (aux[j].contains("xmi:id")) {
                                id = aux[j].split("=")[1].split("\"")[1];
                            }
                            else if (aux[j].contains("name")) {
                                name = aux[j].split("=")[1].split("\"")[1];
                            }
                            else if (aux[j].contains("presentationName")) {
                                nombre = aux[j].split("=")[1];
                                if (!nombre.endsWith("\"")) {
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
                                flag = true;
                                int k = j;
                                while (flag){
                                    k++;
                                    descripcion = descripcion + " " + aux[k];
                                    if (aux[k].contains("\"")) {
                                        flag = false;
                                    }
                                }
                                descripcion = descripcion.split("\"")[1];
                            }
                        }
                        Rol r = new Rol(name, nombre, descripcion, id);
                        Roles.add(r);
                    }
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
                                if (!nombre.endsWith("\"")) {
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
                                if (!nombre.endsWith("\"")) {
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
                                flag = true;
                                int k = j;
                                while (flag){
                                    k++;
                                    descripcion = descripcion + " " + aux[k];
                                    if (aux[k].contains("\"")) {
                                        flag = false;
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
                        
                        System.out.println(linea);
                        System.out.println("name:           "+name);
                        System.out.println("nombre:         "+nombre);
                        System.out.println("descripción:    "+descripcion);
                        System.out.println("id:             "+id);
                        System.out.println("template:       "+id_template);
                        System.out.println("-------------------------------------------------");
                    }else if(aux[i].contains("Task")){ //Falta buscar e identificar cada uno de los templates asociados
                        for (int j = 0; j < aux.length; j++) {
                            if (aux[j].contains("name")) {
                                name = aux[j].split("=")[1].split("\"")[1];
                            }
                            if (aux[j].contains("presentationName")) {
                                nombre = aux[j].split("=")[1];
                                if (!nombre.endsWith("\"")) {
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
                            if (aux[j].contains("briefDescription")) {
                                descripcion = aux[j].split("=")[1];
                                flag = true;
                                int k = j;
                                while (flag){
                                    k++;
                                    descripcion = descripcion + " " + aux[k];
                                    if (aux[k].contains("\"")) {
                                        flag = false;
                                    }
                                }
                                descripcion = descripcion.split("\"")[1];
                            }
                        }
                    }
                    else{
                        //System.out.println(linea);
                    }
                }
            }
        }
    }
    
    public static String nombreTemplate(String id){
        for (int i = 0; i < Templates.size(); i++) {
            if(Templates.get(i).id.equals(id)){
                return Templates.get(i).nombre;
            }
        }
        return "";
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
                //System.out.println(linea);
                //System.out.println(linea_limpia(linea));
                Mostrar_todo(linea_limpia(linea));
                
            }
        }
        catch(Exception e){
            e.printStackTrace();
            //System.out.println("El archivo no se encuentra.");
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
