
package ContentElements;

import java.util.*;

public class ProcessElement {
    private String id;
    private String name;
    private ArrayList<ActivityElement> activities;

    public ProcessElement(String id, String name, ArrayList<ActivityElement> activities) {
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

    public ArrayList<ActivityElement> getActivities() {
        return activities;
    }
    
}