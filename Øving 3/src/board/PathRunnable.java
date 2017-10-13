package board;

import pathfinding.Node;
import pathfinding.Path;

public abstract class PathRunnable {

    private final String name;
    public PathRunnable(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public abstract Path generatePath(Node start, Node end);

}
