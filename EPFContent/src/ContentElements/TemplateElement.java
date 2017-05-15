
package ContentElements;

public class TemplateElement {
    
    private String id;
    private String name;
    private String presentationName;
    private String description;

    public TemplateElement(String id, String name, String presentationName, String description) {
        this.id = id;
        this.name = name;
        this.presentationName = presentationName;
        this.description = description;
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
    
}