package AStarSearch;

import java.util.Deque;
import java.util.LinkedList;

public class AStarSearch {

    public LinkedList<StarNode> generateChildren(StarNode currentNode, int[][] graph) {
        LinkedList<StarNode> children = new LinkedList<StarNode>();
        int[][] adjecentSquares = new int[][] { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 }, { -1, -1 }, { -1, 1 },
                { 1, -1 }, { 1, 1 } };
        for (int[] newPosition : adjecentSquares) {
            int[] position = new int[] { (currentNode.x[0] + newPosition[0]), (currentNode.x[1] + newPosition[1]) };

            // if out of range continue
            if ((position[0] > graph.length - 1) || position[0] < 0 || position[1] > graph.length - 1
                    || position[1] < 0) {
                continue;
            }

            // if path is unwalkable continue
            if (graph[position[0]][position[1]] == 1) {
                continue;
            }

            StarNode childNode = new StarNode(currentNode, position);
            children.add(childNode);

        }

        return children;

    }

    public void runSearch(StarNode startNode, StarNode goalNode, int[][] graph) {

        LinkedList<StarNode> openSet = new LinkedList<StarNode>();
        LinkedList<StarNode> closedSet = new LinkedList<StarNode>();
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            int indexSmallest = 0;

            // get current node
            for (int i = 0; i < openSet.size(); i++) {
                if (openSet.get(i).f < openSet.get(indexSmallest).f) {
                    indexSmallest = i;
                }
            }
            StarNode currentNode = openSet.remove(indexSmallest);
            // add to closed list
            closedSet.add(currentNode);

            // goal test
            if (currentNode.x[0] == goalNode.x[0] && currentNode.x[1] == goalNode.x[1]) {
                System.out.println("Goal node found!");
                for (StarNode node : closedSet) {
                    System.out.println(node);
                }
                break;
            }

            for (StarNode child : generateChildren(currentNode, graph)) {
                if (closedSet.contains(child)) {
                    continue;
                }

                child.g = currentNode.g + 1;
                child.h = (int) (Math.pow(child.x[0] - goalNode.x[0], 2) + Math.pow(child.x[1] - goalNode.x[1], 2));

                child.f = child.g + child.h;

                if (openSet.contains(child)) {
                    int index = openSet.indexOf(child);
                    if (child.g > openSet.get(index).g) {
                        continue;
                    }
                }
                System.out.println("adding to openset: " + child.toString());
                openSet.add(child);
            }
        }

    }

    public static int[] findGoalNodePosition(int[][] grid) {
        int[] position = null;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 2) {
                    position = new int[] { i, j };
                }
            }

        }

        return position;
    }

    public static int[] findStartNodePosition(int[][] grid) {
        int[] position = null;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 3) {
                    position = new int[] { i, j };
                }
            }

        }

        return position;
    }

    public static void main(String args[]) throws Exception {

        int[][] grid = new int[][] { { 3, 0, 0, 0, 0 }, { 0, 1, 0, 1, 0 }, { 0, 0, 0, 1, 0 }, { 0, 1, 0, 1, 0 },
                { 0, 0, 0, 2, 0 }, };
        int[] startPostion = findStartNodePosition(grid);
        int[] goalPosition = findGoalNodePosition(grid);
        StarNode startNode = new StarNode(startPostion);
        StarNode goalNode = new StarNode(goalPosition);

        AStarSearch starSearch = new AStarSearch();

        starSearch.runSearch(startNode, goalNode, grid);

    }
}