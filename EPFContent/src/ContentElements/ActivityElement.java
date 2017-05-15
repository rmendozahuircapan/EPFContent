
package ContentElements;

import java.util.*;

public class ActivityElement {
    
    private String id;
    private String name;
    private ArrayList<TaskElement> tasks;

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
    
}
