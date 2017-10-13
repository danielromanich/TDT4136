package pathfinding;

public class Path {

    private Node[] path, openNodes, closedNodes;
    public Path(Node[] path, Node[] openNodes, Node[] closedNodes) {
        this.path = path;
        this.openNodes = openNodes;
        this.closedNodes = closedNodes;
    }

    public Node[] getPath() {
        return this.path;
    }

    public Node[] getOpenNodes() {
        return this.openNodes;
    }

    public Node[] getClosedNodes() {
        return this.closedNodes;
    }

}
