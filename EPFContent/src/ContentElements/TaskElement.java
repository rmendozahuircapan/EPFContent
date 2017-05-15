
package ContentElements;

import java.util.*;

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

    public TaskElement(String id, String name, String presentationName, String description, ArrayList<RoleElement> producers, ArrayList<RoleElement> collaborators, ArrayList<WorkProductElement> inputs, ArrayList<WorkProductElement> outputs) {
        this.id = id;
        this.name = name;
        this.presentationName = presentationName;
        this.description = description;
        this.producers = producers;
        this.collaborators = collaborators;
        this.inputs = inputs;
        this.outputs = outputs;
        this.steps = new ArrayList<StepElement>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPresentationName() {
        return presentationName;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<RoleElement> getProducers() {
        return producers;
    }

    public ArrayList<RoleElement> getCollaborators() {
        return collaborators;
    }

    public ArrayList<WorkProductElement> getInputs() {
        return inputs;
    }

    public ArrayList<WorkProductElement> getOutputs() {
        return outputs;
    }

    public ArrayList<StepElement> getSteps() {
        return steps;
    }

    protected void setSteps(ArrayList<StepElement> steps) {
        this.steps = steps;
    }
}
