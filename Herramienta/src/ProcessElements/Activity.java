
package ProcessElements;

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

    public String getName() {
        return name;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
    
}
