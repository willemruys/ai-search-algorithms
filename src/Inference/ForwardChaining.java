package Inference;

import java.util.*;

public class ForwardChaining {
    ArrayList<int[]> clauses;

    public ForwardChaining() {
        clauses = new ArrayList<>();
    }

    public boolean forwardChaining(int n) {
        // Simplified version of forward chaining algorithm: does not follow the
        // textbook. This implementation does not run in linear time because it
        // scans all clauses multiple times.
        boolean[] model = new boolean[n + 1]; // All symbols set to false initially.

        // Sanity check clauses
        for (int i = 0; i < clauses.size(); i++) {
            int posLits = 0;
            int[] clause = clauses.get(i);
            for (int j = 0; j < clause.length; j++) {
                assert clause[i] <= n && clause[i] >= -n : "Found reference to variable larger than n.";
                if (clause[j] > 0) {
                    posLits++;
                }
            }

            assert posLits <= 1 : "At most one positive literal is allowed in each clause.";
        }

        // Iterate through set of clauses applying modus ponens until we reach a
        // fixpoint.
        boolean fixPoint = false;

        while (!fixPoint) {
            fixPoint = true;

            for (int i = 0; i < clauses.size(); i++) {
                int[] clause = clauses.get(i);
                // Check all symbols that appear negated in this clause.
                // If all true, then apply modus ponens.

                boolean allTrue = true;

                for (int j = 0; j < clause.length; j++) {
                    if (clause[j] < 0 && !model[-clause[j]]) {
                        allTrue = false;
                        break;
                    }
                }

                if (allTrue) {
                    boolean goalClause = true;
                    for (int j = 0; j < clause.length; j++) {
                        if (clause[j] > 0) {
                            goalClause = false;
                            if (!model[clause[j]]) {
                                model[clause[j]] = true;
                                fixPoint = false;
                                System.out.println("Inferred " + clause[j] + " with clause " + Arrays.toString(clause));
                            }

                            break;
                        }
                    }
                    if (goalClause) {
                        // This is a goal clause
                        System.out.println("No models satisfy all clauses simultaneously. False goal clause: "
                                + Arrays.toString(clause));
                        return false;
                    }
                }
            }
        }

        System.out.println("Model: ");
        for (int i = 1; i < model.length; i++) {
            System.out.println("Variable " + i + " = " + model[i]);
        }
        return true;
    }

    public void addClause(int[] c) {
        clauses.add(c);
    }

    public void resetClauses() {
        clauses.clear();
    }

    public static void example() {

        // The following is the CNF of Figure 7.16 in AIMA
        // Symbols A, B, L, M, P, Q are numbered 1..6.
        ForwardChaining fc = new ForwardChaining();

        fc.addClause(new int[] { -5, 6 });
        fc.addClause(new int[] { -3, -4, 5 });
        fc.addClause(new int[] { -2, -3, 4 });
        fc.addClause(new int[] { -1, -5, 3 });
        fc.addClause(new int[] { -1, -2, 3 });
        fc.addClause(new int[] { 1 });
        fc.addClause(new int[] { 2 });

        // Call forward chaining. 6 is the number of proposition symbols, which must be
        // numbered 1..6.
        boolean modelExists = fc.forwardChaining(6);
        System.out.println("Model exists: " + modelExists);
    }

    /**
     */
    public static void wumpus() {

        ForwardChaining fc = new ForwardChaining();

        // safe clauses 
        fc.addClause(new int[] { -2, 3 });
        fc.addClause(new int[] { -6, 7 });
        fc.addClause(new int[] { -10, 11 });
        fc.addClause(new int[] { -14, 15 });
        fc.addClause(new int[] { -18, 19 });
        fc.addClause(new int[] { -22, 23 });

        // wumpus location clauses diagonal
        fc.addClause(new int[] { -6, -10, -15, 4 });
        fc.addClause(new int[] { -2, -11, -14, 8 });
        fc.addClause(new int[] { -2, -7, -14, 12 });
        fc.addClause(new int[] { -3, -6, -10, 16 });

        // wumpus location horizontal
        fc.addClause(new int[] { -10, -15, 4 });
        fc.addClause(new int[] { -11, -14, 8 });
        fc.addClause(new int[] { -2, -7, 12 });
        fc.addClause(new int[] { -3, -6, 16 });

        fc.addClause(new int[] { 2 }); // safety in location 1.1
        fc.addClause(new int[] { 11 }); // safety in in location 2.1
        fc.addClause(new int[] { 14 }); // stench in location 2.2
        boolean modelExists = fc.forwardChaining(22);
        System.out.println("Model exists: " + modelExists);
    }

    public static void main(String[] args) {

        wumpus();

    }

}
