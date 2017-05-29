
package EPFLibrary;

import java.util.*;

/**
 * It corresponds a process framed in the elements of a software process.
 * @author Rodrigo
 */
public class ProcessElement {
    private String id;
    private String name;
    private ArrayList<ActivityElement> activities;
    private WorkFlow workflow;

    /**
     *
     * @param id process identifier
     * @param name name of process
     * @param activities Activities that make up the process.
     */
    public ProcessElement(String id, String name, ArrayList<ActivityElement> activities) {
        this.id = id;
        this.name = name;
        this.activities = activities;
    }

    /**
     *
     * @return returns identifier of process.
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return returns name of process.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return returns all activities of process.
     */
    public ArrayList<ActivityElement> getActivities() {
        return activities;
    }

    /**
     *
     * @return returns workflow of activity diagram of process.
     */
    public WorkFlow getWorkflow() {
        return workflow;
    }

    protected void setWorkflow(WorkFlow workflow) {
        this.workflow = workflow;
    }
    
}
