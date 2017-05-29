
package EPFLibrary;

public class TemplateElement extends GuidanceElement{
    
    private String description;

    public TemplateElement(String id, String name, String presentationName, String description) {
        super(id, name, presentationName);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    
}
