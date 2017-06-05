
package JEPFLibrary;


class Position {
    private String id;
    private int x;
    private int y;

    protected Position(String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    protected String getId() {
        return id;
    }

    protected int getX() {
        return x;
    }

    protected int getY() {
        return y;
    }

}
