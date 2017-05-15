
package ContentElements;
        
import java.util.ArrayList;

public class WorkProductElement {
    private String id;
    private String name;
    private String presentationName;
    private String description;
    private String type;
    private ArrayList<TemplateElement> templates;

    public WorkProductElement(String id, String name, String presentationName, String description, String type, ArrayList<TemplateElement> templates) {
        this.id = id;
        this.name = name;
        this.presentationName = presentationName;
        this.description = description;
        this.type = type;
        this.templates = templates;
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

    public String getType() {
        return type;
    }

    public ArrayList<TemplateElement> getTemplates() {
        return templates;
    }
    
}
