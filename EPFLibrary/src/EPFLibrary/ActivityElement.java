
package EPFLibrary;

import java.util.*;

/**
 * It corresponds an activity framed in the elements of a software process.
 * @author Rodrigo
 */
public class ActivityElement {
    
    private String id;
    private String name;
    private ArrayList<TaskElement> tasks;
    private WorkFlow workflow;

    /**
     *
     * @param id activity identifier
     * @param name name of activity
     * @param tasks tasks that make up the activity
     */
    public ActivityElement(String id, String name, ArrayList<TaskElement> tasks) {
        this.id = id;
        this.name = name;
        this.tasks = tasks;
    }

    /**
     *
     * @return returns the identifier of the activity.
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return returns the namo of the activity.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return returns the tasks of the activiity.
     */
    public ArrayList<TaskElement> getTasks() {
        return tasks;
    }

    /**
     *
     * @return returns the workflow or activity diagram of the activity.
     */
    public WorkFlow getWorkflow() {
        return workflow;
    }
    
    protected void setWorkflow(WorkFlow workflow) {
        this.workflow = workflow;
    }
    
}
