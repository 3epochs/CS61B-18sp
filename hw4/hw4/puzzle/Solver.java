package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Comparator;

public class Solver {
    private MinPQ<Node> nodeMinPQ;
    private Comparator<Node> cmp;
    private int moves;
    private Node result;
    private ArrayList<WorldState> arr = new ArrayList<>(100);

    public Solver(WorldState initial) {
        cmp = new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                if ((node1.moves + node1.estimateMoves) <
                        (node2.moves + node2.estimateMoves)) {
                    return -1;
                } else if ((node1.moves + node1.estimateMoves) ==
                        (node2.moves + node2.estimateMoves)) {
                    return 0;
                } else {
                    return 1;
                }
            }
        };
        nodeMinPQ = new MinPQ<>(cmp);
        nodeMinPQ.insert(new Node(initial, 0, null));
        Node tmp = nodeMinPQ.delMin();
        while (!tmp.worldState.isGoal()) {
            Iterable<WorldState> i = tmp.worldState.neighbors();
            moves = count(tmp) + 1;
            for (WorldState ws : i) {
                if (tmp.prev != null) {
                    if (tmp.prev.worldState.equals(ws)) {
                        continue;
                    }
                }
                nodeMinPQ.insert(new Node(ws, moves, tmp));
            }
            tmp = nodeMinPQ.delMin();
        }
        result = tmp;
        addResult(result);
        arr.add(result.worldState);
    }


    public int moves() {
        return arr.size() - 1;
    }

    public Iterable<WorldState> solution() {
        return arr;
    }

    private Node addResult(Node p) {
        if (p.prev == null) {
            return p;
        }
        Node res = addResult(p.prev);
        arr.add(p.prev.worldState);
        return res;
    }

    private int count(Node p) {
        Node tmp = p;
        int count = 0;
        while (tmp.prev != null) {
            tmp = tmp.prev;
            count += 1;
        }
        return count;
    }
}
