
package EPFLibrary;

import java.util.*;

/**
 * @author Rodrigo
 */
public class DeliveryProcess {
    
    private ArrayList<ProcessElement> Process;

    /**
     *
     */
    public DeliveryProcess(ArrayList<ProcessElement> Processes) {
        this.Process = Processes;
    }

    /**
     *
     */
    public ArrayList<ProcessElement> getProcess() {
        return Process;
    }
    
}
