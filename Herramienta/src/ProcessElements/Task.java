
package ProcessElements;

import java.util.*;

public class Task {
    private String id;
    private String name;
    private String presentationName;
    private String description;
    private ArrayList<Role> producers;
    private ArrayList<Role> collaborators;
    private ArrayList<WorkProduct> inputs;
    private ArrayList<WorkProduct> outputs;
    private ArrayList<Step> steps;

    public Task(String id, String name, String presentationName, String description, ArrayList<Role> producers, ArrayList<Role> collaborators, ArrayList<WorkProduct> inputs, ArrayList<WorkProduct> outputs) {
        this.id = id;
        this.name = name;
        this.presentationName = presentationName;
        this.description = description;
        this.producers = producers;
        this.collaborators = collaborators;
        this.inputs = inputs;
        this.outputs = outputs;
        this.steps = new ArrayList<Step>();
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

    public ArrayList<Role> getProducers() {
        return producers;
    }

    public ArrayList<Role> getCollaborators() {
        return collaborators;
    }

    public ArrayList<WorkProduct> getInputs() {
        return inputs;
    }

    public ArrayList<WorkProduct> getOutputs() {
        return outputs;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    protected void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }
}
