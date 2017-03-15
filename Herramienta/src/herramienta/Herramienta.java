package herramienta;

import java.io.*;

public class Herramienta {


    public static void main(String[] args) throws IOException {
            String ruta_archivo = "C:\\Users\\Rodrigo\\Desktop\\Library\\Formalización Proceso\\plugin.xmi";
            Contenido_XMI(ruta_archivo);
    }
    
    public static String linea_limpia(String linea){
        String[] linea_separada = linea.split(" ");
        String limpieza = "";
        //System.out.println(limpieza.length() + " jdlakjsdlj");
        for (int i = 0; i < linea_separada.length; i++) {
            if (linea_separada[i].length() > 0) {
                boolean href = linea_separada[i].contains("href");
                boolean cierre = linea_separada[i].contains("/>");
                if ( i < (linea_separada.length - 1)){
                    if(!href){
                        limpieza = limpieza + linea_separada[i] + " ";
                    }
                }else{
                    if(href && cierre){
                        limpieza = limpieza + "/>";
                    }else{
                        limpieza = limpieza + linea_separada[i];
                    }
                }
            }
        }
        //System.out.println(limpieza+"*");
        return limpieza;
    }
    
    
    public static void Mostrar_todo(String linea){// se buscan roles
        String[] aux = linea.split(" ");
        String[] limpio;
        if (aux[0].contains("contentElements")) {
            for (int i = 0; i < aux.length; i++) {
                if (aux[i].contains("org.eclipse.epf.uma")){
                    if(aux[i].contains("Role")){
                        //System.out.println("Rol");
                        for (int j = 0; j < aux.length; j++) {
                            if (aux[j].contains("name")) {
                                limpio = aux[j].split("=");
                                String name = limpio[1];
                                limpio = name.split("\"");
                                name = limpio[1];
                                //System.out.println("> Name: "+ name);
                            }
                            if (aux[j].contains("presentationName")) {
                                limpio = aux[j].split("=");
                                String nombre = limpio[1];
                                if (!nombre.endsWith("\"")) {
                                    boolean flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        nombre = nombre + " " + aux[k];
                                        if (aux[k].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                limpio = nombre.split("\"");
                                nombre = limpio[1];
                                //System.out.println("Nombre: "+ nombre);
                            }
                            if (aux[j].contains("briefDescription")) {
                                limpio = aux[j].split("=");
                                String descripcion = limpio[1];
                                boolean flag = true;
                                int k = j;
                                while (flag){
                                    k++;
                                    descripcion = descripcion + " " + aux[k];
                                    if (aux[k].contains("\"")) {
                                        flag = false;
                                    }
                                }
                                limpio = descripcion.split("\"");
                                descripcion = limpio[1];
                                //System.out.println("Descripción: "+ descripcion);
                            }
                        }
                    }else if(aux[i].contains("Template")){
                        //System.out.println(linea);
                        //System.out.println("Template");
                        for (int j = 0; j < aux.length; j++) {
                            if (aux[j].contains("name")) {
                                limpio = aux[j].split("=");
                                String name = limpio[1];
                                limpio = name.split("\"");
                                name = limpio[1];
                                //System.out.println("> Name: "+ name);
                            }
                            if (aux[j].contains("presentationName")) {
                                limpio = aux[j].split("=");
                                String nombre = limpio[1];
                                if (!nombre.endsWith("\"")) {
                                    boolean flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        nombre = nombre + " " + aux[k];
                                        if (aux[k].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                limpio = nombre.split("\"");
                                nombre = limpio[1];
                                //System.out.println("Nombre: "+ nombre);
                            }
                        }
                    }else if(aux[i].contains("Artifact")){ //Falta buscar e identificar cada uno de los templates asociados
                        //System.out.println("Artifact");
                        for (int j = 0; j < aux.length; j++) {
                            if (aux[j].contains("name")) {
                                limpio = aux[j].split("=");
                                String name = limpio[1];
                                limpio = name.split("\"");
                                name = limpio[1];
                                //System.out.println("> Name: "+ name);
                            }
                            if (aux[j].contains("presentationName")) {
                                limpio = aux[j].split("=");
                                String nombre = limpio[1];
                                if (!nombre.endsWith("\"")) {
                                    boolean flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        nombre = nombre + " " + aux[k];
                                        if (aux[k].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                limpio = nombre.split("\"");
                                nombre = limpio[1];
                                //System.out.println("Nombre: "+ nombre);
                            }
                            if (aux[j].contains("briefDescription")) {
                                limpio = aux[j].split("=");
                                String descripcion = limpio[1];
                                boolean flag = true;
                                int k = j;
                                while (flag){
                                    k++;
                                    descripcion = descripcion + " " + aux[k];
                                    if (aux[k].contains("\"")) {
                                        flag = false;
                                    }
                                }
                                limpio = descripcion.split("\"");
                                descripcion = limpio[1];
                                //System.out.println("Descripción: "+ descripcion);
                            }
                        }
                    }else if(aux[i].contains("Task")){ //Falta buscar e identificar cada uno de los templates asociados
                        for (int j = 0; j < aux.length; j++) {
                            if (aux[j].contains("name")) {
                                limpio = aux[j].split("=");
                                String name = limpio[1];
                                limpio = name.split("\"");
                                name = limpio[1];
                                //System.out.println("> Name: "+ name);
                            }
                            if (aux[j].contains("presentationName")) {
                                limpio = aux[j].split("=");
                                String nombre = limpio[1];
                                if (!nombre.endsWith("\"")) {
                                    boolean flag = true;
                                    int k = j;
                                    while (flag){
                                        k++;
                                        nombre = nombre + " " + aux[k];
                                        if (aux[k].contains("\"")) {
                                            flag = false;
                                        }
                                    }
                                }
                                limpio = nombre.split("\"");
                                nombre = limpio[1];
                                //System.out.println("Nombre: "+ nombre);
                            }
                            if (aux[j].contains("briefDescription")) {
                                limpio = aux[j].split("=");
                                String descripcion = limpio[1];
                                boolean flag = true;
                                int k = j;
                                while (flag){
                                    k++;
                                    descripcion = descripcion + " " + aux[k];
                                    if (aux[k].contains("\"")) {
                                        flag = false;
                                    }
                                }
                                limpio = descripcion.split("\"");
                                descripcion = limpio[1];
                                //System.out.println("Descripción: "+ descripcion);
                            }
                        }
                    }
                    else{
                        System.out.println(linea);
                    }
                }
            }
            //System.out.println(linea);
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
                //System.out.println(linea);
                //System.out.println(linea_limpia(linea));
                Mostrar_todo(linea_limpia(linea));
                
            }
        }
        catch(Exception e){
            //e.printStackTrace();
            System.out.println("El archivo no se encuentra.");
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
