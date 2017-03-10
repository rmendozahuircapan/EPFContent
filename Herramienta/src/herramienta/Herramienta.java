package herramienta;

import java.io.*;

public class Herramienta {


    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        System.out.println("Hola");
        String ruta_archivo = "C:\\Users\\Rodrigo\\Desktop\\Library\\Formalización Proceso\\plugin.xmi";
        Contenido_XMI(ruta_archivo);
        
    }
    
    public static void inicio_cierre(String linea){
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
        System.out.println(limpieza+"*");
    
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
                System.out.println(linea);
                inicio_cierre(linea);
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
