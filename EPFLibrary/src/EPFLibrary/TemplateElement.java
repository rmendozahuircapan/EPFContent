
package EPFLibrary;

/**
 * It corresponds a template framed in the elements of a software process.
 * @author Rodrigo
 */
public class TemplateElement extends GuidanceElement{
    
    private String description;

    /**
     *
     * @param id templeate identifier
     * @param name name of template
     * @param presentationName name of presentation of template
     * @param description description of template
     */
    public TemplateElement(String id, String name, String presentationName, String description) {
        super(id, name, presentationName);
        this.description = description;
    }

    /**
     *
     * @return returns description of template
     */
    public String getDescription() {
        return description;
    }
    
}
