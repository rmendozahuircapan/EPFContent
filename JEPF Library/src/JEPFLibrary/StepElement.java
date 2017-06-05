
package JEPFLibrary;

/**
 * It corresponds a step of a task, framed in the elements of a software process.
 * @author Rodrigo
 */
public class StepElement {
    private String id;
    private String name;

    /**
     *
     * @param id step identifier
     * @param name name of step
     */
    public StepElement(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     *
     * @return returns identifier of step.
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return returns name of step.
     */
    public String getName() {
        return name;
    }
    
}
