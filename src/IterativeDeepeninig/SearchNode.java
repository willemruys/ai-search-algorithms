package IterativeDeepeninig;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class SearchNode {
    private int x;
    private int y;
    private int depth;

    public SearchNode(int x, int y, int depth) {
        super();
        this.x = x;
        this.y = y;
        this.depth = depth;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getDepth() {
        return this.depth;
    }

    /**
     * Compares searchNodeA with searchNodeB. check for the existence of nodes
     * (x,y,d2) where d2 is less than or equal to d. If there are any such nodes
     * (x,y,d2), then (x,y,d) should not be added to the frontier.
     * 
     * 
     * @param searchNodeA
     * @param searchNodeB
     * @return boolean
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SearchNode other = (SearchNode) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }

    /**
     * Expands search node with its neighbours.
     * 
     * @param nodeToExpand
     */
    public List<SearchNode> expand() {

        List<SearchNode> neighbours = new ArrayList<>();

        if (this.getX() > 0) {
            SearchNode left = new SearchNode(this.getX() - 1, this.getY(), this.getDepth() + 1);
            neighbours.add(left);
        }
        if (this.getX() < 14) {
            SearchNode right = new SearchNode(this.getX() + 1, this.getY(), this.getDepth() + 1);
            neighbours.add(right);
        }
        if (this.getY() > 0) {
            SearchNode up = new SearchNode(this.getX(), this.getY() - 1, this.getDepth() + 1);
            neighbours.add(up);
        }
        if (this.getY() < 14) {
            SearchNode down = new SearchNode(this.getX(), this.getY() + 1, this.getDepth() + 1);
            neighbours.add(down);
        }

        return neighbours;
    }

    @Override
    public String toString() {
        return "Node (x=" + this.x + ", y=" + this.y + ", depth=" + this.depth + ")";
    }

}
