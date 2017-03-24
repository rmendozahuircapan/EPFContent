
package herramienta;

public class Template {
    
    private String id;
    private String name;
    private String presentationName;
    private String description;

    public Template(String id, String name, String presentationName, String description) {
        this.id = id;
        this.name = name;
        this.presentationName = presentationName;
        this.description = description;
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

    
}