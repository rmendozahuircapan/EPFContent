
package EPFLibrary;

import java.io.IOException;

public class Plugin {
    private String name;
    private MethodContent MethodContent;
    private Processes Processes;

    public Plugin(String path) throws IOException {
        this.name = path.replace("\\", "%").split("%")[path.replace("\\", "%").split("%").length - 2];
        this.MethodContent = new MethodContent(path);
        this.Processes = new Processes(path);
    }

    public String getName() {
        return name;
    }

    public MethodContent getMethodContent() {
        return MethodContent;
    }

    public Processes getProcesses() {
        return Processes;
    }
    
}
