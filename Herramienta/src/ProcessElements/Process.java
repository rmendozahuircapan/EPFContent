
package ProcessElements;

import java.util.*;

public class Process {
    private String id;
    private String name;
    private ArrayList<Activity> activities;

    public Process(String id, String name, ArrayList<Activity> activities) {
        this.id = id;
        this.name = name;
        this.activities = activities;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }
    
}
