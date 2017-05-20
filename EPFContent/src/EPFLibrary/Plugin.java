
package EPFLibrary;

import java.io.IOException;

public class Plugin {
    private MethodContent MethodContent;
    private Processes Processes;

    public Plugin(String path) throws IOException {
        this.MethodContent = new MethodContent(path);
        this.Processes = new Processes(path);
    }

    public MethodContent getMethodContent() {
        return MethodContent;
    }

    public Processes getProcesses() {
        return Processes;
    }
    
}
