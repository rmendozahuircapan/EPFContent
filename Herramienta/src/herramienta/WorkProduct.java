
package herramienta;
        
public class WorkProduct {
    private String id;
    private String name;
    private String presentationName;
    private String description;
    private String type;
    private Template template;

    public WorkProduct(String id, String name, String presentationName, String description, String type, Template template) {
        this.id = id;
        this.name = name;
        this.presentationName = presentationName;
        this.description = description;
        this.type = type;
        this.template = template;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    
}
