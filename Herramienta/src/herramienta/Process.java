
package Herramienta;

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

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<Activity> activities) {
        this.activities = activities;
    }
    
    
}
