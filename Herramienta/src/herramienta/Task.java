
package herramienta;

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

    public Task(String id, String name, String presentationName, String description, ArrayList<Role> producers, ArrayList<Role> collaborators, ArrayList<WorkProduct> inputs, ArrayList<WorkProduct> outputs) {
        this.id = id;
        this.name = name;
        this.presentationName = presentationName;
        this.description = description;
        this.producers = producers;
        this.collaborators = collaborators;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPresentationName() {
        return presentationName;
    }

    public void setPresentationName(String presentationName) {
        this.presentationName = presentationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Role> getProducers() {
        return producers;
    }

    public void setProducers(ArrayList<Role> producers) {
        this.producers = producers;
    }

    public ArrayList<Role> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(ArrayList<Role> collaborators) {
        this.collaborators = collaborators;
    }

    public ArrayList<WorkProduct> getInputs() {
        return inputs;
    }

    public void setInputs(ArrayList<WorkProduct> inputs) {
        this.inputs = inputs;
    }

    public ArrayList<WorkProduct> getOutputs() {
        return outputs;
    }

    public void setOutputs(ArrayList<WorkProduct> outputs) {
        this.outputs = outputs;
    }

    
}
