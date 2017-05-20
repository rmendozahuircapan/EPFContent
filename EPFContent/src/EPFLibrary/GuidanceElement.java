
package EPFLibrary;

public class GuidanceElement {
    private String id;
    private String name;
    private String presentationName;
    
    public GuidanceElement(String id, String name, String presentationName) {
        this.id = id;
        this.name = name;
        this.presentationName = presentationName;
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
    
}
