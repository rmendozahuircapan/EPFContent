
package EPFLibrary;

import java.io.IOException;

/**
 * It corresponds a plugin framed in the elements of a software process.
 * @author Rodrigo
 */
public class Plugin {
    private String name;
    private MethodContent MethodContent;
    private Processes Processes;

    /**
     *
     * @param path it is the path of the archive xmi of plugin
     * @throws IOException
     */
    public Plugin(String path) throws IOException {
        this.name = path.replace("\\", "%").split("%")[path.replace("\\", "%").split("%").length - 2];
        this.MethodContent = new MethodContent(path);
        this.Processes = new Processes(path);
    }

    /**
     *
     * @return returns the name of plugin
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return returns all method content of plugin
     */
    public MethodContent getMethodContent() {
        return MethodContent;
    }

    /**
     *
     * @return returns the processes of plugin
     */
    public Processes getProcesses() {
        return Processes;
    }
    
}
