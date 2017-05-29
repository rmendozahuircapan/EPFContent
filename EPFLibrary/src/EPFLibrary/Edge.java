
package EPFLibrary;


class Edge {
    private String id;
    private String name;
    private Node source;
    private Node target;

    protected Edge(String id, String name, Node source, Node target) {
        this.id = id;
        this.name = name;
        this.source = source;
        this.target = target;
    }

    protected String getId() {
        return id;
    }

    protected String getName() {
        return name;
    }

    protected Node getSource() {
        return source;
    }

    protected Node getTarget() {
        return target;
    }
    
}
