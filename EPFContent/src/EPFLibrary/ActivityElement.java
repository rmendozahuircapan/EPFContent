
package EPFLibrary;

import java.util.*;

public class ActivityElement {
    
    private String id;
    private String name;
    private ArrayList<TaskElement> tasks;
    private WorkFlow workflow;

    public ActivityElement(String id, String name, ArrayList<TaskElement> tasks) {
        this.id = id;
        this.name = name;
        this.tasks = tasks;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<TaskElement> getTasks() {
        return tasks;
    }

    public WorkFlow getWorkflow() {
        return workflow;
    }

    public void setWorkflow(WorkFlow workflow) {
        this.workflow = workflow;
    }
    
}
