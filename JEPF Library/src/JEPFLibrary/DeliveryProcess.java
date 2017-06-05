
package JEPFLibrary;

import java.util.*;

/**
 * It corresponds to the set of processes to carry out something.
 * @author Rodrigo
 */
public class DeliveryProcess {
    
    private ArrayList<ProcessElement> Process;

    /**
     *
     * @param Processes processes pertaining to the delivery process.
     */
    public DeliveryProcess(ArrayList<ProcessElement> Processes) {
        this.Process = Processes;
    }

    /**
     *
     * @return returns process of the delivery process.
     */
    public ArrayList<ProcessElement> getProcess() {
        return Process;
    }
    
}
