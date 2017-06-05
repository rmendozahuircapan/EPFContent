
package JEPFLibrary;

/**
 * It corresponds a role framed in the elements of a software process.
 * @author Rodrigo
 */
public class RoleElement {
    private String id;
    private String name;
    private String presentationName;
    private String description;

    /**
     *
     * @param id role identifier
     * @param name name of the role
     * @param presentationName name of presentation of the role
     * @param description description of the role
     */
    public RoleElement(String id, String name, String presentationName, String description) {
        this.id = id;
        this.name = name;
        this.presentationName = presentationName;
        this.description = description;
    }

    /**
     *
     * @return returns identifier of role
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return returns name of role
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return returns name of presentation of role
     */
    public String getPresentationName() {
        return presentationName;
    }

    /**
     *
     * @return returns description of role
     */
    public String getDescription() {
        return description;
    }
    
}
