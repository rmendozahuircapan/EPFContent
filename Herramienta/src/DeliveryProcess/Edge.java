
package DeliveryProcess;


public class Edge {
    private String id;
    private String name;
    private Node source;
    private Node target;

    public Edge(String id, String name, Node source, Node target) {
        this.id = id;
        this.name = name;
        this.source = source;
        this.target = target;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Node getSource() {
        return source;
    }

    public Node getTarget() {
        return target;
    }
    
}
