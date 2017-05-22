
package EPFLibrary;


class Node {
    private String id;
    private String name;
    private String type;

    protected Node(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    protected String getId() {
        return id;
    }

    protected String getName() {
        return name;
    }

    protected String getType() {
        return type;
    }
    
}
