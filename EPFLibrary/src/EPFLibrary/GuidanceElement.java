
package EPFLibrary;

/**
 * It corresponds a guidance framed in the elements of a software process.
 * @author Rodrigo
 */
public class GuidanceElement {
    private String id;
    private String name;
    private String presentationName;
    
    /**
     *
     * @param id guidance identifier
     * @param name name of guidance
     * @param presentationName name of presentation of guidance
     */
    public GuidanceElement(String id, String name, String presentationName) {
        this.id = id;
        this.name = name;
        this.presentationName = presentationName;
    }

    /**
     *
     * @return returns the identifier of guidance.
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return returns the name of guidance
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return returns the name of presentation of guidance
     */
    public String getPresentationName() {
        return presentationName;
    }
    
}
