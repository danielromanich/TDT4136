package pathfinding;

import board.PathRunnable;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathFinding {


    private static final PathRunnable ASTAR = new PathRunnable("AStar") {

        @Override
        public Path generatePath(Node start, Node end) {
            return aStar(start, end);
        }

    };
    private static final PathRunnable DIJKSTRA = new PathRunnable("Dijkstra") {

        @Override
        public Path generatePath(Node start, Node end) {
            return dijkstra(start, end);
        }

    };
    private static final PathRunnable BFS = new PathRunnable("BFS") {

        @Override
        public Path generatePath(Node start, Node end) {
            return bfs(start, end);
        }

    };

    public static final PathRunnable[] PATH_RUNNABLES = {ASTAR, DIJKSTRA, BFS};

    public static Path aStar(Node start, Node end) {
        return genericPathFinder(new PriorityQueue<>(ASTAR_COMPARATOR), start, end);
    }

    public static Path dijkstra(Node start, Node end) {
        return genericPathFinder(new PriorityQueue<>(DIJKSTRA_COMPARATOR), start, end);
    }

    public static Path bfs(Node start, Node end) {
        return genericPathFinder(new LinkedList<>(), start, end);
    }

    private static Path genericPathFinder(Queue<Node> open, Node start, Node end) {
        ArrayList<Node> closed = new ArrayList<>(); //The closed set
        start.setG(0); //Initializing start node with G=0
        open.add(start);
        while (!open.isEmpty()) {
            Node current = open.poll(); //Fetching the element in the open set with lowest f
            if (current.equals(end))
                break;
            closed.add(current); //Adding the element to the closed list
            for (Node n : current.getNeighbours()) { //For all the current nodes neighbours
                if (!closed.contains(n)) { //If the neighbour has not been visited yet
                    if (!open.contains(n)) //Add to open set if the neighbour is not in the open st
                        open.add(n);
                    double gScore = current.getG() + n.getWeight(); //The new gScore for our node
                    if (gScore < n.getG()) { //If it is better than the previous update it
                        n.setPrevNode(current);
                        n.setG(gScore);
                        open.remove(n); //Remove and add the element to update the queue (Or else we might get unexpected results)
                        open.add(n);
                    }
                }
            }
        }
        List<Node> path = reconstruct(end);
        List<Node> closedSet = closed.stream().filter((node) -> !path.contains(node)).collect(Collectors.toList());
        return new Path(path.toArray(new Node[path.size()]), open.toArray(new Node[open.size()]), closedSet.toArray(new Node[closedSet.size()]));
    }

    private static ArrayList<Node> reconstruct(Node end) {
        ArrayList<Node> path = new ArrayList<>();
        Node current = end;
        while (current != null) {
            path.add(0, current);
            current = current.getPrevNode();
        }
        return path;
    }

    private static final Comparator<Node> ASTAR_COMPARATOR = (n1, n2) -> {
        double diff = n1.getCost() - n2.getCost();
        return diff < 0 ? -1 : diff == 0 ? 0 : 1;
    };

    private static final Comparator<Node> DIJKSTRA_COMPARATOR = (n1, n2) -> {
        double diff = n1.getG() - n2.getG();
        return diff < 0 ? -1 : diff == 0 ? 0 : 1;
    };

}
