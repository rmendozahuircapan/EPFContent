
package EPFLibrary;
        
import java.util.ArrayList;

/**
 * It corresponds a wprk product framed in the elements of a software process.
 * @author Rodrigo
 */
public class WorkProductElement {
    private String id;
    private String name;
    private String presentationName;
    private String description;
    private String type;
    private ArrayList<TemplateElement> templates;

    /**
     *
     * @param id workproduct identifier
     * @param name name of workproduct  
     * @param presentationName name of presentation of workproduct 
     * @param description description of of workproduct 
     * @param type type of workproduct 
     * @param templates set of templates of workproduct 
     */
    public WorkProductElement(String id, String name, String presentationName, String description, String type, ArrayList<TemplateElement> templates) {
        this.id = id;
        this.name = name;
        this.presentationName = presentationName;
        this.description = description;
        this.type = type;
        this.templates = templates;
    }

    /**
     *
     * @return returns identifier of workproduct 
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return returns name of workproduct 
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return returns name of presentation of workproduct 
     */
    public String getPresentationName() {
        return presentationName;
    }

    /**
     *
     * @return returns description of workproduct 
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return returns type of workproduct 
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @return returns set of templates of workproduct 
     */
    public ArrayList<TemplateElement> getTemplates() {
        return templates;
    }
    
}
