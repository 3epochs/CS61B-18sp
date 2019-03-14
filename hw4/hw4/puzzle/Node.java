package hw4.puzzle;

public class Node {
    public WorldState worldState;
    public int moves;
    public Node prev;
    public int estimateMoves;

    public Node(WorldState worldState, int moves, Node prev) {
        this.worldState = worldState;
        this.moves = moves;
        this.prev = prev;
        this.estimateMoves = worldState.estimatedDistanceToGoal();
    }
}
