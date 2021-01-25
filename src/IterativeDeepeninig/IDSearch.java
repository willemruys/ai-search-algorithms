package IterativeDeepeninig;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

import IterativeDeepeninig.test.TestGrid;

public class IDSearch {

    private SearchNode findStartNode(int[][] grid) {
        SearchNode startNode = null;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 2) {
                    startNode = new SearchNode(i, j, 0);
                }
            }
        }
        return startNode;
    }

    private SearchNode findGoalNode(int[][] grid) {
        SearchNode goalNode = null;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 3) {
                    goalNode = new SearchNode(i, j, 0);
                }
            }
        }

        return goalNode;
    }

    public SearchNode iterativeDeepening(int[][] grid) {

        SearchNode goalNode = findGoalNode(grid);
        for (int i = 0; i < 800; i++) {
            boolean result = depthLimitedSearch(i, grid, goalNode);
            if (result) {
                break;

            }
        }
        return null;
    }

    public boolean existsInExplored(ArrayList<SearchNode> exploredSet, SearchNode currentNode) {
        boolean exists = false;
        for (int j = 0; j < exploredSet.size(); j++) {
            if (currentNode.equals(exploredSet.get(j))) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    public boolean isNotATraficJam(int[][] grid, SearchNode node) {
        return (grid[node.getX()][node.getY()] != 1);
    }

    public boolean depthLimitedSearch(int limit, int[][] grid, SearchNode goalNode) {
        Deque<SearchNode> stack = new LinkedList<>();
        ArrayList<SearchNode> explored = new ArrayList<>();
        SearchNode startNode = findStartNode(grid);
        stack.push(startNode);
        int depth = 0;
        while (!stack.isEmpty() && depth < limit) {
            SearchNode currentNode = stack.pop();
            depth++;
            boolean existsInExplored = existsInExplored(explored, currentNode);
            if (!existsInExplored) {
                explored.add(currentNode);
                for (SearchNode node : currentNode.expand()) {
                    if (node.equals(goalNode)) {
                        System.out.println("goal node found: " + node.toString());
                        return true;
                    }

                    if (isNotATraficJam(grid, node)) {
                        stack.push(node);
                    }
                }
            }

        }
        return false;
    }

    public static void main(String[] args) throws Exception {

        IDSearch search = new IDSearch();

        TestGrid tests = new TestGrid();
        int[][] grid1 = tests.getGrid1();
        int[][] grid2 = tests.getGrid2();
        int[][] grid3 = tests.getGrid3();
        int[][] grid4 = tests.getGrid4();
        int[][] grid5 = tests.getGrid5();
        search.iterativeDeepening(grid1);
        search.iterativeDeepening(grid2);
        search.iterativeDeepening(grid3);
        search.iterativeDeepening(grid4);
        search.iterativeDeepening(grid5);

    }

}
