
package EPFLibrary;

import java.util.*;

/**
 * Corresponds to all the content of method framed in the elements of a software process.
 * @author Rodrigo
 */
public class MethodContent {
    
    private ArrayList<GuidanceElement> Guidances;
    private ArrayList<RoleElement> Roles;
    private ArrayList<TaskElement> Tasks;
    private ArrayList<WorkProductElement> WorkProducts;
    
    /**
     * 
     * @param Guidances List of guidances of method content
     * @param Roles List of roles of method content
     * @param Tasks List of tasks of method content
     * @param WorkProducts List of workproducts of method content
     */
    
    public MethodContent(ArrayList<GuidanceElement> Guidances, ArrayList<RoleElement> Roles, ArrayList<TaskElement> Tasks, ArrayList<WorkProductElement> WorkProducts) {
        this.Guidances = Guidances;
        this.Roles = Roles;
        this.Tasks = Tasks;
        this.WorkProducts = WorkProducts;
        
    }
    /**
     *
     * @return returns all guidances of plugin
     */
    public ArrayList<GuidanceElement> getGuidances() {
        return Guidances;
    }

    /**
     *
     * @return returns all roles of plugin
     */
    public ArrayList<RoleElement> getRoles() {
        return Roles;
    }

    /**
     *
     * @return returns all taks of plugin
     */
    public ArrayList<TaskElement> getTasks() {
        return Tasks;
    }

    /**
     *
     * @return returns all workproducts of plugin
     */
    public ArrayList<WorkProductElement> getWorkProducts() {
        return WorkProducts;
    }

}
