
package breadthFirstSearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    static int breadthFirstSearch(int[] states, int[][] actions, int intialState) {
        int s = states[intialState];

        Queue<Integer> frontier = new LinkedList<Integer>();
        ArrayList<Integer> explored = new ArrayList<>();

        frontier.add(s); // add root to frontier
        while (!frontier.isEmpty()) {
            int node = frontier.remove();
            explored.add(node);
            System.out.println("Explored states: " + explored.toString());
            // for each action in state
            for (int action = 1; action < actions[node - 1].length; action++) {
                int child = actions[node - 1][action];

                if (!explored.contains(child)) {
                    if (child == 7 || child == 8) {
                        System.out.println("Nodes in frontier: " + frontier.toString());
                        System.out.println("Explored states: " + explored.toString());
                        return child;
                    } else {
                        frontier.add(child);
                        System.out.println("Nodes in frontier: " + frontier.toString());
                    }

                }
            }

        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        int[] states = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
        int[][] actions = new int[][] { { 1, 2, 3 }, { 1, 2, 6 }, { 3, 4, 3 }, { 3, 4, 8 }, { 5, 6, 7 }, { 5, 6, 6 },
                { 7, 8, 7 }, { 7, 8, 8 } };

        int intialState = 0;
        int goalState = breadthFirstSearch(states, actions, intialState);

        System.out.println("Reached goal state: " + goalState);

    }
}