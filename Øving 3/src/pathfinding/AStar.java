package pathfinding;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class AStar {

    public static Node[] getPath(Node start, Node end) {
        PriorityQueue<Node> open = new PriorityQueue<>(NODE_COMPARATOR);
        ArrayList<Node> closed = new ArrayList<>();
        HashMap<Node, Node> path = new HashMap<>();
        start.setG(0);
        open.add(start);
        while (!open.isEmpty()) {
            Node current = open.poll();
            if (current.equals(end))
                return reconstruct(path, current, end);
            closed.add(current);
            for (Node n : current.getNeighbours()) {
                if (!closed.contains(n)) {
                    if (!open.contains(n))
                        open.add(n);
                    float gScore = current.getG() + n.getDistance(current);
                    if (gScore < n.getG()) {
                        path.put(n, current);
                        n.setG(gScore);
                    }
                }
            }
        }
        return null;
    }

    private static Node[] reconstruct(HashMap<Node, Node> path, Node current, Node end) {
        ArrayList<Node> totPath = new ArrayList<>();
        while (path.keySet().contains(current)) {
            current = path.get(current);
            totPath.add(0, current);
        }
        totPath.add(end);
        return totPath.toArray(new Node[totPath.size()]);
    }

    public static final Comparator<Node> NODE_COMPARATOR = (n1, n2) -> {
        float diff = n1.getCost() - n2.getCost();
        return diff < 0 ? -1 : diff == 0 ? 0 : 1;
    };

}
