package AStarSearch;

public class StarNode {
    int[] x;
    int f;
    int g;
    int h;
    StarNode parent;

    public StarNode(int[] x) {
        this.x = x;
        this.parent = null;
    }

    public StarNode(StarNode parent, int[] x) {
        this.x = x;
        this.parent = parent;
    }

    public StarNode(int[] x, int f, int g, int h, StarNode parent) {
        this.x = x;
        this.f = f;
        this.g = g;
        this.h = h;
        this.parent = parent;
    }

    @Override
    public String toString() {

        return "(" + this.x[0] + "," + this.x[1] + ") " + "Parent: " + this.parent;
    }

}
