
package herramienta;

import java.util.*;

public class Activity {
    
    private String id;
    private String name;
    private ArrayList<Task> tasks;

    public Activity(String id, String name, ArrayList<Task> tasks) {
        this.id = id;
        this.name = name;
        this.tasks = tasks;
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

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    
    
}
