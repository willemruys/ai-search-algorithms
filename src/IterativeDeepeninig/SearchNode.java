package IterativeDeepeninig;

public class SearchNode {
    int x;
    int y;
    int depth;

    public SearchNode(int x, int y, int depth) {
        this.x = x;
        this.y = y;
        this.depth = depth;
    }

    /**
     * Compares searchNodeA with searchNodeB
     * 
     * @param searchNodeA
     * @param searchNodeB
     * @return
     */
    public boolean equals(SearchNode searchNodeA, SearchNode searchNodeB) {
        return (searchNodeA == searchNodeB);
    }

    /**
     * Expands search node with its neighbours
     * 
     * @param nodeToExpand
     */
    public void expand(SearchNode nodeToExpand) {
        return;
    }

    public void toString(SearchNode nodeToPrint) {
        System.out.println(nodeToPrint);
    }

}
