
package WorkFlow;


public class Position {
    private String idNode;
    private int x;
    private int y;

    public Position(String idNode, int x, int y) {
        this.idNode = idNode;
        this.x = x;
        this.y = y;
    }

    public String getIdNode() {
        return idNode;
    }

    public void setIdNode(String idNode) {
        this.idNode = idNode;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}
