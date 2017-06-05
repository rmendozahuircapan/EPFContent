
package JEPFLibrary;

import java.io.*;
import java.util.*;

/**
 * Corresponds to a library that contains different plugins.
 * @author Rodrigo
 */
public class Library {
    
    private String name;
    private ArrayList<Plugin> Plugins;
    
    private String pathLibrary = new String();
    private String pathExport = new String();
    
    /**
     *
     * @param path it is the path of the library's folder
     * @throws IOException 
     */
    public Library(String path) throws IOException {
        
        setPaths(path);
        
        this.name = path.replace("\\", " ").split(" ")[path.replace("\\", " ").split(" ").length - 1];
        this.Plugins = searchPlugins();
    
    }

    /**
     *
     * @return returns all plugins of library
     */
    public ArrayList<Plugin> getPlugins() {
        return Plugins;
    }
    
    /**
     *
     * @return return name of library
     */
    public String getName() {
        return name;
    }
    
    private void setPaths(String path) {
        this.pathLibrary = path;
        this.pathExport = path + "\\export.xmi";
    }
    
/********************************************************************************************************/
/********************************************************************************************************/
    
    private ArrayList<Plugin> searchPlugins() throws IOException{
        ArrayList<Plugin> plugins = new ArrayList<>();
        ArrayList<String> pathsPlugins = new ArrayList<>();
        ArrayList<String> ExportFile = XMIRead(pathExport);
        boolean flag;
        for (int i = 0; i < ExportFile.size(); i++) {
            String[] separated = ExportFile.get(i).split(" ");
            if (separated[0].equals("<resourceDescriptors")) {
                for (int j = 0; j < separated.length; j++) {
                    if (separated[j].contains("uri")) {
                        String uri = separated[j].split("=")[1];
                        if (!uri.endsWith("\"") && !uri.endsWith(">")) {
                            flag = true;
                            int k = j;
                            while (flag){
                                k++;
                                uri = uri + " " + separated[k];
                                if (separated[k].contains("\"")) {
                                    flag = false;
                                }
                            }
                        }
                        if (uri.length() > "\\plugin.xmi".length()) {
                            uri = pathLibrary + "\\" +uri.split("\"")[1].replace("/", "\\");
                            pathsPlugins.add(uri);
                        }
                    }
                }
            }
        }
        for (String path : pathsPlugins) {
            plugins.add(new Plugin(path));
        }
        
        return plugins;
    }

/********************************************************************************************************/
    
    private ArrayList<String> XMIRead(String path) throws FileNotFoundException, IOException {
        
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
    
    private String cleanLine(String line){

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
    
/********************************************************************************************************/
/********************************************************************************************************/    
}
