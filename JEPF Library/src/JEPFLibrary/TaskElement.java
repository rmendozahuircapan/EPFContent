
package JEPFLibrary;

import java.util.*;

/**
 * It corresponds a task framed in the elements of a software process.
 * @author Rodrigo
 */
public class TaskElement {
    private String id;
    private String name;
    private String presentationName;
    private String description;
    private ArrayList<RoleElement> producers;
    private ArrayList<RoleElement> collaborators;
    private ArrayList<WorkProductElement> inputs;
    private ArrayList<WorkProductElement> outputs;
    private ArrayList<StepElement> steps;

    /**
     *
     * @param id task identifier
     * @param name name of task
     * @param presentationName name of presentation of task
     * @param description description of task
     * @param producers set of roles that perform the task
     * @param collaborators set of roles that collaborate in the task
     * @param inputs set of workproducts needed for the task
     * @param outputs set of workproducts after the task
     */
    public TaskElement(String id, String name, String presentationName, String description, ArrayList<RoleElement> producers, ArrayList<RoleElement> collaborators, ArrayList<WorkProductElement> inputs, ArrayList<WorkProductElement> outputs) {
        this.id = id;
        this.name = name;
        this.presentationName = presentationName;
        this.description = description;
        this.producers = producers;
        this.collaborators = collaborators;
        this.inputs = inputs;
        this.outputs = outputs;
        this.steps = new ArrayList<>();
    }

    /**
     *
     * @return returns identifier of task.
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return returns name of task.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return returns name of presentation of task.
     */
    public String getPresentationName() {
        return presentationName;
    }

    /**
     *
     * @return returns description of task.
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return returns set of roles that perform the task.
     */
    public ArrayList<RoleElement> getProducers() {
        return producers;
    }

    /**
     *
     * @return returns set of roles that collaborate in the task
     */
    public ArrayList<RoleElement> getCollaborators() {
        return collaborators;
    }

    /**
     *
     * @return returns the set of workproducts needed for the task
     */
    public ArrayList<WorkProductElement> getInputs() {
        return inputs;
    }

    /**
     *
     * @return returns the set of workproducts after the task
     */
    public ArrayList<WorkProductElement> getOutputs() {
        return outputs;
    }

    /**
     *
     * @return returns the set of steps of a task
     */
    public ArrayList<StepElement> getSteps() {
        return steps;
    }
    
    protected void setSteps(ArrayList<StepElement> steps) {
        this.steps = steps;
    }
}
