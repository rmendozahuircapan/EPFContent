
package Tool;


import static Tool.App.DiagramFile;
import static Tool.App.ModelFile;
import static Tool.App.PluginFile;
import static Tool.App.TaskFile;

import java.io.*;

public class XMIReadFile {
         
    public static void XMI(String path, String typePath) throws FileNotFoundException, IOException {
        File archive = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archive = new File (path);
            fr = new FileReader (archive);
            br = new BufferedReader(fr);

            String line;
            while((line=br.readLine())!=null){
                if (typePath.equals("Plugin")) {
                    PluginFile.add(cleanLine(line));
                }
                else if (typePath.equals("Task")) {
                    TaskFile.add(cleanLine(line));
                }
                else if (typePath.equals("Model")){
                    ModelFile.add(cleanLine(line));
                }
                else if (typePath.equals("Diagram")){
                    DiagramFile.add(cleanLine(line));
                }
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
