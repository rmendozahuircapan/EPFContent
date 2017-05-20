
package EPFLibrary;

import java.io.IOException;

public class Plugin {
    private static MethodContent MethodContent;
    private static Processes Processes;

    public Plugin(String path) throws IOException {
        Plugin.MethodContent = new MethodContent(path);
        Plugin.Processes = new Processes(path);
    }

    public static MethodContent getMethodContent() {
        return MethodContent;
    }

    public static Processes getProcesses() {
        return Processes;
    }
    
}
