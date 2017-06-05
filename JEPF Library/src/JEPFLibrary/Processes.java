
package JEPFLibrary;

/**
 * It corresponds to all processes framed in the elements of a software process.
 * @author Rodrigo
 */
public class Processes {
    
    private DeliveryProcess DeliveryProcess;

    /**
     *
     * @param DeliveryProcess Delivery process of processes.
     */
    public Processes(DeliveryProcess DeliveryProcess) {
        this.DeliveryProcess = DeliveryProcess;
    }
    
    /**
     *
     * @return returns the delivery process.
     */
    public DeliveryProcess getDeliveryProcess() {
        return DeliveryProcess;
    }
    
    
}
